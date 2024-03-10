package com.tobeto.bootcampProject.core.aspects.logging;

import com.tobeto.bootcampProject.core.crossCuttingConcerns.logging.LogParameter;
import com.tobeto.bootcampProject.core.crossCuttingConcerns.logging.LoggerServiceBase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class LogAspect {

    private final LoggerServiceBase loggerServiceBase;
    private HttpServletRequest httpServletRequest;

    @Autowired
    public LogAspect(LoggerServiceBase loggerServiceBase,
                     HttpServletRequest httpServletRequest) {
        this.loggerServiceBase = loggerServiceBase;
        this.httpServletRequest = httpServletRequest;
    }

    @Pointcut("within(@org.springframework.stereotype.Repository *)"
            + " || within(@org.springframework.stereotype.Service *)"
            + " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {

    }


    @Around("springBeanPointcut() && @annotation(com.tobeto.bootcampProject.core.aspects.logging.Loggable)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        Object result = joinPoint.proceed();

        String methodName = joinPoint.getSignature().getDeclaringTypeName()
                + "." + joinPoint.getSignature().getName();

        List<LogParameter> logParameters = new ArrayList<>();

        logParameters.add(new LogParameter(result));

        String userName = getUserName();
        loggerServiceBase.log(methodName, logParameters, userName);
        return result;

    }


    private String getUserName() {
        if (httpServletRequest.getUserPrincipal() != null) {
            return httpServletRequest.getUserPrincipal().getName();
        } else {
            return "?";
        }
    }


}
