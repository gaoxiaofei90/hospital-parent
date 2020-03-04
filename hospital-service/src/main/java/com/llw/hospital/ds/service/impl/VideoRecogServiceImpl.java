package com.llw.hospital.ds.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jcl.orm.tkmapper.BaseDtoExtendServiceImpl;
import com.jcl.orm.tkmapper.LLwExample;
import com.llw.hospital.api.RecogService;
import com.llw.hospital.api.SysDictService;
import com.llw.hospital.api.VideoRecogService;
import com.llw.hospital.ds.entity.VideoRecog;
import com.llw.hospital.ds.mapper.VideoRecogMapper;
import com.llw.hospital.dto.SysDictDto;
import com.llw.hospital.dto.VideoRecogDto;
import com.llw.hospital.dto.VideoRecogExtendDto;
import com.llw.hospital.util.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Service(timeout = 3000)
public class VideoRecogServiceImpl extends BaseDtoExtendServiceImpl<VideoRecogDto, VideoRecog> implements VideoRecogService {

	@Autowired
	VideoRecogMapper videoRecogMapper;

//	@Autowired
//	PlanService planService;
	
//	@Autowired
//	SceneRecogService sceneRecogService;
	
	@Autowired
	RecogService recogService;
	
	@Autowired
	SysDictService sysDictService;
	
//	@Autowired
//	Medical3Service medical3Service;
	@Override
	public PageInfo getVideoRecogDtoList(VideoRecogExtendDto videoRecogExtendDto, Integer pageNo,
			Integer pageSize) {
		Map<String,Object> params = new HashMap<>();
        params.put("pageNo",pageNo);
        params.put("pageSize", pageSize);
        params.put("auditStatus",videoRecogExtendDto.getAuditStatus());
        params.put("keyword",videoRecogExtendDto.getKeyword());
        params.put("startTime",videoRecogExtendDto.getStartTime());
        params.put("endTime",videoRecogExtendDto.getEndTime());
        params.put("avaliableSysCodes",videoRecogExtendDto.getAvaliableSysCodes());
        params.put("avaliableZoneCode",videoRecogExtendDto.getAvaliableZoneCode());
        params.put("avaliableBusiTypes",videoRecogExtendDto.getAvaliableBusiTypes());
        params.put("departId",videoRecogExtendDto.getDepartId());
        params.put("userOrgId",videoRecogExtendDto.getUserOrgId());
        params.put("fromType",videoRecogExtendDto.getFromType());
        PageHelper.startPage(pageNo, pageSize);
        List<VideoRecogExtendDto> list = videoRecogMapper.getVideoRecogList(params);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
	}
	
	@Override
	public VideoRecogExtendDto getVideoRecogDto(Long videoId) {
		return videoRecogMapper.getVideoRecog(videoId);
	}

