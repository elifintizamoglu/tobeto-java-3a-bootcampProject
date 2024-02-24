package com.tobeto.bootcampProject.webApi.controllers;

import com.tobeto.bootcampProject.business.abstracts.EmployeeService;
import com.tobeto.bootcampProject.business.requests.create.employee.CreateEmployeeRequest;
import com.tobeto.bootcampProject.business.requests.update.employee.UpdateEmployeeRequest;
import com.tobeto.bootcampProject.core.utilities.paging.PageDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeesController extends BaseController {

    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CreateEmployeeRequest request) {
        return handleDataResult(employeeService.add(request));
    }

    @GetMapping("getall")
    public ResponseEntity<?> getAll() {
        return handleDataResult(employeeService.getAll());
    }

    @GetMapping("getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return handleDataResult(employeeService.getById(id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return handleResult(employeeService.delete(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody UpdateEmployeeRequest request, @PathVariable int id) {
        return handleDataResult(employeeService.update(request, id));
    }

    @GetMapping("sort")
    public ResponseEntity<?> getAllPage(@RequestBody PageDto pageDto) {
        return handleDataResult(employeeService.getAllPage(pageDto));
    }
}
