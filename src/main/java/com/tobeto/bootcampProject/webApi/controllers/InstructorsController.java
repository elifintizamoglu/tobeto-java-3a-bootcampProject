package com.tobeto.bootcampProject.webApi.controllers;

import com.tobeto.bootcampProject.business.abstracts.InstructorService;
import com.tobeto.bootcampProject.business.requests.create.instructor.CreateInstructorRequest;
import com.tobeto.bootcampProject.business.requests.update.instructor.UpdateInstructorRequest;
import com.tobeto.bootcampProject.business.responses.create.instructor.CreateInstructorResponse;
import com.tobeto.bootcampProject.business.responses.delete.instructor.DeleteInstructorResponse;
import com.tobeto.bootcampProject.business.responses.get.instructor.GetAllInstructorResponse;
import com.tobeto.bootcampProject.business.responses.get.instructor.GetInstructorResponse;
import com.tobeto.bootcampProject.business.responses.update.instructor.UpdateInstructorResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
@AllArgsConstructor
public class InstructorsController {

    private InstructorService instructorService;

    @PostMapping
    public CreateInstructorResponse add(@RequestBody CreateInstructorRequest request){
        CreateInstructorResponse result = instructorService.add(request);
        return result;
    }
    @GetMapping("getAll")
    public List<GetAllInstructorResponse> getAll() {
        return instructorService.getAll();
    }

    @GetMapping("getById/{id}")
    public GetInstructorResponse getById(@PathVariable int id) {
        return instructorService.getById(id);
    }

    @DeleteMapping("delete/{id}")
    public DeleteInstructorResponse delete(@PathVariable int id) {
        return instructorService.delete(id);
    }

    @PutMapping("update/{id}")
    public UpdateInstructorResponse update(@RequestBody UpdateInstructorRequest request, @PathVariable int id) {
        return instructorService.update(request, id);
    }
}
