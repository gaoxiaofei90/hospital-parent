package com.llw.hospital.bs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.jcl.common.base.BaseController;
import com.jcl.dto.BaseResp;
import com.llw.hospital.common.base.constants.BaseConstants;
import com.llw.module.network.ftp.FtpUtil;

@Controller
public class SysUploadFileController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(SysUploadFileController.class);

	
	@ResponseBody
	@RequestMapping(value = "/upload.jspx")
    public BaseResp upload(HttpServletRequest request, HttpServletResponse response,
    					  @RequestParam(value = "file", required = false) MultipartFile file) {
		List<String> l = new ArrayList<String>();
		Map<String,String> m = new HashMap<String,String>();
		try {
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					file = multiRequest.getFile(iter.next());
					if (file != null) { 
						String oFile = System.currentTimeMillis()+"-"+file.getOriginalFilename();
//						String viewPath = FtpUtils.uploadFile(oFile, file.getInputStream()); 
						String viewPath = FtpUtil.getInstance(BaseConstants.MODULE_NAME).upload(file,"upload");
					    m.put(file.getOriginalFilename(),viewPath);
					    l.add(viewPath);
					}
				}
			}
		} catch (Exception e) {
			logger.error("上传失败", e);
			return BaseResp.error(10001,"上传失败");
		}
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("fileMap", m);
		result.put("fileList", l);
        return BaseResp.success(result);
    }
 
}
