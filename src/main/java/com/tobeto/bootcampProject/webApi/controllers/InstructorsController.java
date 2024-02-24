package com.tobeto.bootcampProject.webApi.controllers;

import com.tobeto.bootcampProject.business.abstracts.InstructorService;
import com.tobeto.bootcampProject.business.requests.create.instructor.CreateInstructorRequest;
import com.tobeto.bootcampProject.business.requests.update.instructor.UpdateInstructorRequest;
import com.tobeto.bootcampProject.core.utilities.paging.PageDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instructors")
@AllArgsConstructor
public class InstructorsController extends BaseController {

    private InstructorService instructorService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CreateInstructorRequest request) {
        return handleDataResult(instructorService.add(request));
    }

    @GetMapping("getall")
    public ResponseEntity<?> getAll() {
        return handleDataResult(instructorService.getAll());
    }

    @GetMapping("getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return handleDataResult(instructorService.getById(id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return handleResult(instructorService.delete(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody UpdateInstructorRequest request, @PathVariable int id) {
        return handleDataResult(instructorService.update(request, id));
    }

    @GetMapping("sort")
    public ResponseEntity<?> getAllPage(@RequestBody PageDto pageDto) {
        return handleDataResult(instructorService.getAllPage(pageDto));
    }

}
