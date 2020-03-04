package com.llw.hospital.api.aspect;

import com.jcl.common.util.JSONUtils;
import com.jcl.common.util.PropertiesUtils;
import com.llw.hospital.api.annotation.LogIgnore;
import com.llw.hospital.api.shiro.JWTFilter;
import com.llw.hospital.api.util.CircleQueue;
import com.llw.hospital.common.util.DatabaseTimeUtil;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    public static CircleQueue<AccessLog> queue = new CircleQueue<AccessLog>(200);

    private boolean ignore(ProceedingJoinPoint pjp){
        //生产环境不记录日志
        if("product".equals(PropertiesUtils.getEnv()))
        {
            return true;
        }else{
            Class[] parameterTypes = new Class[pjp.getArgs().length];
            Object[] args = pjp.getArgs();
            for(int i=0; i<args.length; i++) {
                if(args[i] != null) {
                    parameterTypes[i] = args[i].getClass();
                }else {
                    parameterTypes[i] = null;
                }
            }
            String methodName = pjp.getSignature().getName();
            try {
                Method method = pjp.getSignature().getDeclaringType().getMethod(methodName, parameterTypes);
                Method realMethod = pjp.getTarget().getClass().getDeclaredMethod(pjp.getSignature().getName(), method.getParameterTypes());
                return realMethod.isAnnotationPresent(LogIgnore.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    @Around(value="@annotation(apiOperation)")
    public Object doAround(ProceedingJoinPoint pjp, ApiOperation apiOperation) throws Throwable {
        if(!ignore(pjp)){
            AccessLog log = new AccessLog();
            log.setRequestTime(DatabaseTimeUtil.getCurrentTimeService().getCurrentTime());
            log.setFn(apiOperation.value());
            try {
                Object[] args = pjp.getArgs();
                if(null != args && args.length > 0){
                    log.setRequest(JSONUtils.toJSONString(args[0]));
                }else{
                    if(null != JWTFilter.getCurrentRequest()) {
                        log.setRequest(JSONUtils.toJSONString(JWTFilter.getCurrentRequest().getParameterMap()));
                    }
                }
                Object result = pjp.proceed();
                if(null != result){
                    log.setResponse(JSONUtils.toJSONString(result));
                }else{
                    log.setResponse("");
                }
                return result;
            } catch (Throwable e) {
                log.setResponse(e.getMessage());
                throw e;
            }finally {
                log.setResponseTime(DatabaseTimeUtil.getCurrentTimeService().getCurrentTime());
                queue.add(log);
            }
        }else{
            return pjp.proceed();
        }
    }
}
