package com.tobeto.bootcampProject.entities.concretes;

import com.tobeto.bootcampProject.core.entities.BaseEntity;
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
@Table(name = "applicationStates")
@EqualsAndHashCode(callSuper = true)
public class ApplicationState extends BaseEntity<Integer> {

    @Column(name = "name")
    private String name;
}
