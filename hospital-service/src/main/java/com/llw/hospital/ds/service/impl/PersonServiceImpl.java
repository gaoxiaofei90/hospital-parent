package com.llw.hospital.ds.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.NamedThreadFactory;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.util.StringUtil;
import com.jcl.dto.IdcardUtils;
import com.jcl.orm.tkmapper.BaseDtoExtendServiceImpl;
import com.llw.cache.broadcast.BroadcastUtil;
import com.llw.hospital.api.PersonDataService;
import com.llw.hospital.api.PersonFeaturesService;
import com.llw.hospital.api.PersonModelService;
import com.llw.hospital.api.PersonService;
import com.llw.hospital.api.RecogService;
import com.llw.hospital.api.SysAlgorithmService;
import com.llw.hospital.api.SysRecognizeService;
import com.llw.hospital.common.base.constants.BaseConstants;
import com.llw.hospital.ds.entity.Person;
import com.llw.hospital.ds.mapper.PersonDao;
import com.llw.hospital.dto.FeatureByte;
import com.llw.hospital.dto.PersonDto;
import com.llw.hospital.dto.PersonFeaturesDto;
import com.llw.hospital.dto.PersonModelDto;
import com.llw.hospital.dto.RecognizeResult;
import com.llw.hospital.event.ModelAddEvent;
import com.llw.hospital.event.ModelDelEvent;
import com.llw.hospital.util.DateUtils;
import com.llw.module.network.ftp.FtpUtil;
import common.Logger;


