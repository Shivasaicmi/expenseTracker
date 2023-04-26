package com.expenseTracker.backend.aspects;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LogginAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getRequest() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postRequest() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putRequest() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deleteRequest() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void patchRequest() {}
    private static final Logger logger = LoggerFactory.getLogger(LogginAspect.class);

    @Before("getRequest()")
    public void beforeLogin(JoinPoint joinPoint){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("Received GET request for URL: {}", request.getRequestURL().toString());

    }


}
