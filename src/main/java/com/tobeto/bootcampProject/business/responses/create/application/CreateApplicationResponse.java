package com.tobeto.bootcampProject.business.responses.create.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateApplicationResponse {
    private int id;
    private int applicantId;
    private int bootcampId;
    private int applicationStateId;
}
