package com.tobeto.bootcampProject.business.responses.create.applicationState;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateApplicationStateResponse {
    private int id;
    private String name;
}
