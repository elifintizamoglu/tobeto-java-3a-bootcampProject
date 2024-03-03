package com.tobeto.bootcampProject.business.responses.get.bootcamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBootcampResponse {
    private int id;
    private String name;
    private String instructorFirstName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String bootcampStateName;
}
