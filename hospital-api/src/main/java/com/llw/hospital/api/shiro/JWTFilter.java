package com.llw.hospital.api.shiro;

import io.jsonwebtoken.ExpiredJwtException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.SerializationUtils;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.rpc.RpcContext;
import com.jcl.common.util.JSONUtils;
import com.llw.common.redis.LlwRedisUtil;
import com.llw.common.util.JwtUtil;
import com.llw.hospital.api.vo.ResponseParam;
import com.llw.hospital.ds.entity.LoginUser;

/**
 * @author wendellpeng
 * @Title: JWTFilter
 * @ProjectName inst-parent
 * @date 2018/8/2719:57
 */
public class JWTFilter extends AuthenticationFilter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    static ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<>();
    public static ThreadLocal<LoginUser> currentUser = new ThreadLocal<>();

    public static HttpServletRequest getCurrentRequest() {
        return currentRequest.get();
    }

    public static LoginUser getCurrentUser() {
        return currentUser.get();
    }


    public static void setCurrentUser(LoginUser loginUser) {
        currentUser.set(loginUser);
        System.out.println("login-----"+loginUser.getUserId() +";"+loginUser.getName());
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        currentRequest.set((HttpServletRequest) request);
        String auth = ((HttpServletRequest)request).getHeader("auth");
        boolean authFromParamter = false;
        if(StringUtils.isEmpty(auth)){
            auth = request.getParameter("auth");
            authFromParamter = true;
        }

        if(null != auth && auth.length() > 0){
            auth = auth.trim();
            try{
                if(JwtUtil.verify(auth))
                {
                    if(authFromParamter){
                        //从url参数中获取的auth信息，只做权限控制，不在本地进行登陆
                        return true;
                    }else{
                        //本地做一把登陆
                        Subject subject = SecurityUtils.getSubject();
                        if (!(subject.isAuthenticated())) {
                            // 使用shiro认证
                            JWTToken jwtToken = new JWTToken(auth);
                            try {
                                subject.login(jwtToken);
                                //保存当前用户
                                String userMessage = JwtUtil.getUid(auth);
                                String userId = userMessage;
                                if(userMessage.indexOf(":") != -1) {
                                    userId = userMessage.split(":")[0];
                                }

                                byte[] userCached = LlwRedisUtil.get(("userCache:"+userId).getBytes());
                                if(null == userCached){
                                    return false;
                                }
                                LoginUser user = (LoginUser) SerializationUtils.deserialize(userCached);
                                //将当前用户存入RpcContext
                                user.setToRpcContext(RpcContext.getContext());
                                currentUser.set(user);
                                return true;
                            } catch (UnknownAccountException e) {
                                logger.error(e.getMessage(),e);
                            } catch (IncorrectCredentialsException e) {
                                logger.error(e.getMessage(),e);
                            } catch (LockedAccountException e) {
                                logger.error(e.getMessage(),e);
                            }catch (Exception e){
                                logger.error(e.getMessage(),e);
                            } finally{
//                            currentUserId.remove();
                            }
                        }
                    }
                }
            }catch (ExpiredJwtException e){
                logger.warn("过期的token"  + auth + " request:"+ request.getRemoteAddr() + " host:" + request.getRemoteHost());
                return false;
            }
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        logger.warn("无权限的请求" + servletRequest.getRemoteAddr() + " host:" + servletRequest.getRemoteHost());
        servletResponse.getWriter().append(JSONUtils.toJSONString(ResponseParam.error("401","no auth"))).flush();
        currentUser.remove();
        return false;
    }

    public static void main(String[] args) {
    	try {
    		LoginUser logUser = new LoginUser();
        	logUser.setName("ggg");
        	JWTFilter.setCurrentUser(logUser);
        	//JWTFilter.getCurrentUser();
        	if(JWTFilter.getCurrentUser() != null){
        		System.out.println("login---"+JWTFilter.getCurrentUser().getName());
        	}else{
        		System.out.println("ggg");
        	}
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
