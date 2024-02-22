package com.tobeto.bootcampProject.entities.concretes;

import com.tobeto.bootcampProject.core.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "applications")
@EqualsAndHashCode(callSuper = true)
public class Application extends BaseEntity<Integer> {

    @OneToOne
    @JoinColumn(name = "applicantId")
    private Applicant applicant;

    @OneToOne
    @JoinColumn(name = "bootcampId")
    private Bootcamp bootcamp;

    @OneToOne
    @JoinColumn(name = "applicationStateId")
    private ApplicationState applicationState;
}
