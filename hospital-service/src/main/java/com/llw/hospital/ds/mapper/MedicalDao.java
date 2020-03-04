package com.llw.hospital.ds.mapper;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.jcl.orm.tkmapper.BaseMapper;
import com.llw.hospital.ds.entity.Medical;
import com.llw.hospital.vo.medical.ChangeHospitalPersonVo;
import com.llw.hospital.vo.medical.InHospitalPersonVo;

/**
 * 住院/就诊/门特/购药信息
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-03-03 10:18:37
 */
public interface MedicalDao extends BaseMapper<Medical> {

    /**
     * 获取当前在院的人员
     * @param clusterIndex
     * @param clusterSize
     * @return
     */
    @Select(
            "SELECT A.PERSON_ID,A.ORG_ID FROM T_MEDICAL A WHERE A.STATUS=1 AND ora_hash(person_id,#{clusterSize}-1) = #{clusterIndex} order by MEDICAL_ID"
    )
    List<InHospitalPersonVo> getInHospitalPerson(@Param(value = "clusterIndex") Integer clusterIndex, @Param(value = "clusterSize")Integer clusterSize);


    /**
     * 获取所有已出院人员
     * @param clusterIndex
     * @param clusterSize
     * @return
     */
    @Select(
            "SELECT A.PERSON_ID,A.ORG_ID FROM T_MEDICAL A WHERE A.STATUS=2 and update_time >= #{date} AND ora_hash(person_id,#{clusterSize}-1) = #{clusterIndex} order by MEDICAL_ID"
    )
    List<InHospitalPersonVo> getOutHospitalPerson(@Param(value = "date") Date date,@Param(value = "clusterIndex") Integer clusterIndex, @Param(value = "clusterSize")Integer clusterSize);


    /**
     * 获取所有已出院人员
     * @param clusterIndex
     * @param clusterSize
     * @return
     */
    @Select(
            "SELECT A.PERSON_ID,A.ORG_ID FROM T_MEDICAL_${medicalType} A WHERE update_time >= #{date} AND ora_hash(person_id,#{clusterSize}-1) = #{clusterIndex} order by MEDICAL_ID"
    )
    List<InHospitalPersonVo> getHistoryPerson(@Param(value = "date") Date date,@Param(value = "medicalType") int medicalType,@Param(value = "clusterIndex") Integer clusterIndex, @Param(value = "clusterSize")Integer clusterSize);


    /**
     * 获取指定时间点之后，状态有变化的人员
     * @param date
     * @param clusterIndex
     * @param clusterSize
     * @return
     */
    @Select(
            "SELECT A.PERSON_ID,A.ORG_ID,A.STATUS,A.IN_DATE,A.OUT_DATE FROM T_MEDICAL A WHERE update_time >= #{date} AND ora_hash(person_id,#{clusterSize}-1) = #{clusterIndex} order by MEDICAL_ID"
    )
    List<ChangeHospitalPersonVo> getChangedHospitalPerson(@Param(value = "date") Date date, @Param(value = "clusterIndex") Integer clusterIndex, @Param(value = "clusterSize")Integer clusterSize);

    /**
     * 获取指定时间之后办理过业务的人
     * @param date
     * @param clusterIndex
     * @param clusterSize
     * @return
     */
    @Select(
            "SELECT A.PERSON_ID,A.ORG_ID FROM T_MEDICAL_${medicalType} A WHERE create_time >= #{date} AND ora_hash(person_id,#{clusterSize}-1) = #{clusterIndex} order by MEDICAL_ID"
    )
    List<InHospitalPersonVo> getBusiPerson(@Param(value = "date") Date date,@Param(value = "medicalType") Integer medicalType, @Param(value = "clusterIndex") Integer clusterIndex, @Param(value = "clusterSize")Integer clusterSize);


    @Select(
            "select OBJ_ID from T_BLACKLIST_PERSON where OBJ_TYPE in (1,2) and STATUS = 1 and create_time >= #{date} AND ora_hash(OBJ_ID,#{clusterSize}-1) = #{clusterIndex} order by OBJ_ID"
    )
    List<Long> getBlackListPersons(@Param(value = "date") Date date, @Param(value = "clusterIndex") Integer clusterIndex, @Param(value = "clusterSize")Integer clusterSize);
}
