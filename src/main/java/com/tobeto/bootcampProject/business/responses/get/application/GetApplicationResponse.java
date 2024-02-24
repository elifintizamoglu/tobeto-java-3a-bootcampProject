package com.tobeto.bootcampProject.business.responses.get.application;

import com.tobeto.bootcampProject.entities.concretes.Applicant;
import com.tobeto.bootcampProject.entities.concretes.ApplicationState;
import com.tobeto.bootcampProject.entities.concretes.Bootcamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetApplicationResponse {
    private int id;
    private Applicant applicant;
    private Bootcamp bootcamp;
    private ApplicationState applicationState;
}
