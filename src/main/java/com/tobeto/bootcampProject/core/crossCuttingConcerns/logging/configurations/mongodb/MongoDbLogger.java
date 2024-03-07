package com.tobeto.bootcampProject.core.crossCuttingConcerns.logging.configurations.mongodb;

import com.tobeto.bootcampProject.core.crossCuttingConcerns.logging.LogDetail;
import com.tobeto.bootcampProject.core.crossCuttingConcerns.logging.LogParameter;
import com.tobeto.bootcampProject.core.crossCuttingConcerns.logging.LoggerServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoDbLogger extends LoggerServiceBase {

    private MongoTemplate mongoTemplate;

    @Autowired
    public MongoDbLogger(MongoTemplate mongoTemplate) {
        super(MongoDbLogger.class);
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void log(String methodName, List<LogParameter> logParameters, String userName) {
        LogDetail logDetail = new LogDetail();
        logDetail.setMethodName(methodName);
        logDetail.setLogParameters(logParameters);
        logDetail.setUser(userName);
        info(logDetail.toString());
        mongoTemplate.save(logDetail);
    }
}