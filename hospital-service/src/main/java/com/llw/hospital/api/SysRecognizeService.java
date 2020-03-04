package com.llw.hospital.api;

import java.util.List;
import java.util.Map;

import com.llw.hospital.dto.RecognizeResult;

/**
 * 认证识别相关业务
 * 只提供算法层面的服务，不提供持久化功能，具体的业务逻辑由调用端完成
 * 1比1接口会根据人员id自动查找对应版本的特征进行比对
 * 1比n接口则依赖本地缓存的特征进行比对，而特征的更新则依赖全局的事件HospitalEvent和PersonEvent
 * 注意：为了达到动态路由的目的，recognizeN接口需要从consumer端发起调用。
 */
public interface SysRecognizeService {

    /**
     * 1比1接口
     * @param personId 人员编号
     * @param picUrls 比对的图片地址
     * @param bioType 图片生物类型 11人脸
     * @param picMetaData 图片的元数据信息 可以包括人脸位置，人眼位置等等，算法需要可扩充
     * @return
     */
    RecognizeResult recognize1(Long personId, List<String> picUrls, Integer bioType, List<Map<String,String>> picMetaData);
    
    RecognizeResult recognizePic(List<String> picUrls1, List<String> picUrls2, Integer bioType);

}
