package com.llw.hospital.bs.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.jcl.common.base.BaseController;
import com.jcl.dto.BaseResp;
import com.llw.common.redis.LlwRedisUtil;
import com.llw.hospital.api.SysGeneratorService;
import com.llw.hospital.bs.model.ColumnModel;
import com.llw.hospital.common.util.StringUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/medical/code")
public class CodeController extends BaseController {

    @Autowired
    SysGeneratorService sysGeneratorService;


    /**
     * 列表
     */
    @RequestMapping("/list.jspx")
    public BaseResp list(String name) {
        try {
            if(StringUtils.isEmpty(name)){
                name = "";
            }
            Map<String,Object> map = new HashMap<>(1);
            map.put("tableName",name);
            List list = sysGeneratorService.queryList(map);
            return BaseResp.success(new PageInfo<>(list));
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 详情
     */
    @RequestMapping("/info.jspx")
    public BaseResp info(String tableName) {
        try {
            List<Map<String, Object>> columns = sysGeneratorService.queryColumns(tableName);
            return BaseResp.success(new PageInfo<>(columns));
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }



    @RequestMapping("/create")
    public BaseResp generator(String tableName, ColumnModel column) throws IOException {
        byte[] data = sysGeneratorService.generatorCode(new String[]{tableName},column.getColumn());
        String uuid = UUID.randomUUID().toString();
        LlwRedisUtil.set(uuid.getBytes(),data,60);
        return BaseResp.success().setRespData(uuid);
    }


    @RequestMapping("/download")
    public void download(String id) throws IOException {
        byte[] data = LlwRedisUtil.get(id.getBytes());
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"AutoCode.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
