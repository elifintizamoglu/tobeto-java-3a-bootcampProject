package com.tobeto.bootcampProject.business.requests.create.bootcamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBootcampRequest {
    private String name;
    private int instructorId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int bootcampStateId;
}
