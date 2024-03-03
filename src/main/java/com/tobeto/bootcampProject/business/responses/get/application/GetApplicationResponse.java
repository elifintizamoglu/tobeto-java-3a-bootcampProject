package com.tobeto.bootcampProject.business.responses.get.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetApplicationResponse {
    private int id;
    private String applicantFirstName;
    private String bootcampName;
    private String applicationStateName;
}
