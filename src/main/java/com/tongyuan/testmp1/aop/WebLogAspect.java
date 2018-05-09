package com.tongyuan.testmp1.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by zhangcy on 2018/3/29
 */
@Aspect
@Component
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.tongyuan.testmp1.controller.*.*(..))")
    public void webLog(){
    }

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("IP : " + getIpAddr(request));
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    //后置异常通知
    @AfterThrowing("webLog()")
    public void doRecordException(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.error("IP : " + request.getRemoteAddr());
        logger.error("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()+" 方法执行异常!");
    }

    public  String getIpAddr(HttpServletRequest request)  {
        String ip = request.getRemoteAddr();
        if(ip  ==   null   ||  ip.length()  ==   0   ||   "unknown" .equalsIgnoreCase(ip)){
            ip  =  request.getHeader( " x-forwarded-for " );
        }
        if (ip  ==   null   ||  ip.length()  ==   0   ||   "unknown" .equalsIgnoreCase(ip))  {
            ip  =  request.getHeader( " Proxy-Client-IP " );
        }
        if (ip  ==   null   ||  ip.length()  ==   0   ||   "unknown" .equalsIgnoreCase(ip))  {
            ip  =  request.getHeader( " WL-Proxy-Client-IP " );
        }
        if (ip  ==   null   ||  ip.length()  ==   0   ||   "unknown" .equalsIgnoreCase(ip))  {
            ip  =  request.getRemoteAddr();
        }
        return  ip;
    }
}
