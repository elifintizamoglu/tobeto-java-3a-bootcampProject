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
@Table(name = "instructors")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "user_id")
public class Instructor extends User {

    @Column(name = "companyName")
    private String companyName;

    @OneToMany(mappedBy = "instructor")
    private List<Bootcamp> bootcamps;
}
