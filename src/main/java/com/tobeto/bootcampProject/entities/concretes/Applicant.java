package com.tobeto.bootcampProject.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "applicants")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id")
public class Applicant extends User {

    @Column(name = "about")
    private String about;

    @OneToOne(mappedBy = "applicant", cascade = CascadeType.REMOVE)
    private Blacklist blacklist;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.REMOVE)
    private List<Application> applications;
}
