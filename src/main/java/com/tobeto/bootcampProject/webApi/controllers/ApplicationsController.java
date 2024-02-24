package com.tobeto.bootcampProject.webApi.controllers;

import com.tobeto.bootcampProject.business.abstracts.ApplicationService;
import com.tobeto.bootcampProject.business.requests.create.application.CreateApplicationRequest;
import com.tobeto.bootcampProject.business.requests.update.application.UpdateApplicationRequest;
import com.tobeto.bootcampProject.core.utilities.paging.PageDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@AllArgsConstructor
public class ApplicationsController extends BaseController {

    private ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CreateApplicationRequest request) {
        return handleDataResult(applicationService.add(request));
    }

    @GetMapping("getall")
    public ResponseEntity<?> getAll() {
        return handleDataResult(applicationService.getAll());
    }

    @GetMapping("getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return handleDataResult(applicationService.getById(id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return handleResult(applicationService.delete(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody UpdateApplicationRequest request, @PathVariable int id) {
        return handleDataResult(applicationService.update(request, id));
    }

    @GetMapping("sort")
    public ResponseEntity<?> getAllPage(@RequestBody PageDto pageDto){
        return handleDataResult(applicationService.getAllPage(pageDto));
    }
}
