package com.llw.hospital.api.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.jcl.orm.tkmapper.LLwExample;
import com.llw.hospital.api.MsgService;
import com.llw.hospital.api.shiro.JWTFilter;
import com.llw.hospital.api.vo.BasePageRequest;
import com.llw.hospital.api.vo.ResponseParam;
import com.llw.hospital.api.vo.login.MsgResponse;
import com.llw.hospital.common.base.constants.BaseConstants;
import com.llw.hospital.ds.entity.LoginUser;
import com.llw.hospital.dto.MsgDto;

@RestController
@RequestMapping("/api/msg")
public class MyMsgController {

	@Autowired
	MsgService msgService;

	@PostMapping("/getMsgAmount")
	@ApiOperation(value = "获得新消息条数", notes = "获得新消息条数",tags = "消息相关接口列表")
	@ApiImplicitParams(@ApiImplicitParam(name = "auth", value = "access token，登录接口返回的", required = true, dataType = "String", paramType = "header"))
	@ApiResponses({ @ApiResponse(code = 200, message = "执行成功"),@ApiResponse(code = 400, message = "系统异常") })
	public ResponseParam<Integer> getMsgAmount() {
		MsgDto where = new MsgDto();
		where.setStatus(0);
		LLwExample example;
		int amount = 0;
		try {
			example = new LLwExample(where);
			LoginUser loginUser = JWTFilter.getCurrentUser();
			if(null != loginUser.getOrgCategory() && loginUser.getOrgCategory().intValue() == BaseConstants.ORG_CATEGORY_INSTITUTION) {
				//中心端用户 获取消息 要获取operateType 为3的
				example.andCondition(" operate_type = '3' ");
			}else if(null != loginUser.getOrgCategory() && (loginUser.getOrgCategory().intValue() == BaseConstants.ORG_CATEGORY_HOSPITAL || loginUser.getOrgCategory().intValue() == BaseConstants.ORG_CATEGORY_department)){
				example.andCondition(" operate_type in ('1','2')  ");
			}else {
				return ResponseParam.ok().setData(amount);
			}
			List<MsgDto> l = msgService.selectByExampleInOrg(example);
			if(CollectionUtils.isNotEmpty(l)) {
				amount = l.size();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ResponseParam.ok().setData(amount);
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/msgList")
	@ApiOperation(value = "消息列表", notes = "消息列表",tags = "消息相关接口列表")
	@ApiImplicitParams(@ApiImplicitParam(name = "auth", value = "access token，登录接口返回的", required = true, dataType = "String", paramType = "header"))
	@ApiResponses({ @ApiResponse(code = 200, message = "执行成功"),
			@ApiResponse(code = 400, message = "系统异常") })
	public @ApiParam ResponseParam<List<MsgResponse>> msgList(@RequestBody @ApiParam BasePageRequest request) {
		List<MsgResponse> retList = new ArrayList<MsgResponse>();
		try {
			LoginUser loginUser = JWTFilter.getCurrentUser();
			MsgDto where = new MsgDto();
			
			
			where.setStatus(0);
			LLwExample example = new LLwExample(where);
			if(null != loginUser.getOrgCategory() && loginUser.getOrgCategory().intValue() == BaseConstants.ORG_CATEGORY_INSTITUTION) {
				//中心端用户 获取消息 要获取operateType 为3的
				example.andCondition(" operate_type = '3' ");
			}else if(null != loginUser.getOrgCategory() && (loginUser.getOrgCategory().intValue() == BaseConstants.ORG_CATEGORY_HOSPITAL || loginUser.getOrgCategory().intValue() == BaseConstants.ORG_CATEGORY_department)){
				example.andCondition(" operate_type in ('1','2')  ");
			}else {
				example.andCondition(" operate_type in (-1)  ");
			}
			example.orderBy("createTime").desc();
			List<MsgDto> msgList = msgService.selectByPageInOrg(request.getPageNo(), request.getPageSize(), example).getList();
			if (msgList == null) {
				msgList = new ArrayList<MsgDto>();
			}
			if (msgList.size() < 10 && request.getPageNo()==1) {
				where.setStatus(1);
				example = new LLwExample(where);
				if(null != loginUser.getOrgCategory() && loginUser.getOrgCategory().intValue() == BaseConstants.ORG_CATEGORY_INSTITUTION) {
					//中心端用户 获取消息 要获取operateType 为3的
					example.andCondition(" operate_type = '3' ");
				}else if(null != loginUser.getOrgCategory() && (loginUser.getOrgCategory().intValue() == BaseConstants.ORG_CATEGORY_HOSPITAL || loginUser.getOrgCategory().intValue() == BaseConstants.ORG_CATEGORY_department)){
					example.andCondition(" operate_type in ('1','2')  ");
				}else {
					example.andCondition(" operate_type in (-1)  ");
				}
				
				example.orderBy("createTime").desc();
				List<MsgDto> tmpList = msgService.selectByPageInOrg(1,request.getPageSize() - msgList.size(), example).getList();
				if(tmpList!=null && tmpList.size()>0){
					msgList.addAll(tmpList);
				}
			}
			for (MsgDto row : msgList) {
				MsgResponse msg = new MsgResponse();
				BeanUtils.copyProperties(msg, row);
				retList.add(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseParam.ok().setData(retList);
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/msgRead")
	@ApiOperation(value = "已读消息", notes = "已读消息",tags = "消息相关接口列表")
	@ApiImplicitParams(@ApiImplicitParam(name = "auth", value = "access token，登录接口返回的", required = true, dataType = "String", paramType = "header"))
	@ApiResponses({ @ApiResponse(code = 200, message = "执行成功"),@ApiResponse(code = 400, message = "系统异常") })
	public ResponseParam msgRead(@ApiParam(name = "msgId", value = "消息ID", required = true) @RequestParam(value = "msgId", required = true) Long msgId) {
		MsgDto msg = new MsgDto();
		msg.setMsgId(msgId);
		msg.setStatus(1);
		msgService.updateByPrimaryKeySelective(msg);
		return ResponseParam.ok();
	}

}
