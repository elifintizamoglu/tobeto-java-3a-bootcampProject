package com.tobeto.bootcampProject.business.responses.update.applicant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateApplicantResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String about;
}
