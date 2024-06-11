package com.w2m.challenge.aop;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
    private static final Logger LOGGER = LogManager.getLogger(LoggerAspect.class);
    @Before("execution(* com.w2m.challenge.controller.SpaceshipController.getById(..))")
    public void logNegativeIds(JoinPoint joinPoint) {

        Long id = (Long)joinPoint.getArgs()[0];
        if (id < 0){
            LOGGER.info("NEGATIVE ID: {}", id);
        }
    }
}
