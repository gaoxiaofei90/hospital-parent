package com.llw.hospital.api.vo.person;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="入院登记信息",description="入院登记信息")
public class HospitalRegistrationInfo {

	@ApiModelProperty(value = "姓名")
    private String name;
	
	@ApiModelProperty(value = "床号")
    private String bedNo;
	
	@ApiModelProperty(value = "诊断/疾病名称")
    private String diagnosis;
	
	@ApiModelProperty(value = "身份证号码")
    private String idcard;
	
	@ApiModelProperty(value = "就诊号/住院号")
    private String medicalNo;
	
	@ApiModelProperty(value = "入院时间")
    private Long inDate;
	
	@ApiModelProperty(value = "科室")
    private Long departId;

	@ApiModelProperty(value = "身份证照，图片 base64编码")
	private String idcardPhoto;
	
	
	@ApiModelProperty(value = "参保统筹区（1本省、2本市、3外省、4省本级）")
	private Integer personFrom;
	
	@ApiModelProperty(value = "险种/业务（310城镇职工医保、390城乡居民医保）")
	private String busiType;
	
	@ApiModelProperty(value = "人员类别（1医保人员、2非医保人员")
	private String personType;
	
	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  
    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getMedicalNo() {
        return medicalNo;
    }

    public void setMedicalNo(String medicalNo) {
        this.medicalNo = medicalNo;
    }

    public Long getInDate() {
        return inDate;
    }

    public void setInDate(Long inDate) {
        this.inDate = inDate;
    }

   
    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
    }

	public String getIdcardPhoto() {
		return idcardPhoto;
	}

	public void setIdcardPhoto(String idcardPhoto) {
		this.idcardPhoto = idcardPhoto;
	}

	public Integer getPersonFrom() {
		return personFrom;
	}

	public void setPersonFrom(Integer personFrom) {
		this.personFrom = personFrom;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}
    
    
}
