package com.tobeto.bootcampProject.business.responses.create.applicant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateApplicantResponse {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String about;
}
