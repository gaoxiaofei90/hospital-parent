package com.llw.hospital.api;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.jcl.orm.tkmapper.BaseDtoExtendService;
import com.llw.hospital.dto.PersonDto;
import com.llw.hospital.dto.PersonModelDto;

/**
 * 生物特征库人员表
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-03-03 10:18:36
 */
public interface PersonService extends BaseDtoExtendService<PersonDto> {

	Long insertPerson(PersonDto person);

	boolean addModel(PersonModelDto bean);

	boolean editModel(PersonModelDto model);

	boolean delModel(Long modelId);

	boolean delPerson(Long personId);

	void batchImport(PersonDto person);

	/**
	 * 根据身份证号码查询人员信息，有此身份证信息则返回personId,否则添加此人信息
	 * @param person
	 * @return personId
	 */
	@Deprecated
	Long addPerson(PersonDto person);

	/**
	 * 根据身份证号码查询人员信息，有此身份证信息则返回personId,否则添加此人信息
	 * @param person
	 * @param forceUpdate 是否强制更新姓名
	 * @return personId
	 */
	Long addPerson(PersonDto person,boolean forceUpdate);


	/**
     * 对上传到后台的TXT文件中的人员信息插入到人员信息表中，根据身份证号码进行判断，有则不插入，无则插入到人员信息表中
     * @param list
     * @return
     */
	List<String[]> batchHandleTxt(List<String[]> list);

	
	
	Long addOrUpdateModel(PersonModelDto bean);
	
	//boolean specialPersonAddModel(PersonModelDto bean,List<String> materialList,String operatorPhoto);
	
	/**
	 * 
	* @Title: readPicFiles 
	* @Description: TODO 
	* @param Path
	* @param resultPath void
	* @author Administrator
	* @date 2019年11月29日下午11:14:40
	 */
	void readPicFiles(String Path, String resultPath);
	
	public PersonModelDto addOrUpdateModel(String idcard, String name,String photoAddress,int bioType);

	Long addModelByPerson(PersonModelDto bean);
}
