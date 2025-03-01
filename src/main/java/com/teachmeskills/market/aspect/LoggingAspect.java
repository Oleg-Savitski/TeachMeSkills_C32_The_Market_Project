package com.teachmeskills.market.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Перехватывает все методы (*) в любом классе (*) в пакете com.teachmeskills.market.controller, независимо от возвращаемого типа (*) и независимо от параметров ((..)).
    @Around("execution(* com.teachmeskills.market.controller.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object proceed = joinPoint.proceed();
        stopWatch.stop();
        logger.info("Method called: {} | Execution time -> {} ms", joinPoint.getSignature().getName(), stopWatch.getTotalTimeMillis());

        return proceed;
    }
}