@Component
@Service(timeout = 3000)
public class PersonServiceImpl extends
		BaseDtoExtendServiceImpl<PersonDto, Person> implements PersonService {


	@Autowired
	SysAlgorithmService sysAlgorithmService;

	@Autowired
	PersonModelService personModelService;

	@Autowired
	PersonFeaturesService personFeaturesService;

	@Autowired
	RecogService recogService;
	
	@Autowired
	PersonDao personDao;

	@Autowired
	SysRecognizeService recognizeService;

	@Autowired
	private PersonService personService;

	@Autowired
	PersonDataService personDataService;
	
	@SuppressWarnings("AlibabaThreadPoolCreation")
	private ExecutorService executor = Executors.newCachedThreadPool(new NamedThreadFactory("PersonServiceImpl-executor", true));

	private static Logger logger = Logger.getLogger(PersonServiceImpl.class);


	@Override
	@Transactional
	public Long insertPerson(PersonDto person) {
		PersonDto where = new PersonDto();
		where.setName(person.getName());
		where.setIdcard(person.getIdcard());
		List<PersonDto> psList = this.selectList(where);
		if (psList == null || psList.size() == 0) {
			this.insert(person);
			person = this.selectOne(where);
		} else {
			person = psList.get(0);
			// throw new ServiceException("1001", "身份证号码已存在！");
		}
		return person.getPersonId();
	}

	@Override
	@Transactional
	public boolean addModel(PersonModelDto model) {
		// 提取特征信息
		List<String> picUrls = new ArrayList<String>();
		picUrls.add(model.getModelAddress());
		FeatureByte featureByte = sysAlgorithmService.extractFeatures(picUrls, 11);

		// 添加人员模板信息
		Long modelId = personModelService.insertModel(model);

		// 人员 特征信息
		PersonFeaturesDto features = new PersonFeaturesDto();
		features.setModelId(modelId);
		features.setPersonId(model.getPersonId());
		features.setBioType(11);
		features.setMajorVersion(featureByte.getAlgVersion());
		features.setFeatures(featureByte.getFeatures().get(0));
		features.setCreateTime(new Date());
		features.setStatus(10);
		personFeaturesService.insert(features);

		// 修改人员头像
		PersonDto person = this.selectByPrimaryKey(model.getPersonId());
		if (StringUtil.isNotEmpty(model.getIdcardAddress())) {
			person.setIdcardPhoto(model.getIdcardAddress());
			person.setHeadPhoto(model.getIdcardAddress());
		} else {
			person.setIdcardPhoto(model.getModelAddress());
			person.setHeadPhoto(model.getModelAddress());
		}

		person.setFaceExist(1L);
		this.updateByPrimaryKeySelective(person);

		ModelAddEvent modelAddEvent = new ModelAddEvent(this, model.getPersonId());
		BroadcastUtil.boradCast(modelAddEvent);

		return true;
	}

	@Override
	@Transactional
	public boolean delModel(Long modelId) {
		PersonModelDto model = new PersonModelDto();
		model.setModelId(modelId);
		Long personId = personModelService.selectOne(model).getPersonId();

		PersonFeaturesDto where = new PersonFeaturesDto();
		where.setModelId(modelId);
		personFeaturesService.delete(where);
		personModelService.deleteByPrimaryKey(modelId);

		model.setModelId(null);
		model.setPersonId(personId);
		PersonModelDto pModel = personModelService.selectOne(model, "create_time desc");
		String photo = null;
		if (pModel != null) {
			photo = pModel.getModelAddress();
		}

		PersonDto person = this.selectByPrimaryKey(personId);
		person.setHeadPhoto(photo);
		person.setIdcardPhoto(photo);//add 20191017 
		this.updateByPrimaryKey(person);

		ModelDelEvent modelDelEvent = new ModelDelEvent(this, personId);
		BroadcastUtil.boradCast(modelDelEvent);
		return true;
	}

	@Override
	@Transactional
	public boolean editModel(PersonModelDto model) {
		personModelService.updateByPrimaryKey(model);

		PersonFeaturesDto where = new PersonFeaturesDto();
		where.setModelId(model.getModelId());
		List<PersonFeaturesDto> list = personFeaturesService.selectList(where);
		for (PersonFeaturesDto m : list) {
			m.setStatus(model.getStatus());
			personFeaturesService.updateByPrimaryKey(m);
		}

		return true;
	}

	@Override
	@Transactional
	public boolean delPerson(Long personId) {

		PersonFeaturesDto where1 = new PersonFeaturesDto();
		where1.setPersonId(personId);
		personFeaturesService.delete(where1);

		PersonModelDto where2 = new PersonModelDto();
		where2.setPersonId(personId);
		personModelService.delete(where2);

		PersonDto p = new PersonDto();
		p.setPersonId(personId);
		this.delete(p);

		return true;
	}

	@Override
	public void batchImport(PersonDto person) {
		Long personId = this.insertPerson(person);
		PersonModelDto model = new PersonModelDto();
		model.setPersonId(personId);
		model.setBioType(11);
		model.setModelAddress(person.getHeadPhoto());
		model.setCreateType(14);
		model.setCreateTime(new Date());
		model.setStatus(10);
		this.addModel(model);

	}

	


	@Override
	@Transactional
	public Long addPerson(PersonDto person) {
		return addPerson(person, false);
	}

	@Override
	@Transactional
	public Long addPerson(PersonDto person, boolean forceUpdate) {
		PersonDto where = new PersonDto();
		where.setIdcard(person.getIdcard());
		where.setName(person.getName());
		List<PersonDto> psList = this.selectList(where);
		if (psList == null || psList.size() == 0) {
			this.insert(person);
			person = this.selectOne(where);
		} else {
			if (forceUpdate) {
				PersonDto personDto = psList.get(0);
				Long personId = personDto.getPersonId();
				BeanUtils.copyProperties(person, personDto);
				personDto.setPersonId(personId);
				updateByPrimaryKeySelective(personDto);
				return personId;
			} else {
				person = psList.get(0);
			}
		}
		return person.getPersonId();
	}

	@Override
	public List<String[]> batchHandleTxt(List<String[]> list) {


		List<String[]> error = new ArrayList<String[]>();
		String[] errArray = new String[1];//记录每条错误记录原因
		List<PersonDto> personList = new ArrayList<PersonDto>();

		for (int i = 0; i < list.size(); i++) {

			if (i == 0) { //对标题栏(行)进行过滤
				continue;
			}

			try {

				String[] array = list.get(i);
				String name = array[0].replace(" ", "");
				String idcard = array[1].replace(" ", "");
				String sicard = "";//社保卡号
				if (array.length >= 3) {
					sicard = array[2].replace(" ", "");
				}
				if (!StringUtils.isEmpty(idcard) && !StringUtils.isEmpty(name)) {
					if (IdcardUtils.validateIdCard18(idcard)) {//判断合身份证号是否合法合
						PersonDto personDto = new PersonDto();
						personDto.setName(name);
						personDto.setIdcard(idcard);
						personDto.setPersonType("1");//默认为医保人

						try {
							String birthdayStr = IdcardUtils.getBirthByIdCard(idcard);
							birthdayStr = birthdayStr.substring(0, 4) + "-" + birthdayStr.substring(4, 6) + "-" + birthdayStr.substring(6, 8);
							if (IdcardUtils.getGenderByIdCard(idcard) == 1) {
								personDto.setSex("1");//默认性别为男
							} else if (IdcardUtils.getGenderByIdCard(idcard) == 0) {
								personDto.setSex("2");
							}
							Date birthday = DateUtils.longSdf.parse(birthdayStr + " 00:00:00");
							personDto.setBirthday(birthday);
						} catch (Exception e) {
							e.printStackTrace();
						}

						if (!StringUtils.isEmpty(sicard)) {
							personDto.setSicard(sicard);
						}

						personList.add(personDto);
						/*
						PersonDto where = new PersonDto();
						where.setIdcard(idcard);
						where = this.selectOne(where);
						if(where != null){
							errArray[0] = "此身份证号已经存在于人员信息表中";
             				array = Arrays.copyOf(array, array.length + 1);
             				System.arraycopy(errArray, 0, array, array.length - 1 , 1);
             				error.add(array);
			            	logger.debug("此身份证号已经存在于人员信息表中");
						}else{
							int susFlag = this.insertSelective(personDto);
							if (susFlag > 0) {
 				            	logger.debug("人员信息保存成功");
 				            }else{
 				            	errArray[0] = "人员信息保存失败";
 	             				array = Arrays.copyOf(array, array.length + 1);
 	             				System.arraycopy(errArray, 0, array, array.length - 1 , 1);
 	             				error.add(array);
 				            	logger.debug("人员信息保存失败");
 				            }
						}*/

					} else {
						errArray[0] = "身份证号不合法";
						array = Arrays.copyOf(array, array.length + 1);
						System.arraycopy(errArray, 0, array, array.length - 1, 1);
						error.add(array);
						logger.debug("身份证号不合法！");
					}
				} else {
					errArray[0] = "格式有误或者必填项为空自行检查";
					array = Arrays.copyOf(array, array.length + 1);
					System.arraycopy(errArray, 0, array, array.length - 1, 1);
					error.add(array);
					logger.debug("格式有误或者必填项为空自行检查");
				}
			} catch (Exception e) {
				errArray[0] = "处理异常，请查看后台日志";
				String[] tmpArray = list.get(i);
				tmpArray = Arrays.copyOf(tmpArray, tmpArray.length + 1);
				System.arraycopy(errArray, 0, tmpArray, tmpArray.length - 1, 1);
				error.add(tmpArray);
				e.printStackTrace();
			}
		}

		//当数据过多时，这儿开启多线程进行处理
		if (personList.size() > 0) {

			executor.submit(new Runnable() {
				@Override
				public void run() {
					try {
						for (PersonDto person : personList) {
							AddPersonList(person);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});
		}

		return error;


	}

	

	public void AddPersonList(PersonDto personDto) {

		PersonDto where = new PersonDto();
		where.setIdcard(personDto.getIdcard());
		where = this.selectOne(where);
		if (where == null) {
			int susFlag = this.insertSelective(personDto);
			if (susFlag > 0) {
				logger.debug("人员信息保存成功:" + personDto.getIdcard());
			} else {
				logger.debug("人员信息保存失败:" + personDto.getIdcard());
			}
		} else {
			logger.debug("人员信息已经存在：" + personDto.getIdcard());
		}

	}


	@Override
	@Transactional
	public Long addOrUpdateModel(PersonModelDto model) {
		// 提取特征信息
		List<String> picUrls = new ArrayList<String>();
		picUrls.add(model.getModelAddress());
		FeatureByte featureByte = sysAlgorithmService.extractFeatures(picUrls, 11);

		PersonModelDto personModelDto = new PersonModelDto();
		personModelDto.setPersonId(model.getPersonId());
		List<PersonModelDto> personModelDtoList = personModelService.selectList(personModelDto);
		Long modelId = -1L;
		if (CollectionUtils.isNotEmpty(personModelDtoList)) {
			//已有模板则更新
			for (PersonModelDto pmd : personModelDtoList) {
				pmd.setModelAddress(model.getModelAddress());
				pmd.setCreateTime(new Date());
				personModelService.updateByPrimaryKeySelective(pmd);
				modelId = pmd.getModelId();

				PersonFeaturesDto personFeaturesDto = new PersonFeaturesDto();
				personFeaturesDto.setModelId(pmd.getModelId());
				List<PersonFeaturesDto> personFeaturesDtoList = personFeaturesService.selectList(personFeaturesDto);
				if (CollectionUtils.isNotEmpty(personFeaturesDtoList)) {
					//有特征 更新
					for (PersonFeaturesDto pfd : personFeaturesDtoList) {
						pfd.setPersonId(model.getPersonId());
						pfd.setMajorVersion(featureByte.getAlgVersion());
						pfd.setFeatures(featureByte.getFeatures().get(0));
						pfd.setCreateTime(new Date());
						personFeaturesService.updateByPrimaryKeySelective(pfd);
					}
				} else {
					//无特征 新增
					// 人员 特征信息
					PersonFeaturesDto features = new PersonFeaturesDto();
					features.setModelId(pmd.getModelId());
					features.setPersonId(model.getPersonId());
					features.setBioType(11);
					features.setMajorVersion(featureByte.getAlgVersion());
					features.setFeatures(featureByte.getFeatures().get(0));
					features.setCreateTime(new Date());
					features.setStatus(10);
					personFeaturesService.insert(features);
				}
			}
		} else {
			// 添加人员模板信息
			modelId = personModelService.insertModel(model);

			//添加人员 特征信息
			PersonFeaturesDto features = new PersonFeaturesDto();
			features.setModelId(modelId);
			features.setPersonId(model.getPersonId());
			features.setBioType(11);
			features.setMajorVersion(featureByte.getAlgVersion());
			features.setFeatures(featureByte.getFeatures().get(0));
			features.setCreateTime(new Date());
			features.setStatus(10);
			personFeaturesService.insert(features);
		}

		// 修改人员头像
		PersonDto person = this.selectByPrimaryKey(model.getPersonId());
		if (StringUtil.isNotEmpty(model.getIdcardAddress())) {
			person.setIdcardPhoto(model.getIdcardAddress());
			person.setHeadPhoto(model.getIdcardAddress());
		} else {
			person.setIdcardPhoto(model.getModelAddress());
			person.setHeadPhoto(model.getModelAddress());
		}

		person.setFaceExist(1L);
		this.updateByPrimaryKeySelective(person);

		ModelAddEvent modelAddEvent = new ModelAddEvent(this, model.getPersonId());
		BroadcastUtil.boradCast(modelAddEvent);
		return modelId;
	}

	@Override
	public void readPicFiles(String Path, String resultPath) {

		File parentPath = new File(Path);
		if (parentPath.exists()) {
			logger.error("pic Path is  exist :" + Path);
			File[] list = parentPath.listFiles();
			executor.submit(new Runnable() {
				@Override
				public void run() {
					AddModelByIdCard(list, resultPath);
				}
			});
		} else {
			logger.error("pic Path not exist :" + Path);
		}

	}


	/**
	 * @param list
	 * @param resultPath void
	 * @Title: AddModelByIdCard
	 * @Description: 根据身份证号码添加模板照
	 * @author Administrator
	 * @date 2019年11月30日下午7:37:21
	 */
	public void AddModelByIdCard(File[] list, String resultPath) {

		List<String> resultList = new ArrayList<String>();
		long lSuccess = 0, lError = 0;

		for (File file : list) {

			String idcard = file.getName();
			try {

				if (!idcard.toLowerCase().endsWith(".jpg")) {
					resultList.add(idcard + " :文件格式错误 ");
					logger.error(idcard + " :文件格式错误 ");
					lError++;
					continue;
				}

				idcard = idcard.substring(0, idcard.lastIndexOf(".")).replace(" ", "");
				if (!IdcardUtils.validateIdCard18(idcard)) {
					logger.error("身份证号码不正确:" + idcard);
					resultList.add("身份证号码不正确:" + idcard);
					lError++;
					continue;
				}

				PersonDto personDto = new PersonDto();
				personDto.setIdcard(idcard);
				PersonDto person = this.selectOne(personDto);

				if (person != null) {

					logger.error("-----start send pic to Ftp Server----");
					String picFile = FtpUtil.getInstance(BaseConstants.MODULE_NAME).upload(file, "model");
					logger.error("-----end send pic to Ftp Server----" + picFile);
					//照片上传到FTP服务器成功
					if (!StringUtils.isBlank(picFile)) {
						PersonModelDto where = new PersonModelDto();
						where.setPersonId(person.getPersonId());
						PersonModelDto personModel = personModelService.selectOne(where);//查询模板
						RecognizeResult recogResult = null;
						boolean recogFlag = false;
						if (personModel != null) {//此人已经有模板存在
							List<String> picArrays = new ArrayList<String>();
							picArrays.add(picFile);
							recogResult = recognizeService.recognize1(person.getPersonId(), picArrays, 11, null);
							//判断是否认证成功
							if (recogResult != null) {
								if (!recogResult.isSuccess()) {//比对失败直接跳出循环
									logger.error("此身份证号码对应的图片认证不通过:" + idcard);
									resultList.add("此身份证号码对应的图片认证不通过:" + idcard);
									lError++;
									continue;
								} else {
									recogFlag = true;
								}
							} else {
								logger.error("此身份证号码对应的图片认证不通过:" + idcard);
								resultList.add("此身份证号码对应的图片认证不通过:" + idcard);
								lError++;
								continue;
							}
						} else {//此人目前没有模板存在则直接建模
							recogFlag = true;
						}

						if (recogFlag) {

							PersonModelDto model = new PersonModelDto();
							model.setPersonId(person.getPersonId());
							model.setBioType(11);
							model.setModelAddress(picFile);
							model.setCreateType(14);
							model.setCreateTime(new Date());
							model.setStatus(10);
							logger.error("-----start addModel :----" + idcard);
							boolean modelFlag = personService.addModel(model);
							logger.error("-----end addModel :----" + idcard);
							if (!modelFlag) {
								logger.error("此身份证号码建模失败:" + idcard);
								resultList.add("此身份证号码建模失败:" + idcard);
								lError++;
								continue;
							}
							logger.error("此身份证号码建模成功:" + idcard);
							lSuccess++;
						}


					} else {//照片上传到FTP服务器失败
						logger.error("此身份证号码对应的照片上传到FTP服务器失败:" + idcard);
						resultList.add("此身份证号码对应的照片上传到FTP服务器失败:" + idcard);
						lError++;
						continue;
					}
				} else {
					logger.error("此身份证号码未在系统登记人员信息:" + idcard);
					resultList.add("此身份证号码未在系统登记人员信息:" + idcard);
					lError++;
					continue;
				}


			} catch (Exception ex) {
				logger.error("add model pic error:", ex);
				//ex.printStackTrace();
				resultList.add(idcard + " : " + ex.getMessage());
				lError++;
			}
		}


		resultList.add(0, "成功导入：" + lSuccess + "人，失败：" + lError + "人");
		if (lError > 0) {
			resultList.add(1, "失败的人员列表在目录下查看：" + resultPath);
		}


		FileWriter writer = null;
		BufferedWriter out = null;
		try {

			File writeName = new File(resultPath);
			writeName.createNewFile();
			writer = new FileWriter(writeName);
			out = new BufferedWriter(writer);
			for (String s : resultList) {
				out.write(s + "\r\n");
			}
			out.flush();// 把缓存区内容压入文件
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				if (out != null) {
					out.close();  // 最后记得关闭文件
					if (writer != null) {
						writer.close(); // 最后记得关闭文件
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	@Transactional
	public PersonModelDto addOrUpdateModel(String idcard, String name, String photoAddress, int bioType) {
		//有身份证和姓名 就可以去建模 或者更新模板
		PersonDto where = new PersonDto();
		where.setIdcard(idcard);
		where.setName(name);
		PersonDto personInfo = personService.selectOne(where);

		PersonModelDto pmd = null;
		//先新增人员基本信息
		if (personInfo == null) {
			String birthday = idcard.substring(6, 10) + "-" + idcard.substring(10, 12) + "-" + idcard.substring(12, 14);
			String sex = Integer.parseInt(idcard.substring(16, 17)) % 2 == 0 ? "2" : "1";

			PersonDto personDto = new PersonDto();
			personDto.setName(name);
			personDto.setIdcard(idcard);
			personDto.setSex(sex);
			personDto.setIdcardPhoto(photoAddress);
			personDto.setHeadPhoto(photoAddress);
			personDto.setBirthday(DateUtils.string2Date(birthday));
			personInfo = personService.insertSelectiveAndGet(personDto);
			pmd = new PersonModelDto();
			pmd.setPersonId(personInfo.getPersonId());

		} else {
			pmd = new PersonModelDto();
			pmd.setPersonId(personInfo.getPersonId());
		}

		if (personInfo != null && StringUtil.isNotEmpty(photoAddress)) {
			PersonModelDto model = new PersonModelDto();
			model.setPersonId(personInfo.getPersonId());
			model.setBioType(bioType);

			model.setModelAddress(photoAddress);
			model.setIdcardAddress(photoAddress);
			model.setCreateType(11);
			model.setCreateTime(new Date());
			model.setStatus(10);
			Long res = this.addOrUpdateModel(model);
			if (res > 0) {
				pmd = new PersonModelDto();
				pmd.setPersonId(personInfo.getPersonId());
				pmd.setModelId(res);
			}
		}
		return pmd;
	}

	@Override
	public Long addModelByPerson(PersonModelDto model) {
		// 提取特征信息
		Long modelId = -1L;
		List<String> picUrls = new ArrayList<String>();
		picUrls.add(model.getModelAddress());
		FeatureByte featureByte = sysAlgorithmService.extractFeatures(picUrls, 11);

		// 添加人员模板信息
		modelId = personModelService.insertModel(model);

		// 人员 特征信息
		PersonFeaturesDto features = new PersonFeaturesDto();
		features.setModelId(modelId);
		features.setPersonId(model.getPersonId());
		features.setBioType(11);
		features.setMajorVersion(featureByte.getAlgVersion());
		features.setFeatures(featureByte.getFeatures().get(0));
		features.setCreateTime(new Date());
		features.setStatus(10);
		personFeaturesService.insert(features);

		// 修改人员头像
		PersonDto person = this.selectByPrimaryKey(model.getPersonId());
		if (StringUtil.isNotEmpty(model.getIdcardAddress())) {
			person.setIdcardPhoto(model.getIdcardAddress());
			person.setHeadPhoto(model.getIdcardAddress());
		} else {
			person.setIdcardPhoto(model.getModelAddress());
			person.setHeadPhoto(model.getModelAddress());
		}

		person.setFaceExist(1L);
		this.updateByPrimaryKeySelective(person);

		ModelAddEvent modelAddEvent = new ModelAddEvent(this, model.getPersonId());
		BroadcastUtil.boradCast(modelAddEvent);

		return modelId;
	}


}
