package com.tobeto.bootcampProject.business.rules;

import com.tobeto.bootcampProject.core.exceptions.types.BusinessException;
import com.tobeto.bootcampProject.dataAccess.abstracts.ApplicationRepository;
import com.tobeto.bootcampProject.entities.concretes.Application;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationBusinessRules {

    private ApplicationRepository applicationRepository;

    public void checkIfApplicationStateInUse(int id) {
        List<Application> applications = applicationRepository.getAllByApplicationStateId(id);
        if (!applications.isEmpty()) {
            throw new BusinessException("This application state has application(s) and can not be deleted!");
        }
    }
}
