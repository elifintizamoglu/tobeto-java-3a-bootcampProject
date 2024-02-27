package com.tobeto.bootcampProject.business.responses.get.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllEmployeeResponse {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String position;
}
