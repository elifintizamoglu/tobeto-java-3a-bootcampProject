package com.tobeto.bootcampProject.dataAccess.abstracts;

import com.tobeto.bootcampProject.entities.concretes.Bootcamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BootcampRepository extends JpaRepository<Bootcamp, Integer> {
    Bootcamp getById(int id);

    List<Bootcamp> getAllByInstructorId(int id);

    List<Bootcamp> getAllByBootcampStateId(int id);
}
