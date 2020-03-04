package com.llw.hospital.api;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.jcl.orm.tkmapper.BaseDtoExtendService;
import com.llw.hospital.ds.entity.LoginUser;
import com.llw.hospital.dto.MedicalDto;
import com.llw.hospital.dto.MedicalExtendDto;

/**
 * 住院/就诊信息
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-03-03 10:18:37
 */
public interface MedicalService extends BaseDtoExtendService<MedicalDto> {

    /**
     * 获取本机构对应业务的所有人员的特征
     * @param page
     * @param size
     * @param orgId
     * @param busiTypes
     * @return
     */
   // PageInfo<InHospitalPersonFeatureVo> selectInHostpitalFeature(int page, int size, Long orgId, Set<Integer> busiTypes) throws Exception;
    
    /**
     * 通过姓名身份证获取人员ID
     * @return
     */
    Long getPersonId(String name,String idCard);
    
    /**
     * 通过姓名身份证获取人员ID
     * @return
     */
    void update(Long medicalId);
    
    public PageInfo getMedicalList(Integer pageNo,Integer pageSize,String keyword,Integer medicalType,Long[] personArr,Integer blackAndWhite,String label,String zoonCode);

    
    public Integer medicalCount(Long orgBeginId,Long orgEndId);
    
    //public PageInfo<MedicalExtendDto> medicalHistoryList(MedicalExtendDto dto, Integer pageNo, Integer pageSize);
    
    
    /**
     * 库---在院人员一级页面查询
     * @param dto
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo getInhospital(MedicalExtendDto dto, Integer pageNo, Integer pageSize);
    
    
    /**
     * 库----在院人员二级页面查询
     * @param dto
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo getInhospitaPersonList(MedicalExtendDto dto, Integer pageNo, Integer pageSize);
    /**
     * 在院人员二级页面查询--有入院信息人员
     * @param dto
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo GetPersonbyStatus(MedicalExtendDto dto, Integer pageNo, Integer pageSize);
     
    
    /**
     * 函数功能：将excel文件中的记录插入DE_KC21表中
     * @param list
     * @return
     */
    //public ExcelDto  handleExcelFileToDe_kc21(List<String[]> list);

    /**
     * 住院登记
     * @param medicalDto
     * @return
     */
    boolean registration(MedicalDto medicalDto);
    
    /**
     * 场景住院-一级页面
     * @param dto
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo getMedical1List(MedicalExtendDto dto, Integer pageNo, Integer pageSize);
    
    /**
     * 场景-住院-二级页面
     * @param dto
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo getMedicalIndexDetailList(MedicalExtendDto dto, Integer pageNo, Integer pageSize);
    
    /**
     * 场景-门诊、门特、购药-二级页面
     * @param dto
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo getOutpatPharmacyDetailList(MedicalExtendDto dto, Integer pageNo, Integer pageSize);
    
    /**
     * 出院人员记录
     * @param dto
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo getOutHospitalPersonRecord(MedicalExtendDto dto, Integer pageNo, Integer pageSize);
    
    /**
     * 取消出院-修改状态
     * @param medicalId
     */
    void updateStatus(Long medicalId);
    
    /**
     * 医院端---根据条件查询查询在院人员
     * @param dto
     * @return
     */
    public List<MedicalDto> getInHosPersonList(MedicalExtendDto dto); 
    
    public String hospitalRegistration(MedicalDto dto,LoginUser loginUser) ;
}

