package com.tobeto.bootcampProject.business.abstracts;

import com.tobeto.bootcampProject.business.requests.create.employee.CreateEmployeeRequest;
import com.tobeto.bootcampProject.business.requests.update.employee.UpdateEmployeeRequest;
import com.tobeto.bootcampProject.business.responses.create.employee.CreateEmployeeResponse;
import com.tobeto.bootcampProject.business.responses.delete.employee.DeleteEmployeeResponse;
import com.tobeto.bootcampProject.business.responses.get.employee.GetAllEmployeeResponse;
import com.tobeto.bootcampProject.business.responses.get.employee.GetEmployeeResponse;
import com.tobeto.bootcampProject.business.responses.update.employee.UpdateEmployeeResponse;

import java.util.List;

public interface EmployeeService {
    CreateEmployeeResponse add(CreateEmployeeRequest request);

    List<GetAllEmployeeResponse> getAll();

    GetEmployeeResponse getById(int id);

    DeleteEmployeeResponse delete(int id);

    UpdateEmployeeResponse update(UpdateEmployeeRequest request, int id);
}
