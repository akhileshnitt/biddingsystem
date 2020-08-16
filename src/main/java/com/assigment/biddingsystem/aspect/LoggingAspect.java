package com.assigment.biddingsystem.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


    @Before("execution(* com.assigment.biddingsystem.service.*.*(..))||execution(* com.assigment.biddingsystem.controller.*.*(..))")
    public void logging(JoinPoint joinPoint) throws Throwable {
        logger.info("****LoggingAspect : " + joinPoint.getSignature().getName() + ": Before Method Execution");

    }


    @After("execution(* com.assigment.biddingsystem.service.*.*(..))||execution(* com.assigment.biddingsystem.controller.*.*(..))")
    public void loggingAfter(JoinPoint joinPoint) throws Throwable {
        logger.info("****LoggingAspect : " + joinPoint.getSignature().getName() + ": After Method Execution");

    }


}