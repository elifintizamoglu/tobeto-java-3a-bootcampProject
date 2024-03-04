package com.tobeto.bootcampProject.business.rules;

import com.tobeto.bootcampProject.core.exceptions.types.BusinessException;
import com.tobeto.bootcampProject.dataAccess.abstracts.BootcampRepository;
import com.tobeto.bootcampProject.entities.concretes.Bootcamp;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BootcampBusinessRules {
    private BootcampRepository bootcampRepository;

    public void checkIfInstructorHasBootcamps(int id) {
        List<Bootcamp> bootcamps = bootcampRepository.getAllByInstructorId(id);
        if (!bootcamps.isEmpty()) {
            throw new BusinessException("This instructor has bootcamp(s) and can not be deleted!");
        }
    }

    public void checkIfBootcampStateInUse(int id) {
        List<Bootcamp> bootcamps = bootcampRepository.getAllByBootcampStateId(id);
        if (!bootcamps.isEmpty()) {
            throw new BusinessException("This bootcamp state has bootcamp(s) and can not be deleted!");
        }
    }
}
