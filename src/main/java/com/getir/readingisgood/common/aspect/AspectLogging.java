package com.getir.readingisgood.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Aspect
@Configuration
@Slf4j
public class AspectLogging {

    @Pointcut("@annotation(com.getir.readingisgood.common.annotations.SaveEntityLogger)")
    public void saveEntityLoggerAnnotated() {}

    @Pointcut("@annotation(com.getir.readingisgood.common.annotations.UpdateEntityLogger)")
    public void updateEntityLoggerAnnotated() {}

    @Pointcut("@annotation(com.getir.readingisgood.common.annotations.QueryEntityLogger)")
    public void queryEntityLoggerAnnotated() {}

    @Pointcut("execution(public * * (..))")
    public void methodExecuted() {}

    @Pointcut("methodExecuted() && saveEntityLoggerAnnotated()")
    public void saveMethodExecuted() {}

    @Pointcut("methodExecuted() && updateEntityLoggerAnnotated()")
    public void updateMethodExecuted() {}

    @Pointcut("methodExecuted() && queryEntityLoggerAnnotated()")
    public void queryMethodExecuted() {}

    @AfterReturning("saveMethodExecuted()")
    public void logSave(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringType().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] requestParams = joinPoint.getArgs();
        log.info("Entity successfully added.");
        log.info("Class: " + className + " method: " + methodName + " params: " + Arrays.asList(requestParams));
    }

    @AfterReturning("updateMethodExecuted()")
    public void logUpdate(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringType().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] requestParams = joinPoint.getArgs();
        log.info("Entity successfully updated.");
        log.info("Class: " + className + " method: " + methodName + " params: " + Arrays.asList(requestParams));
    }

    @AfterReturning("queryMethodExecuted()")
    public void logQuery(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringType().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] requestParams = joinPoint.getArgs();
        log.info("Entity successfully queried.");
        log.info("Class: " + className + " method: " + methodName + " params: " + Arrays.asList(requestParams));
    }
}
