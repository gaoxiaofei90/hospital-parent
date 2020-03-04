package com.llw.hospital.api.vo.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.aeye.master.algorithm.vo.BaseResult;
import com.aeye.master.algrithm.FaceRecognize;
import com.aeye.master.exception.AlgVersionNotSupportException;
import com.aeye.master.exception.AlgorithmCommuException;
import com.aeye.master.exception.GetAlgorithmConnectionException;
import com.jcl.common.util.JSONUtils;
import com.llw.hospital.common.util.MapUrlParamsUtils;
import com.llw.hospital.common.util.ParamUtil;
import com.llw.hospital.common.util.StringUtils;

/**
 * 活体service
 */
@Service
public class AliveService implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(AliveService.class);

    public boolean aliveDetect(List<String> pics){
    	
    	String aliveConfig = ParamUtil.getParam(ParamUtil.SYS_PARAM_ALIVE_CONFIG);
        if(StringUtils.isEmpty(aliveConfig)){
            logger.debug("没有开启后端活体，默认活体成功");
            return true;
        }

        try {
            boolean success =  false;
            
            Map<String,String> map = MapUrlParamsUtils.getUrlParams(aliveConfig);
            if(map.containsKey("host") && map.containsKey("port") && map.containsKey("threshold")) {
            	FaceRecognize faceRecognize = new FaceRecognize(map.get("host"), Integer.parseInt(map.get("port")));
                
                BaseResult<List<String>> listBaseResult = faceRecognize.aliveDetect(pics);
                if(logger.isInfoEnabled()){
                    logger.info("活体得分：{}", JSONUtils.toJSONString(listBaseResult.getValue()));
                }

                if(listBaseResult.isSuccess()){
                    boolean allSuccess = true;
                    for (String s : listBaseResult.getValue()) {
                        if(Float.parseFloat(s) < Float.parseFloat(map.get("threshold"))){
                            allSuccess = false;
                        }
                    }
                    success = allSuccess;
                }
                return success;
            }else {
            	logger.error("活体参数{}配置不正确，默认活体失败",aliveConfig);
            	return false;
            }
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
        }
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        
    }
}
