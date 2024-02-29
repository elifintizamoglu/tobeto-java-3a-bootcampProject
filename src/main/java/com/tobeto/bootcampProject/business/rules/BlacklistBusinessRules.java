package com.tobeto.bootcampProject.business.rules;

import com.tobeto.bootcampProject.core.exceptions.types.BusinessException;
import com.tobeto.bootcampProject.dataAccess.abstracts.BlacklistRepository;
import com.tobeto.bootcampProject.entities.concretes.Blacklist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BlacklistBusinessRules {

    private BlacklistRepository blacklistRepository;

    public void checkIfApplicantInBlacklist(int id) {
        Blacklist blacklist = blacklistRepository.getByApplicantId(id);
        if (blacklist != null) {
            throw new BusinessException("This applicant is already in blacklist!");
        }
    }
}
