package com.dnight.log.aop.aspectj;

import cn.hutool.json.JSONUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @author ZHONGPENG769
 * @date 2019/11/8
 */
@Aspect
@Component
@Slf4j
public class AopLog {

    private static final String START_TIME = "request-time";

    @Pointcut("execution(public * com.dnight.log.aop.controller.*Controller.*(..))")
    public void log(){}

    @Before("log()")
    public void beforeLog(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        log.info("[请求 URL] : {}", request.getRequestURL());
        log.info("[请求 IP] : {}", request.getRemoteAddr());
        log.info("[请求类名] : {}, [请求方法名] : {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());

        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("[请求参数] : {}", JSONUtil.toJsonStr(parameterMap));
        request.setAttribute(START_TIME, System.currentTimeMillis());
    }

    @Around("log()")
    public Object aroundLog(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        log.info("[返回值] : {}", JSONUtil.toJsonStr(result));
        return result;
    }

    @AfterReturning("log()")
    public void afterReturning(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        Long start = (Long)request.getAttribute(START_TIME);
        log.info("[请求耗时] : {} ms", System.currentTimeMillis()-start);

        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        log.info("[浏览器类型] : {}, [操作系统] : {}, [原始User-Agent] : {}",
                userAgent.getBrowser().toString(), userAgent.getOperatingSystem().toString(),
                header);
    }
}
