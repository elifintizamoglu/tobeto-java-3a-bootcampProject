package com.tobeto.bootcampProject.webApi.controllers;

import com.tobeto.bootcampProject.business.abstracts.EmployeeService;
import com.tobeto.bootcampProject.business.requests.create.employee.CreateEmployeeRequest;
import com.tobeto.bootcampProject.business.requests.update.employee.UpdateEmployeeRequest;
import com.tobeto.bootcampProject.business.responses.create.employee.CreateEmployeeResponse;
import com.tobeto.bootcampProject.business.responses.delete.employee.DeleteEmployeeResponse;
import com.tobeto.bootcampProject.business.responses.get.employee.GetAllEmployeeResponse;
import com.tobeto.bootcampProject.business.responses.get.employee.GetEmployeeResponse;
import com.tobeto.bootcampProject.business.responses.update.employee.UpdateEmployeeResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping
    public CreateEmployeeResponse add(@RequestBody CreateEmployeeRequest request) {
        CreateEmployeeResponse result = employeeService.add(request);
        return result;
    }

    @GetMapping("getAll")
    public List<GetAllEmployeeResponse> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("getById/{id}")
    public GetEmployeeResponse getById(@PathVariable int id) {
        return employeeService.getById(id);
    }

    @DeleteMapping("delete/{id}")
    public DeleteEmployeeResponse delete(@PathVariable int id) {
        return employeeService.delete(id);
    }

    @PutMapping("update/{id}")
    public UpdateEmployeeResponse update(@RequestBody UpdateEmployeeRequest request, @PathVariable int id) {
        return employeeService.update(request, id);
    }
}
