package com.example.press_lab.aop;


import com.example.press_lab.request.user.AuthenticateRequest;
import com.example.press_lab.request.user.JwtDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AuthenticationLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationLoggingAspect.class);

    @Pointcut("execution(* com.example.press_lab.service.user.UserCreateServiceImpl.authenticate(..))")
    public void authenticateMethod() {
        // TODO  SONRA Pointcut for authenticate
    }

    @Before("authenticateMethod()")
    public void logBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0 && args[0] instanceof AuthenticateRequest) {
            AuthenticateRequest request = (AuthenticateRequest) args[0];
            logger.info("Attempting to authenticate user with email: {}", request.getEmail());
        }
    }

    @AfterReturning(pointcut = "authenticateMethod()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        if (result instanceof JwtDto) {
            logger.info("User authenticated successfully. JWT token generated.");
        }
    }

    @AfterThrowing(pointcut = "authenticateMethod()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.warn("Authentication failed: {}", exception.getMessage());
    }


}
