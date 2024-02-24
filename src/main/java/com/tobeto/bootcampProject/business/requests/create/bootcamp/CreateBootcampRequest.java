package com.tobeto.bootcampProject.business.requests.create.bootcamp;

import com.tobeto.bootcampProject.entities.concretes.BootcampState;
import com.tobeto.bootcampProject.entities.concretes.Instructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBootcampRequest {
    private String name;
    private Instructor instructor;
    private LocalDate startDate;
    private LocalDate endDate;
    private BootcampState bootcampState;
}
