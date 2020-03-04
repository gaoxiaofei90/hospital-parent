package com.llw.hospital.bs.controller;

import io.swagger.annotations.Api;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.jcl.common.base.BaseController;
import com.jcl.dto.BaseResp;
import com.llw.hospital.api.SysOrganizationService;
import com.llw.hospital.api.SysUserService;
import com.llw.hospital.common.base.constants.UpmsConstant;
import com.llw.hospital.common.base.constants.UpmsResultConstant;
import com.llw.hospital.common.util.MD5Util;
import com.llw.hospital.ds.entity.LoginUser;
import com.llw.hospital.dto.SysOrganizationDto;
import com.llw.hospital.dto.SysUserDto;


/**
 * 登录管理
 * Created by shengpeng on 2018/4/10.
 */
@Controller
@RequestMapping("/")
@Api(value = "登录管理", description = "登录管理")
public class SysLoginController extends BaseController {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysOrganizationService organizationService;

    @Autowired
    private Producer producer;

    @RequestMapping("/captcha.jspx")
    public void captcha(HttpServletResponse response)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        SecurityUtils.getSubject().getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    @RequestMapping(value = "/login.jspx", method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response,@RequestBody LoginVo loginVo) {
    	String vercode = loginVo.getVercode();
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        /*if("product".equals(PropertiesUtils.getEnv())){
            Object x = verifyVercode(vercode);
            if (x != null){
                return x;
            }
        }else{
            if(!"123456".equals(vercode) && !"1".equals(vercode)){
                Object x = verifyVercode(vercode);
                if (x != null){
                    return x;
                }
            }
        }*/
        if (StringUtils.isBlank(username)) {
            return BaseResp.error(UpmsResultConstant.EMPTY_USERNAME.getCode(), "帐号不能为空！");
        }
        if (StringUtils.isBlank(password)) {
            return BaseResp.error(UpmsResultConstant.EMPTY_PASSWORD.getCode(), "密码不能为空！");
        }
        Subject subject = SecurityUtils.getSubject();
        // 判断是否已登录，如果已登录，则回跳，防止重复登录
        if (!(subject.isAuthenticated() || subject.isRemembered())) {
            // 使用shiro认证
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            try {
                usernamePasswordToken.setRememberMe(false);
                subject.login(usernamePasswordToken);
            } catch (UnknownAccountException e) {
                return BaseResp.error(UpmsResultConstant.INVALID_USERNAME.getCode(), "帐号不存在！");
            } catch (IncorrectCredentialsException e) {
                return BaseResp.error(UpmsResultConstant.INVALID_PASSWORD.getCode(), "密码错误！");
            } catch (LockedAccountException e) {
                return BaseResp.error(UpmsResultConstant.INVALID_ACCOUNT.getCode(), "帐号已锁定！");
            }
        }

        SysUserDto user = new SysUserDto();
        user.setUsername(username);
        user = sysUserService.selectOne(user);
        LoginUser loginUser = getLoginUser(user);// update 20191227
        SecurityUtils.getSubject().getSession().setAttribute(UpmsConstant.LOGIN_USER, user);
        SecurityUtils.getSubject().getSession().setAttribute(UpmsConstant.LOGIN_USER1, loginUser);// update 20191227
        return BaseResp.success();
    }


    private Object verifyVercode(String vercode) {
        Object kaptcha = SecurityUtils.getSubject().getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(kaptcha == null){
            return   BaseResp.error(UpmsResultConstant.FAILED.getCode(), "验证码已过期");
        }
        SecurityUtils.getSubject().getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(!kaptcha.toString().equalsIgnoreCase(vercode)){
            return  BaseResp.error(UpmsResultConstant.FAILED.getCode(), "验证码不正确");
        }
        return null;
    }

    @RequestMapping(value = "/logout.jspx", method = RequestMethod.GET)
    public String  logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated() && subject.getPrincipal() != null){
            subject.logout();
        }
        return "redirect:login.html";
    }

    @RequestMapping(value = "/logoutAjax.jspx", method = RequestMethod.GET)
    @ResponseBody
    public BaseResp logoutAjax(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated() && subject.getPrincipal() != null){
            subject.logout();
        }
        return BaseResp.success("ok").setRespData(request.getContextPath() + "/login.html");
    }


    @RequestMapping(value = "/changePassword.jspx", method = RequestMethod.POST)
    @ResponseBody
    public Object changePassword(HttpServletRequest request, HttpServletResponse response, @RequestParam String oldPassword,@RequestParam String newPassword) {
        Subject subject = SecurityUtils.getSubject();
        Session session=subject.getSession();
        SysUserDto sysUserDto= (SysUserDto) session.getAttribute(UpmsConstant.LOGIN_USER);
        if(sysUserDto==null){
            return BaseResp.error(UpmsResultConstant.NOT_LOGIN.getCode(), "未登录");
        }
        if(StringUtils.isBlank(oldPassword)
            ||StringUtils.isBlank(newPassword)){
            return BaseResp.error(UpmsResultConstant.EMPTY_PASSWORD.getCode(), "密码不能为空！");
        }
        oldPassword=MD5Util.MD5(oldPassword + sysUserDto.getSalt());
        newPassword=MD5Util.MD5(newPassword + sysUserDto.getSalt());
        String password=sysUserDto.getPassword();
        if(StringUtils.isNotBlank(password)
            && password.equals(oldPassword)){
            sysUserDto.setPassword(newPassword);
            sysUserService.updateByPrimaryKey(sysUserDto);
            session.setAttribute(UpmsConstant.LOGIN_USER,sysUserDto);
            return BaseResp.success("修改成功");
        }
        return BaseResp.error(UpmsResultConstant.INVALID_PASSWORD.getCode(),"密码错误");
    }
    
    
    private LoginUser getLoginUser(SysUserDto user) {
        
    	LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(user,loginUser);
        loginUser.setPassword(null);
        SysOrganizationDto sysOrganizationDto = organizationService.selectByPrimaryKey(loginUser.getOrgId());
        loginUser.setOrgCategory(sysOrganizationDto.getOrgCategory());
        return loginUser;
    }
    
}
