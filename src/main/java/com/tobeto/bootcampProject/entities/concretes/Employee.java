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
@Table(name = "employees")
@EqualsAndHashCode(callSuper = true)
public class Employee extends User<Integer> {

    @Column(name = "position")
    private String position;
}