	@Override
	public VideoRecogDto recognize(VideoRecogDto bean) throws Exception  {
		VideoRecogDto newbean = this.insertAndGet(bean);
		if(newbean.getSceneId()!=null){
			//更新本次认证 同时在此时间内需要认证的任务都要更新 比如稽查 必须要新更新本次任务
			/*
			SceneRecogDto sceneRecogDto = sceneRecogService.selectByPrimaryKey(newbean.getSceneId());
			if(sceneRecogDto!=null){
				sceneRecogDto.setRecogMode(21);
				sceneRecogDto.setStatus(20);
				sceneRecogDto.setVideoId(newbean.getVideoId());
				sceneRecogDto.setRecogTime(newbean.getRecogTime());
				sceneRecogDto.setUpdateTime(new Date());
				sceneRecogService.updateByPrimaryKeySelective(sceneRecogDto);
			}
			
			SceneRecogDto sr = new SceneRecogDto();
			sr.setMedicalId(bean.getMedicalId());
			LLwExample lLwExample = new LLwExample(sr);
			Date now = new Date();
			lLwExample.andGreaterThanOrEqualTo("finishTime", now);
			lLwExample.andLessThanOrEqualTo("startTime", now);
			lLwExample.andCondition("  status in(10,12,20,22,30,32)");
	    	List<SceneRecogDto> sceneRecogList = sceneRecogService.selectByExampleInOrg(lLwExample);
	    	if(CollectionUtils.isNotEmpty(sceneRecogList)) {
	    		for(SceneRecogDto srd : sceneRecogList) {
	    			srd.setRecogMode(21);
	    			srd.setVideoId(newbean.getVideoId());
	    			srd.setStatus(20);
	    			srd.setRecogTime(newbean.getRecogTime());
	    			srd.setUpdateTime(new Date());
					sceneRecogService.updateByPrimaryKeySelective(srd);
	    		}
	    	}*/
			/*SceneRecogDto sceneRecogDto = sceneRecogService.selectByPrimaryKey(newbean.getSceneId());
			if(sceneRecogDto!=null){
				sceneRecogDto.setRecogMode(21);
				sceneRecogDto.setStatus(20);
				sceneRecogDto.setVideoId(newbean.getVideoId());
				sceneRecogService.updateByPrimaryKeySelective(sceneRecogDto);
			}*/
		}else {
			//t_recog 和 t_scene_Recog 数据回填  
		/*	if(newbean.getRecogBusi() == null || newbean.getRecogBusi() != 10) {
				recogService.updateRecogStatus(newbean.getVideoId(), "t_video_recog");
			}*/
			//recogService.updateRecogStatus(newbean.getVideoId(), "t_video_recog");
		}
		return newbean;
	}

//	@Override
//	public VideoRecogDto entrustPersonRecognize(VideoRecogDto bean, Medical3Dto medical3Dto) throws Exception {
//
//		VideoRecogDto newbean = this.insertAndGet(bean);
//		
//		Medical3Dto md = new Medical3Dto();
//		md.setMenterId(medical3Dto.getMenterId());
//		md.setRelativeId(medical3Dto.getRelativeId());
//		//md.setMenterId(menterDto.getMenterId());
//		md.setPersonId(medical3Dto.getPersonId());
//		LLwExample lLwExample = new LLwExample(md);
//		SysDictDto sysDictDto = new SysDictDto();
//		sysDictDto.setAppFieldName("menteTimeRange");
//		List<SysDictDto> list = sysDictService.selectList(sysDictDto);
//		Integer time = 5;//默认查询前后五分钟
//		if(CollectionUtils.isNotEmpty(list)) {
//			 time = Integer.parseInt(list.get(0).getFieldValue());
//		}
//		Date now = new Date();
//		lLwExample.andLessThanOrEqualTo("updateTime", DateUtils.addMinutes(now,time));
//    	lLwExample.andGreaterThanOrEqualTo("updateTime", DateUtils.addMinutes(now,-time));
//    	lLwExample.orderBy("updateTime").desc();
//    	List<Medical3Dto> medicalList = medical3Service.selectByExample(lLwExample);
//    	if(CollectionUtils.isNotEmpty(medicalList)) {
//    		//前后 time时间内有数据 则更新,如果有多条 选取最近一条
//    		Medical3Dto medical = medicalList.get(0);
//    		medical.setVideoId(newbean.getVideoId());
//    		medical.setUpdateTime(new Date());
//    		medical.setStatus(bean.getAuditStatus());
//    		md = medical;
//    		medical3Service.updateByPrimaryKeySelective(medical);
//    	}else {
//    		//添加数据
//    		
//    		medical3Dto.setVideoId(newbean.getVideoId());
//    		medical3Dto.setCreateTime(new Date());
//    		medical3Dto.setUpdateTime(new Date());
//    		medical3Dto.setStatus(bean.getAuditStatus());
//    		medical3Dto.setDataFrom(1);
//    		md = medical3Service.insertSelectiveAndGet(medical3Dto);
//    	}
//    	
//    	newbean.setSceneId(md.getMedicalId());
//		this.updateByPrimaryKeySelective(newbean);
//		return newbean;
//		
//	}

}


