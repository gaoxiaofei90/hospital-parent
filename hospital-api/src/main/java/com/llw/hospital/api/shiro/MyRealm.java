package com.llw.hospital.api.shiro;

import com.llw.common.redis.LlwRedisUtil;
import com.llw.common.util.JwtUtil;
import com.llw.hospital.api.SysUserService;
import com.llw.hospital.ds.entity.LoginUser;
import com.llw.hospital.dto.SysUserDto;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;

/**
 * @author wendellpeng
 * @Title: MyRealm
 * @ProjectName gov-parent
 * @date 2018/8/27 19:55
 */
public class MyRealm extends AuthorizingRealm {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    SysUserService sysUserService;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
/*        String userId = JwtUtil.getUid(principals.toString());
        Map<Long, CachedUserDto> users = (Map<Long, CachedUserDto>) CacheClient.getCachedObject(CacheConstants.ORG_USER_CACHE);
        CachedUserDto user = users.get(Long.parseLong(userId));
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("admin");
        Set<String> permission = new HashSet<>(Arrays.asList(user.getPermissions().split(";")));
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;*/
       return null;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得userId，用于和数据库进行对比
        String userMessage = JwtUtil.getUid(token);
        String userId = userMessage;
        if(userMessage.indexOf(":") != -1) {
        	userId = userMessage.split(":")[0];
        }

        if (userId == null) {
            throw new AuthenticationException("token invalid");
        }

        byte[] userCached = LlwRedisUtil.get(("userCache:"+userId).getBytes());
        LoginUser user = null;
        if(null == userCached){
            LOGGER.warn("缓存中没有userId:{}对应的数据，尝试从数据库获取",userId);
            SysUserDto sysUserDto = sysUserService.selectByPrimaryKey(Long.parseLong(userId));
            if(null == sysUserDto){
                throw new AuthenticationException("user didn't existed!");
            }
            user = getLoginUser(sysUserDto);
            LlwRedisUtil.set(("userCache:"+user.getUserId()).getBytes(), SerializationUtils.serialize(user),24*3600);
        }else{
            user= (LoginUser) SerializationUtils.deserialize(userCached);
        }
        //保存用户信息到上下文
        JWTFilter.currentUser.set(user);
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }

    private LoginUser getLoginUser(SysUserDto user) {
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(user,loginUser);
        loginUser.setPassword(null);
        // FIXME: 2019/7/9 获取数据权限相关数据
        return loginUser;
    }
}
