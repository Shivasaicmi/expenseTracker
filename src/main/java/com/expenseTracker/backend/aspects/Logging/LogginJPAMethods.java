package com.expenseTracker.backend.aspects.Logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogginJPAMethods {

    private static final Logger logger = LoggerFactory.getLogger(LogginJPAMethods.class);

    @Pointcut("execution(* com.expenseTracker.backend.repositories.*.*(..))")
    public void everyJpaMethod(){}

    @Before("everyJpaMethod()")
    public void logJpaMethods(JoinPoint joinPoint){
        logger.debug("Jpa Method "+joinPoint.getSignature());
        Object[] args = joinPoint.getArgs();
        for(Object arg:args){
            logger.debug(arg.toString());
        }
    }


}
