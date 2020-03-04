package com.llw.hospital.bs.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jcl.common.base.BaseController;
import com.jcl.dto.BaseResp;
import com.jcl.dto.ErrorCodeConstants;
import com.llw.hospital.api.*;
import com.llw.hospital.bs.common.util.MenuNode;
import com.llw.hospital.bs.common.util.OrgUtil;
import com.llw.hospital.bs.common.util.TreeBuilder;
import com.llw.hospital.bs.util.LoginUtil;
import com.llw.hospital.common.base.constants.BaseConstants;
import com.llw.hospital.dto.*;
import com.llw.hospital.vo.organization.SysOrganizationVO;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 机构
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
@RestController
@RequestMapping("/medical/sysOrganization")
public class SysOrganizationController extends BaseController{
    @Autowired
    private SysOrganizationService sysOrganizationService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @ResponseBody
    @RequestMapping(value = "/userRoles.jspx")
    public BaseResp userRoles(Long userId) {
        //用户为空，查询出当前登录用户所在机构的所有角色
        SysRoleDto sysRoleDto = new SysRoleDto();
        sysRoleDto.setOrgId(LoginUtil.getCurrentUser().getOrgId());
        sysRoleDto.setEnabled(BaseConstants.STATUS_NORMAL);
        List<SysRoleDto> list = sysRoleService.selectList(sysRoleDto);
        if(null == userId){
            return BaseResp.success(list);
        }else{
            SysUserRoleDto sysUserRoleDto = new SysUserRoleDto();
            sysUserRoleDto.setUserId(userId);
            sysUserRoleService.selectList(sysUserRoleDto);
            return BaseResp.success();
        }
    }


    @ResponseBody
    @RequestMapping(value = "/orgList.jspx")
    public BaseResp orgList(Long pid) {
        if(null == pid){
            List<SysOrganizationTreeDto> list = new ArrayList<>(1);
            pid = LoginUtil.getCurrentUser().getOrgId();
            SysOrganizationDto dto = sysOrganizationService.selectByPrimaryKey(pid);
            SysOrganizationTreeDto treeDto = new SysOrganizationTreeDto();
            BeanUtils.copyProperties(dto,treeDto);
            SysOrganizationDto countDto = new SysOrganizationDto();
            countDto.setPid(dto.getPid());
            treeDto.setChildCount(sysOrganizationService.selectCount(countDto));
            list.add(treeDto);
            return BaseResp.success(list);
        }else{
            List<SysOrganizationTreeDto> children = sysOrganizationService.selectChildren(pid);
            return BaseResp.success(children);
        }
    }

    /**
     * 列表
     */
    @RequestMapping("/list.jspx")
    public BaseResp list(SysOrganizationDto sysOrganizationDto) {
        try {
            sysOrganizationDto.setIsDel(BaseConstants.NOT_DELETED);
            List<SysOrganizationDto> list=sysOrganizationService.selectList(sysOrganizationDto);
            return BaseResp.success(list);
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 信息
     */
    @RequestMapping("/info.jspx")
    public BaseResp info(Long pk) {
        try {
            SysOrganizationVO sysOrganizationVO = sysOrganizationService.selectByPrimaryKey(pk,SysOrganizationVO.class);
            return BaseResp.success(sysOrganizationVO);
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 新增子节点
     */
    @RequestMapping("/addChild.jspx")
    public BaseResp addChildren(SysOrganizationDto sysOrganizationDto) {
        try {
            if(null == sysOrganizationDto.getPid()){
                sysOrganizationDto.setPid(BaseConstants.SUPER_ORG_ID);
            }

            sysOrganizationService.createSysOrganizationDto(sysOrganizationDto);
            return BaseResp.success("成功");
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 修改
     */
    @RequestMapping("/modify.jspx")
    public BaseResp update(SysOrganizationDto sysOrganizationDto) {
        try {
            sysOrganizationDto.setPid(null);
            if(sysOrganizationDto.getOrgId()==null){
                return BaseResp.error(ErrorCodeConstants.PARAM_INCOMPLETE.getVal(),"缺少当前机构id");
            }
            int rows = sysOrganizationService.updateSysOrganizationDto(sysOrganizationDto);
            if (rows > 0) {
                return BaseResp.success(sysOrganizationDto);
            } else {
                return BaseResp.error(500, "记录修改失败");
            }

        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delete.jspx")
    public BaseResp delete(Long pk) {
        try {
            if(pk == BaseConstants.SUPER_ORG_ID){
                return BaseResp.error(-1, "根节点不允许删除");
            }

            SysOrganizationDto countDto = new SysOrganizationDto();
            countDto.setPid(pk);
            if(sysOrganizationService.selectCount(countDto) > 0){
                return BaseResp.error(500, "请先删除子节点");
            }
            
            Long pid = LoginUtil.getCurrentUser().getOrgId();
            if(pk.equals(pid)) {
            	return BaseResp.error(500, "当前登录用户所属机构不能删除");
            }
            
            int rows = sysOrganizationService.deleteOrganization(pk);
            if (rows > 0) {
                return BaseResp.success(true);
            } else {
                return BaseResp.error(500, "记录删除失败");
            }
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "/orgListTree.jspx")
    public List<MenuNode> orgListTree() {

    	Long beginId = 0L;
    	List<SysOrganizationTreeDto> lst = sysOrganizationService.selectChildren(0L);
    	while(lst!=null && lst.size()==1){
    		beginId = lst.get(0).getOrgId();
			lst = sysOrganizationService.selectChildren(beginId);
    	}
    	if(lst==null || lst.size() < 2){
    		beginId = 0L;
    	}
    	Long endId = OrgUtil.getMaxLeafTheoreticalValue(beginId);
    	List<SysOrganizationTreeDto> list = sysOrganizationService.selectChildren2(beginId, endId);

    	ArrayList<MenuNode> allNodes = new ArrayList<MenuNode>();
		for (SysOrganizationTreeDto rs : list) {
			MenuNode node = new MenuNode();
			node.setId(String.valueOf(rs.getOrgId()));
			node.setName(rs.getName());
			node.setPid(String.valueOf(rs.getPid()));
			node.setPname(rs.getPname());
			node.setType(rs.getOrgCategory());
			allNodes.add(node);
		}

		TreeBuilder tb = new TreeBuilder();
		ArrayList<MenuNode> menuList = (ArrayList<MenuNode>) tb.buildListToTree(allNodes);

    	return menuList;
    }

}
