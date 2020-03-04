package com.llw.hospital.api;

import java.util.List;

import com.aeye.master.algorithm.vo.FeatureResult;
import com.aeye.master.algorithm.vo.ScoreResult;
import com.llw.hospital.common.exception.ServiceException;
import com.llw.hospital.dto.FeatureByte;

/**
 * @author wendellpeng
 * @Title: AlgorithmService
 * @ProjectName gov-parent
 * @Description: 算法服务
 * @date 2018/9/10 13:29
 */
public interface SysAlgorithmService {

    /**
     * 提取特征
     * @param pics 需要提取特征的照片
     * @param bioType 生物类型 11 人脸
     * @param algVersion 算法版本  人脸用FACE305
     * @return FeatureResult.featureModel为提取到的特征字符串，直接保存接口，比对时需要用到
     */
    FeatureResult extractFeature(List<String> pics,Integer bioType,String algVersion);

    FeatureByte extractFeatures(List<String> picUrls, Integer bioType) throws ServiceException;

    /**
     * 特征比对
     * @param pics 比对的图片列表
     * @param bioType 生物类型 11 人脸
     * @param featureModel 模板数据，需要是FeatureResult中的featureModel字段
     * @param algVersion 算法版本，需要与抽取特征时的算法版本一致，否则比对不通过
     * @return ScoreResult.success表示调用是否成功 成功了则会返回3个分数 max min avg
     */
    ScoreResult compare(List<String> pics, Integer bioType,String featureModel,String algVersion);


    /**
     * 特征比对
     * @param featureModel1
     * @param featureModel
     * @param bioType
     * @param algVersion
     * @return
     */
    ScoreResult compare(String featureModel1,String featureModel, Integer bioType,String algVersion);

}
