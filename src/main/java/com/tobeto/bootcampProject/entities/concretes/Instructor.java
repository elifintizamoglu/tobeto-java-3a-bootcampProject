package com.tobeto.bootcampProject.entities.concretes;

import com.tobeto.bootcampProject.core.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instructors")
@EqualsAndHashCode(callSuper = true)
public class Instructor extends User<Integer> {

    @Column(name = "companyName")
    private String companyName;
}
