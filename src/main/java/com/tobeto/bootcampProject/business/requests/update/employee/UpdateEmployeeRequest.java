package com.tobeto.bootcampProject.business.requests.update.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequest {
    int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String userName;
    private String nationalIdentity;
    private LocalDate dateOfBirth;
    private String position;
}
