package com.llw.hospital.bs.controller;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.jcl.common.base.BaseController;
import com.jcl.dto.BaseResp;
import com.llw.hospital.api.MsgService;
import com.llw.hospital.dto.MsgDto;

/**
 * 消息表
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-07-02 16:53:38
 */
@RestController
@RequestMapping("/medical/msg")
public class MsgController extends BaseController{
    
	@Autowired
    private MsgService msgService;

    /**
     * 列表
     */
    @RequestMapping("/list.jspx")
    @RequiresPermissions("medical:msg:list")
    public BaseResp list(MsgDto msgDto,
                         @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            PageInfo pageInfo = msgService.selectByPage(pageNo, pageSize, msgDto);
            return BaseResp.success(pageInfo);
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 信息
     */
    @RequestMapping("/info.jspx")
    @RequiresPermissions("medical:msg:info")
    public BaseResp info(Long pk) {
        try {
            MsgDto msgDto = msgService.selectByPrimaryKey(pk);
            return BaseResp.success(msgDto);
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 保存
     */
    @RequestMapping("/save.jspx")
    @RequiresPermissions("medical:msg:save")
    public BaseResp save(MsgDto msgDto) {
        try {
            int rows = msgService.insertSelective(msgDto);
            if (rows > 0) {
                return BaseResp.success(msgDto);
            } else {
                return BaseResp.error(500, "记录保存失败");
            }
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 修改
     */
    @RequestMapping("/update.jspx")
    @RequiresPermissions("medical:msg:update")
    public BaseResp update(MsgDto msgDto) {
        try {
            int rows = msgService.updateByPrimaryKeySelective(msgDto);
            if (rows > 0) {
                return BaseResp.success(msgDto);
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
    @RequiresPermissions("medical:msg:delete")
    public BaseResp delete(Long pk) {
        try {
            int rows = msgService.deleteByPrimaryKey(pk);
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

}
