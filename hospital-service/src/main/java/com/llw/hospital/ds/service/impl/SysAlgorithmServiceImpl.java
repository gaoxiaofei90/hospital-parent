package com.llw.hospital.ds.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.llw.hospital.dto.FeatureByte;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.aeye.master.algorithm.vo.FeatureResult;
import com.aeye.master.algorithm.vo.Model;
import com.aeye.master.algorithm.vo.ScoreResult;
import com.aeye.master.algrithm.FaceRecognize;
import com.aeye.master.algrithm.FingerPrintRecognize;
import com.aeye.master.algrithm.FingerVeinRecognize;
import com.aeye.master.algrithm.Recognize;
import com.aeye.master.exception.AlgVersionNotSupportException;
import com.aeye.master.exception.AlgorithmCommuException;
import com.aeye.master.exception.GetAlgorithmConnectionException;
import com.aeye.master.util.ModelUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.jcl.common.util.JSONUtils;
import com.llw.hospital.api.SysAlgorithmService;
import com.llw.hospital.common.exception.ExceptionCode;
import com.llw.hospital.common.exception.ServiceException;
import com.llw.hospital.common.util.ParamUtil;
import com.llw.hospital.util.FileReadWriteUtil;

/**
 * @author wendellpeng
 * @Title: AlgorithmServiceImpl
 * @ProjectName gov-parent
 * @Description: 算法服务实现，内部采用socket实现
 * @date 2018/9/10 13:39
 */
