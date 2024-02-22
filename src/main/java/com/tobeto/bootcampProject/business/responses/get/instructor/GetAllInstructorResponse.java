package com.tobeto.bootcampProject.business.responses.get.instructor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllInstructorResponse {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String companyName;
}
