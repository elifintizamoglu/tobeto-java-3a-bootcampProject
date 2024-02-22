package com.tobeto.bootcampProject.entities.concretes;

import com.tobeto.bootcampProject.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bootcamps")
@EqualsAndHashCode(callSuper = true)
public class Bootcamp extends BaseEntity<Integer> {
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "instructorId")
    private Instructor instructor;

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "endDate")
    private  LocalDate endDate;

    @OneToOne
    @JoinColumn(name = "bootcampId")
    private BootcampState bootcampState;
}