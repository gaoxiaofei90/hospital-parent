package com.llw.hospital.ds.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.aeye.master.algorithm.vo.ScoreResult;
import com.aeye.master.util.ModelUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.llw.hospital.api.SysAlgorithmService;
import com.llw.hospital.api.SysRecognizeService;
import com.llw.hospital.common.base.constants.BaseConstants;
import com.llw.hospital.common.util.ParamUtil;
import com.llw.hospital.ds.entity.PersonFeatures;
import com.llw.hospital.ds.mapper.PersonFeaturesDao;
import com.llw.hospital.ds.util.RecognizeUtil;
import com.llw.hospital.dto.FeatureByte;
import com.llw.hospital.dto.RecognizeResult;

@Component
@Service(timeout = 10000)
public class SysRecognizeServiceImpl implements SysRecognizeService{

    private Logger logger = LoggerFactory.getLogger(SysRecognizeServiceImpl.class);
    @Autowired
    SysAlgorithmService sysAlgorithmService;
    @Autowired
    PersonFeaturesDao personFeaturesDao;
    @Override
    public RecognizeResult recognize1(Long personId, List<String> picUrls, Integer bioType, List<Map<String, String>> picMetaData) {
        logger.info("recognize1 start");
        long startTime = System.currentTimeMillis();

        if(null == personId || CollectionUtils.isEmpty(picUrls) || null == bioType){
            logger.error("参数不正确{},{}",personId,bioType);
            return RecognizeResult.fail("参数不正确");
        }

        String algVersion = ParamUtil.getParam(ParamUtil.SYS_PARAM_ALGVERSION_PREFIX+bioType,"FACE303");
        String thresholdStr = ParamUtil.getParam(ParamUtil.SYS_PARAM_THRESHOLD_PREFIX+bioType,"0.512");

        //从文件服务器获取图片
        List<String> pics = RecognizeUtil.getPics(picUrls);

        if(CollectionUtils.isEmpty(pics)){
            for(String url:picUrls){
                logger.error("没有图片：",url);
            }
            return RecognizeResult.fail("缺少图片");
        }

        //从缓存或者数据库获取特征数据
        List<byte[]> features = getFeatures(personId,bioType,algVersion);
        if(CollectionUtils.isEmpty(features)){
            logger.error("没有模板，人员id:{}",personId);
            return RecognizeResult.fail("没有模板");
        }

        List<String> encryptFeatures = new ArrayList<>(features.size());
        for(byte[] feature:features){
            encryptFeatures.add(Base64.encodeBase64String(feature));
        }
        //构造特征结构
        String featureModel = ModelUtil.constructFeatures(encryptFeatures,bioType*10+1);

        RecognizeResult recognizeResult = new RecognizeResult();
        //进行比对
        ScoreResult scoreResult = sysAlgorithmService.compare(pics,bioType,featureModel,algVersion);

        if(!scoreResult.isSuccess()){
            return RecognizeResult.fail("特征提取失败");
        }else{
            recognizeResult.setScore(scoreResult.getMax());
            if(scoreResult.getMax() > Float.valueOf(thresholdStr)){
                recognizeResult.setSuccess(true);
                recognizeResult.setPersonId(personId);
                recognizeResult.setMessage("比对通过");
            }else{
                recognizeResult.setSuccess(false);
                recognizeResult.setPersonId(personId);
                recognizeResult.setMessage("比对失败");
            }
        }

        logger.info("recognize1 end");
        long endTime = System.currentTimeMillis();
        logger.info("1比1总共耗时{}毫秒",endTime-startTime);
        return recognizeResult;
    }
    
    
    public RecognizeResult recognizePic(List<String> picUrls1, List<String> picUrls2, Integer bioType) {
    	logger.info("recognize1 start");
    	long startTime = System.currentTimeMillis();
    	
    	if(CollectionUtils.isEmpty(picUrls1) || CollectionUtils.isEmpty(picUrls2) || null == bioType){
    		return RecognizeResult.fail("参数不正确");
    	}
    	
    	String algVersion = ParamUtil.getParam(ParamUtil.SYS_PARAM_ALGVERSION_PREFIX+bioType,"FACE303");
    	String thresholdStr = ParamUtil.getParam(ParamUtil.SYS_PARAM_THRESHOLD_PREFIX+bioType,"0.512");
    	
		// 提取特征信息
		FeatureByte featureByte1 = sysAlgorithmService.extractFeatures(picUrls1,11);
		FeatureByte featureByte2 = sysAlgorithmService.extractFeatures(picUrls2,11);
    	
        List<String> encryptFeatures = new ArrayList<String>();
        encryptFeatures.add(Base64.encodeBase64String(featureByte1.getFeatures().get(0)));
        String featureModel1 = ModelUtil.constructFeatures(encryptFeatures,bioType*10+1);
        
        encryptFeatures = new ArrayList<String>();
        encryptFeatures.add(Base64.encodeBase64String(featureByte2.getFeatures().get(0)));
        String featureModel2 = ModelUtil.constructFeatures(encryptFeatures,bioType*10+1);
        
    	//进行比对
    	ScoreResult scoreResult = sysAlgorithmService.compare(featureModel1, featureModel2, bioType, algVersion);
    	
    	RecognizeResult recognizeResult = new RecognizeResult();
    	if(!scoreResult.isSuccess()){
    		return RecognizeResult.fail("特征提取失败");
    	}else{
    		recognizeResult.setScore(scoreResult.getMax());
    		if(scoreResult.getMax() > Float.valueOf(thresholdStr)){
    			recognizeResult.setSuccess(true);
    			recognizeResult.setMessage("比对通过");
    		}else{
    			recognizeResult.setSuccess(false);
    			recognizeResult.setMessage("比对失败");
    		}
    	}
    	
    	logger.info("recognize1 end");
    	long endTime = System.currentTimeMillis();
    	logger.info("1比1总共耗时{}毫秒",endTime-startTime);
    	return recognizeResult;
    }
    

    private List<byte[]> getFeatures(Long personId,int biotype,String algVersion) {
        List<byte[]> features = new ArrayList<>(0);
        PersonFeatures personFeatures = new PersonFeatures();
        personFeatures.setPersonId(personId);
        personFeatures.setMajorVersion(algVersion);
        personFeatures.setStatus(BaseConstants.MODEL_STATUS_NOMAL);
        personFeaturesDao.select(personFeatures).forEach((item)->{
            features.add(item.getFeatures());
        });

        return features;
    }
}
