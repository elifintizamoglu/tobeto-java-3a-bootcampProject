package com.tobeto.bootcampProject.business.requests.create.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateApplicationRequest {
    private int applicantId;
    private int bootcampId;
    private int applicationStateId;
}
