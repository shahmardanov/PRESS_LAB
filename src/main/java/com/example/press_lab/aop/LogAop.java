package com.example.press_lab.aop;


import com.example.press_lab.annotation.ConsoleLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LogAop {

    @Around("@annotation(com.example.press_lab.annotation.ConsoleLog)")
    public Object consoleLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        var startTime = System.currentTimeMillis();
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();

        String[] parameterNames = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        Object[] args = proceedingJoinPoint.getArgs();

        var className = String.valueOf(method.getDeclaringClass());
        var methodName = method.getName();

        log.info("{}.{} is started", className, methodName);
        ConsoleLog annotation = method.getAnnotation(ConsoleLog.class);
        if (annotation.printArgs()) {
            for (int i = 0, j = 0; i < parameterNames.length && j < args.length; i++, j++) {
                log.info("Request info {}-{}", parameterNames[i], args[j]);
            }
        }

        Object proceed = proceedingJoinPoint.proceed();

        var endTime = System.currentTimeMillis();
        log.info("{}.{} is completed in {} millisecond", className, methodName, (endTime - startTime));

        return proceed;
    }


    private static final Logger logger = LoggerFactory.getLogger(LogAop.class);

    @Around("@annotation(com.example.press_lab.annotation.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        String methodName = joinPoint.getSignature().getName();
        logger.info("Method {} is starting", methodName);

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;
        logger.info("Method {} executed in {} ms", methodName, executionTime);

        return proceed;
    }





}

