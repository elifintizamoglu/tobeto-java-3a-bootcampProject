package com.tobeto.bootcampProject.core.crossCuttingConcerns.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class LoggerServiceBase {

    protected Logger logger;

    public LoggerServiceBase(Class<?> logClass){
        this.logger = LoggerFactory.getLogger(logClass);
    }

    public void verbose(String message){
        logger.trace(message);
    }

    public void fatal(String message){
        logger.error(message);
    }

    public void info(String message){
        logger.info(message);
    }

    public void warn(String message){
        logger.warn(message);
    }

    public void debug(String message){
        logger.debug(message);
    }

    public void error(String message){
        logger.error(message);
    }

    public abstract void log(String methodName, List<LogParameter> logParameters, String userName);


}
