package com.expenseTracker.backend.aspects.Logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.hibernate.mapping.Join;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingServiceMethods {

    @Pointcut("execution(* com.expenseTracker.backend.services.*.*(..))")
    public void everyServiceMethod(){}

    private static final Logger logger = LoggerFactory.getLogger(LoggingServiceMethods.class);

    @Around("everyServiceMethod()")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        logger.info("ServiceMethod : "+proceedingJoinPoint.getSignature());
        logger.info("Args : ");
        Object[] args = proceedingJoinPoint.getArgs();
        for(Object arg:args){
            logger.debug(arg.toString());
        }
        long begin = System.currentTimeMillis();
        Object result;
        try{
            result = proceedingJoinPoint.proceed();
            logger.debug("Successfully done ServiceMethodExecution");
        }
        catch (Throwable e){
            logger.debug("Exception or Error occurred in Service "+e.getMessage());
            throw e;
        }

        long end = System.currentTimeMillis();
        logger.debug("Execution time : "+((end-begin)*0.1)/1000);
        return result;
    }

}
