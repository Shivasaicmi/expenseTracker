package com.expenseTracker.backend.aspects.Logging;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingRequests {

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

    private static final Logger logger = LoggerFactory.getLogger(LoggingRequests.class);


    @Before("getRequest()")
    public void beforeReceivingGetRequest(JoinPoint joinPoint){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("Received GET request for URL: {}", request.getRequestURL().toString());
        System.out.println("this is the method signature "+joinPoint.getSignature());
        logger.info(" Method : "+joinPoint.getSignature());
        Object[] args = joinPoint.getArgs();
        for(Object arg:args){
            logger.debug(arg.toString());
        }

    }

    @AfterThrowing(value = "getRequest()",throwing = "theExc")
    public void afterGetRequestThrowing(JoinPoint joinPoint,Throwable theExc){
        logger.info("Exception or error occured");
        logger.debug(joinPoint.getSignature().toString());
        logger.debug(theExc.getMessage());
    }

    @AfterReturning(value = "getRequest()",returning = "result")
    public void afterGetRequestReturning(JoinPoint joinPoint,Object result){
        logger.info("successfully responded ");
        logger.debug(joinPoint.getSignature().toString());
    }

    @Before("postRequest()")
    public void beforeReceivingPostRequest(JoinPoint joinPoint){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("Received POST request for URL: {}", request.getRequestURL().toString());
        logger.debug(" Method : "+joinPoint.getSignature());
        Object[] args = joinPoint.getArgs();
        for(Object arg:args){
            logger.debug(arg.toString());
        }

    }

    @AfterThrowing(value = "postRequest()",throwing = "theExc")
    public void afterPostRequestThrowing(JoinPoint joinPoint,Throwable theExc){
        logger.info("Exception or error occured");
        logger.debug(joinPoint.getSignature().toString());
        logger.debug(theExc.getMessage());
    }

    @AfterReturning(value = "postRequest()",returning = "result")
    public void afterPostRequestReturning(JoinPoint joinPoint,Object result){
        logger.info("successfully responded ");
        logger.debug(joinPoint.getSignature().toString());

    }

    @Before("putRequest()")
    public void beforeReceivingPutRequest(JoinPoint joinPoint){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("Received POST request for URL: {}", request.getRequestURL().toString());
        logger.debug(" Method : "+joinPoint.getSignature());
        Object[] args = joinPoint.getArgs();
        for(Object arg:args){
            logger.debug(arg.toString());
        }

    }

    @AfterThrowing(value = "putRequest()",throwing = "theExc")
    public void afterPutRequestThrowing(JoinPoint joinPoint,Throwable theExc){
        logger.info("Exception or error occured");
        logger.debug(joinPoint.getSignature().toString());
        logger.debug(theExc.getMessage());
    }

    @AfterReturning(value = "putRequest()",returning = "result")
    public void afterPutRequestReturning(JoinPoint joinPoint,Object result){
        logger.info("successfully responded ");
        logger.debug(joinPoint.getSignature().toString());

    }

    @Before("patchRequest()")
    public void beforeReceivingPatchRequest(JoinPoint joinPoint){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("Received POST request for URL: {}", request.getRequestURL().toString());
        logger.debug(" Method : "+joinPoint.getSignature());
        Object[] args = joinPoint.getArgs();
        for(Object arg:args){
            logger.debug(arg.toString());
        }

    }

    @AfterThrowing(value = "patchRequest()",throwing = "theExc")
    public void afterPatchRequestThrowing(JoinPoint joinPoint,Throwable theExc){
        logger.info("Exception or error occured");
        logger.debug(joinPoint.getSignature().toString());
        logger.debug(theExc.getMessage());
    }

    @AfterReturning(value = "patchRequest()",returning = "result")
    public void afterPatchRequestReturning(JoinPoint joinPoint,Object result){
        logger.info("successfully responded ");
        logger.debug(joinPoint.getSignature().toString());
    }

    @Before("deleteRequest()")
    public void beforeReceivingDeleteRequest(JoinPoint joinPoint){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("Received POST request for URL: {}", request.getRequestURL().toString());
        logger.debug(" Method : "+joinPoint.getSignature());
        Object[] args = joinPoint.getArgs();
        for(Object arg:args){
            logger.debug(arg.toString());
        }

    }

    @AfterThrowing(value = "deleteRequest()",throwing = "theExc")
    public void afterDeleteRequestThrowing(JoinPoint joinPoint,Throwable theExc){
        logger.info("Exception or error occured");
        logger.debug(joinPoint.getSignature().toString());
        logger.debug(theExc.getMessage());
    }

    @AfterReturning(value = "deleteRequest()",returning = "result")
    public void afterDeleteRequestReturning(JoinPoint joinPoint,Object result){
        logger.info("successfully responded ");
        logger.debug(joinPoint.getSignature().toString());

    }

}
