package com.llw.hospital.ds.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;

import com.llw.hospital.ds.sqlprovide.MedicalSqlProvide;
import com.llw.hospital.dto.MedicalDto;
import com.llw.hospital.dto.MedicalExtendDto;

public interface MedicalMapper {
		@SelectProvider(type = MedicalSqlProvide.class,method = "getMedicalList")
		List<MedicalDto> getMedicalList(Map<String, Object> params);
		
		
		@SelectProvider(type = MedicalSqlProvide.class,method = "medicalCount")
		List<MedicalExtendDto> medicalCount(Map<String, Object> params);
		
		@SelectProvider(type = MedicalSqlProvide.class,method = "getPersonId")
		Long getPersonId(Map<String, Object> params);
		
		@SelectProvider(type = MedicalSqlProvide.class,method = "getPerson")
		List<MedicalDto> getPerson();
		
		@SelectProvider(type = MedicalSqlProvide.class,method = "update")
		void update(Long medicalId);
		
		@SelectProvider(type = MedicalSqlProvide.class,method = "updateStatus")
		void updateStatus(Long medicalId);
		
		
		@SelectProvider(type = MedicalSqlProvide.class,method = "medicalHistoryList")
		List<MedicalExtendDto> medicalHistoryList(Map<String, Object> params);
		
		
		@SelectProvider(type = MedicalSqlProvide.class,method = "getInhospital")
		List<MedicalExtendDto> GetInhospital(Map<String ,Object> params);
	
		
		@SelectProvider(type = MedicalSqlProvide.class,method = "getPersonbyStatus")
		List<MedicalExtendDto> GetPersonbyStatus(Map<String ,Object> params);
		
		
		@SelectProvider(type = MedicalSqlProvide.class,method = "getPersonbyStatus")
		List<MedicalExtendDto> GetInHospitalInfo(Map<String ,Object> params);
		
		
		@SelectProvider(type = MedicalSqlProvide.class,method = "getInhospitaPersonList")
		List<MedicalExtendDto> GetInhospitaPersonList(Map<String ,Object> params);
		
		@SelectProvider(type = MedicalSqlProvide.class,method = "getMedical1List")
		List<MedicalExtendDto> getMedical1List(Map<String ,Object> params);
		
		@SelectProvider(type = MedicalSqlProvide.class,method = "getMedical2List")
		List<MedicalExtendDto> getMedical2List(Map<String ,Object> params);
		
		@SelectProvider(type = MedicalSqlProvide.class,method = "getMedical3List")
		List<MedicalExtendDto> getMedical3List(Map<String ,Object> params);
		
		@SelectProvider(type = MedicalSqlProvide.class,method = "getMedical4List")
		List<MedicalExtendDto> getMedical4List(Map<String ,Object> params);
		
		@SelectProvider(type = MedicalSqlProvide.class,method = "getMedicalIndexDetailList")
		List<MedicalExtendDto> getMedicalIndexDetailList(Map<String ,Object> params);
		
		@SelectProvider(type = MedicalSqlProvide.class,method = "getOutpatPharmacyDetailList")
		List<MedicalExtendDto> getOutpatPharmacyDetailList(Map<String ,Object> params);
		
		@SelectProvider(type = MedicalSqlProvide.class,method = "getOutHospitalPersonRecord")
		List<MedicalExtendDto> getOutHospitalPersonRecord(Map<String, Object> params);
		
		@SelectProvider(type = MedicalSqlProvide.class,method = "getInHosPersonList")
		List<MedicalDto> getInHosPersonList(Map<String ,Object> params);
		
		@SelectProvider(type = MedicalSqlProvide.class,method = "getPersonListByLoginUser")
		List<MedicalExtendDto> getPersonListByLoginUser(Map<String ,Object> params);
		
}
