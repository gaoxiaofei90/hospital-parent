package com.llw.hospital.api.controller;

import com.jcl.common.base.BaseController;
import com.llw.hospital.api.annotation.LogIgnore;
import com.llw.hospital.api.vo.ResponseParam;
import com.llw.hospital.api.vo.common.UploadResponse;
import com.llw.hospital.common.base.constants.BaseConstants;
import com.llw.hospital.common.util.StringUtils;
import com.llw.module.network.ftp.FtpUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@RestController
@Api(tags="上传文件相关接口列表")
@RequestMapping(value = "/api/user/file")
public class SysUploadFileController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(SysUploadFileController.class);
	
	private static Set<String> ignoreSet = new HashSet<String>();
	
	static{
		ignoreSet.add("js");
		ignoreSet.add("jsp");
		ignoreSet.add("php");
		ignoreSet.add("asp");
		ignoreSet.add("aspx");
		ignoreSet.add("html");
		ignoreSet.add("htm");
	}
	

	@Autowired
	private ServletContext context;


	@LogIgnore
	@PostMapping(value = "/upload")
	@ApiImplicitParams({@ApiImplicitParam(name = "auth",value = "access token，登录接口返回的",required = true,dataType = "String",paramType = "header")
	})
	@ApiResponses({
			@ApiResponse(code = 200,message = "success",response = UploadResponse.class),
			@ApiResponse(code = 400,message = "系统异常")
	})
    public ResponseParam<UploadResponse> upload(@ApiParam(name="file",value = "上传的文件",required = true) @RequestParam(value = "files", required = true) MultipartFile[] files, HttpServletRequest request, @ApiParam(name="bucketName",value = "文件类型(checkRoom:查房,accident:事件记录)",required = true) @RequestParam(value = "bucketName", required = true)String bucketName) {
		if(StringUtils.isEmpty(bucketName)){
			bucketName = "upload";
		}
		String[] paths=new String[files.length];
		for(int i=0;i<files.length;i++){
			String r = FtpUtil.getInstance(BaseConstants.MODULE_NAME).upload(files[i],bucketName);
			paths[i]=r;
		}
		UploadResponse uploadResponse=new UploadResponse();
		uploadResponse.setPath(StringUtils.join(paths,","));
        return ResponseParam.ok().setData(uploadResponse);
    }
}
