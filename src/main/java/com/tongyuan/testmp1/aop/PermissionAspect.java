package com.tongyuan.testmp1.aop;

import com.tongyuan.testmp1.entity.Admin;
import com.tongyuan.testmp1.entity.Hr;
import com.tongyuan.testmp1.entity.Teacher;
import com.tongyuan.testmp1.entity.User;
import com.tongyuan.testmp1.helper.Token;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by zhangcy on 2018/4/18
 */
@Aspect
@Component
public class PermissionAspect {
    @Pointcut("execution(public * com.tongyuan.testmp1.controller.*.*(..))")
    public void permission(){
    }

    private Method getSourceMethod(JoinPoint jp){
        Method proxyMethod = ((MethodSignature) jp.getSignature()).getMethod();
        try {
            return jp.getTarget().getClass().getMethod(proxyMethod.getName(), proxyMethod.getParameterTypes());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean containsPermission(String[] permissions,String permission){
        if(permission!=null){
            for(int i=0;i<permissions.length;i++){
                if(permission.equals(permissions[i])){
                    return true;
                }
            }
        }
        return false;
    }


    @Before("permission()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        Method targetMethod = getSourceMethod(joinPoint);
        if(targetMethod!=null){
            Permission permission = targetMethod.getAnnotation(Permission.class);
            if(permission!=null){
                String[] roles = permission.value().split(",");
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = attributes.getRequest();
                User user = (User)request.getSession().getAttribute("user");
                boolean isRedirect = false;
                if(user instanceof Token){
                    isRedirect = !containsPermission(roles,"student");
                }else if(user instanceof Teacher){
                    isRedirect = !containsPermission(roles,"teacher");
                }else if(user instanceof Hr){
                    isRedirect = !containsPermission(roles,"hr");
                }else if(user instanceof Admin){
                    isRedirect = !containsPermission(roles,"admin");
                }
                //需要跳转
                // TODO: 2018/4/18  
            }
        }
    }
}