@Component
@Service(timeout = 10000)
public class SysAlgorithmServiceImpl implements SysAlgorithmService, ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    private ApplicationContext applicationContext;

    private Map<Integer,Recognize> recognizeMap = new HashMap<>();

    private Logger logger = LoggerFactory.getLogger(SysAlgorithmServiceImpl.class);

    public Map<Integer, Recognize> getRecognizeMap() {
        return recognizeMap;
    }

    public void setRecognizeMap(Map<Integer, Recognize> recognizeMap) {
        this.recognizeMap = recognizeMap;
    }


    @Override
    public FeatureResult extractFeature(List<String> pics, Integer bioType,String algVersion) {
        FeatureResult featureResult = new FeatureResult();
        featureResult.setSuccess(false);
        if(null != recognizeMap.get(bioType))
        {
            Model model = new Model();
            model.setAlgVersion(algVersion);
            model.setBioType(bioType);
            model.setModelType(bioType *10+1);
            model.setImages(ModelUtil.constructModelImage(pics,bioType*10+1));
            try {
               return recognizeMap.get(bioType).analysisFeature(model);
            } catch (AlgVersionNotSupportException e) {
                logger.error("暂不支持算法版本为"+algVersion+"的算法");
            } catch (GetAlgorithmConnectionException e) {
                logger.error("连接算法服务器失败");
            } catch (AlgorithmCommuException e) {
                logger.error("算法服务器返回异常");
            }
        }else{
            logger.error("暂不支持生物类型"+bioType+"的算法");
        }
        return featureResult;
    }

    @Override
    public FeatureByte extractFeatures(List<String> picUrls, Integer bioType) throws ServiceException {
        String algVersion = ParamUtil.getParam(ParamUtil.SYS_PARAM_ALGVERSION_PREFIX + bioType);
        List<String> pics = new ArrayList<>(1);
        for(String url:picUrls){
            pics.add(Base64.encodeBase64String(FileReadWriteUtil.readFile(url)));
        }

        if(CollectionUtils.isEmpty(pics)){
            throw new ServiceException(ExceptionCode.IMG_ERROR,"获取图片失败");
        }

        if(null != recognizeMap.get(bioType))
        {
            Model model = new Model();
            model.setAlgVersion(algVersion);
            model.setBioType(bioType);
            model.setModelType(bioType *10+1);
            model.setImages(ModelUtil.constructModelImage(pics,bioType*10+1));
            try {
                FeatureResult featureResult = recognizeMap.get(bioType).analysisFeature(model);
                if(featureResult.isSuccess()){
                    List<byte[]> features = new ArrayList<>(1);
                    JSONObject featObj = JSONUtils.parseObject(featureResult.getFeatureModel());
                    String var3 = featObj.getString("feature");
                    JSONObject jsonObject = JSONUtils.parseObject(var3);
                    for(String key:jsonObject.keySet()){
                        String featureStr = jsonObject.getString("0");
                        byte[] feature = Base64.decodeBase64(featureStr);
                        features.add(feature);
                    }
                    FeatureByte result = new FeatureByte();
                    result.setAlgVersion(algVersion);
                    result.setFeatures(features);
                    return result;
                }else{
                    logger.error("特征抽取失败");
                    throw new ServiceException(ExceptionCode.ALG_RETURN_ERROR,"算法服务器返回异常");
                }
            } catch (AlgVersionNotSupportException e) {
                logger.error("暂不支持算法版本为"+algVersion+"的算法");
                throw new ServiceException(ExceptionCode.ALG_ERROR_NOT_SUPPORT,"算法版本不支持");
            } catch (GetAlgorithmConnectionException e) {
                logger.error("连接算法服务器失败");
                throw new ServiceException(ExceptionCode.ALG_ERROR_NOT_CONNECTED,"连接算法服务器失败");
            } catch (AlgorithmCommuException e) {
                logger.error("算法服务器返回异常");
                throw new ServiceException(ExceptionCode.ALG_RETURN_ERROR,"算法服务器返回异常");
            }
        }else{
            logger.error("暂不支持生物类型"+bioType+"的算法");
            throw new ServiceException(ExceptionCode.ALG_ERROR_NOT_SUPPORT,"算法类型不支持");
        }
    }

    @Override
    public ScoreResult compare(List<String> pics, Integer bioType, String featureModel,String algVersion) {
        ScoreResult scoreResult = new ScoreResult();
        if(null != recognizeMap.get(bioType))
        {
            Model model1 = new Model();
            model1.setAlgVersion(algVersion);
            model1.setBioType(bioType);
            model1.setModelType(bioType *10+1);
            model1.setFeature(featureModel);

            Model model2 = new Model();
            model2.setAlgVersion(algVersion);
            model2.setBioType(bioType);
            model2.setModelType(bioType *10+1);
            model2.setImages(ModelUtil.constructModelImage(pics,bioType*10+1));

            try {
                return recognizeMap.get(bioType).calculateScoreWithFeatureAndPic(model1,model2,algVersion);
            } catch (AlgVersionNotSupportException e) {
                logger.error("暂不支持算法版本为"+algVersion+"的算法");
                throw new ServiceException(ExceptionCode.ALG_ERROR_NOT_SUPPORT,"算法版本不支持");
            } catch (GetAlgorithmConnectionException e) {
                logger.error("连接算法服务器失败");
                throw new ServiceException(ExceptionCode.ALG_ERROR_NOT_CONNECTED,"连接算法服务器失败");
            } catch (AlgorithmCommuException e) {
                logger.error("算法服务器返回异常");
                throw new ServiceException(ExceptionCode.ALG_RETURN_ERROR,"算法服务器返回异常");
            } catch (Exception e) {
            	logger.error("算法对比返回异常：",e);
            	throw new ServiceException(ExceptionCode.ALG_RETURN_ERROR,"算法服务器返回异常");
            }
        }else{
            logger.error("暂不支持生物类型"+bioType+"的算法");
        }
        return scoreResult;
    }

    @Override
    public ScoreResult compare(String featureModel1, String featureModel2, Integer bioType, String algVersion) {
        ScoreResult scoreResult = new ScoreResult();
        if(null != recognizeMap.get(bioType))
        {
            Model model1 = new Model();
            model1.setAlgVersion(algVersion);
            model1.setBioType(bioType);
            model1.setModelType(bioType *10+1);
            model1.setFeature(featureModel1);

            Model model2 = new Model();
            model2.setAlgVersion(algVersion);
            model2.setBioType(bioType);
            model2.setModelType(bioType *10+1);
            model2.setFeature(featureModel2);

            try {
                return recognizeMap.get(bioType).calculateScoreWithFeature(model1,model2,algVersion);
            } catch (AlgVersionNotSupportException e) {
                logger.error("暂不支持算法版本为"+algVersion+"的算法");
            } catch (GetAlgorithmConnectionException e) {
                logger.error("连接算法服务器失败");
            } catch (AlgorithmCommuException e) {
                logger.error("算法服务器返回异常");
            }
        }else{
            logger.error("暂不支持生物类型"+bioType+"的算法");
        }
        return scoreResult;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Map<String,Recognize> map = applicationContext.getBeansOfType(Recognize.class);
        if(null != map && !map.isEmpty()){
            for(Recognize recognize:map.values()){
                if(recognize instanceof FaceRecognize){
                    recognizeMap.put(11,recognize);
                }
                if(recognize instanceof FingerPrintRecognize){
                    recognizeMap.put(15,recognize);
                }
                if(recognize instanceof FingerVeinRecognize){
                    recognizeMap.put(12,recognize);
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
