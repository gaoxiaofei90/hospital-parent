package com.llw.hospital.ds.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jcl.orm.tkmapper.BaseMapper;
import com.llw.hospital.ds.entity.PersonFeatures;

/**
 * 人员特性信息
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-03-03 10:18:37
 */
public interface PersonFeaturesDao extends BaseMapper<PersonFeatures> {

    @Select("select person_id,features from t_person_features where create_time >= #{date} and status = 10 and ora_hash(person_id,#{clusterSize}-1) = #{clusterIndex} and bio_type = #{bioType} and major_version = #{algVersion} order by features_id")
    List<PersonFeatures> loadFeature(@Param(value = "date") Date date, @Param(value = "clusterIndex") Integer clusterIndex, @Param(value = "clusterSize")Integer clusterSize,
                                     @Param(value = "bioType")int bioType, @Param(value = "algVersion")String algVersion);
}
