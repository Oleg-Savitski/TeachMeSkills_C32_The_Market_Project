package com.teachmeskills.market.aspect;

import com.teachmeskills.market.annotation.LeadTimed;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LeadTimedAspect {
    private static final Logger logger = LoggerFactory.getLogger(LeadTimedAspect.class);

    @Around("@annotation(leadTimed)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, LeadTimed leadTimed) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logger.info("Method {} executed in {} ms. Message -> {}", joinPoint.getSignature(), executionTime, leadTimed.value());
        return proceed;
    }
}