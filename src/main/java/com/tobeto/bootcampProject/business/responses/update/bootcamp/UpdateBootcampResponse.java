package com.tobeto.bootcampProject.business.responses.update.bootcamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBootcampResponse {
    private int id;
    private String name;
    private int instructorId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int bootcampStateId;
}
