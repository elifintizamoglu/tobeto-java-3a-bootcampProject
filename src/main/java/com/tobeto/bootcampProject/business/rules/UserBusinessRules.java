package com.tobeto.bootcampProject.business.rules;

import com.tobeto.bootcampProject.core.exceptions.types.BusinessException;
import com.tobeto.bootcampProject.dataAccess.abstracts.UserRepository;
import com.tobeto.bootcampProject.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRules {

    private UserRepository userRepository;

    public void checkIfEmailExists(String email) {
        User user = userRepository.getByEmail(email.trim());
        if (user != null) {
            throw new BusinessException("This email is already used!");
        }
    }
}
