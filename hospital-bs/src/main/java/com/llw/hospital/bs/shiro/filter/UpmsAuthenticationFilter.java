package com.llw.hospital.bs.shiro.filter;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 重写authc过滤器
 * Created by shuzheng on 2017/3/11.
 */
public class UpmsAuthenticationFilter extends AuthenticationFilter {

    private final static Logger _log = LoggerFactory.getLogger(UpmsAuthenticationFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
//        System.out.println("sessionId:" + session.getId());
        // 判断请求类型
        boolean authed = subject.isAuthenticated();
        return authed;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //StringBuffer sso_server_url = new StringBuffer(PropertiesUtils.getString("zheng.upms.sso.server.url"));
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        String contextPath = request.getServletContext().getContextPath();
        if (isAjaxRequest((HttpServletRequest) request)) {//ajax
            //向http头添加 状态 sessionStatus
            httpServletResponse.setStatus(403);
            httpServletResponse.setHeader("sessionStatus", "timeout");
            //向http头添加登录的url
            httpServletResponse.setHeader("loginPath", contextPath+"/login.html");
            return false;
        }else{
            httpServletResponse.sendRedirect(contextPath+"/login.html");
            return false;
        }
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        return requestType != null && requestType.equals("XMLHttpRequest");
    }
}
