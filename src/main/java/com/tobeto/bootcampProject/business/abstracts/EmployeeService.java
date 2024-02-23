package com.tobeto.bootcampProject.business.abstracts;

import com.tobeto.bootcampProject.business.requests.create.employee.CreateEmployeeRequest;
import com.tobeto.bootcampProject.business.requests.update.employee.UpdateEmployeeRequest;
import com.tobeto.bootcampProject.business.responses.create.employee.CreateEmployeeResponse;
import com.tobeto.bootcampProject.business.responses.get.employee.GetAllEmployeeResponse;
import com.tobeto.bootcampProject.business.responses.get.employee.GetEmployeeResponse;
import com.tobeto.bootcampProject.business.responses.update.employee.UpdateEmployeeResponse;
import com.tobeto.bootcampProject.core.utilities.results.DataResult;
import com.tobeto.bootcampProject.core.utilities.results.Result;

import java.util.List;

public interface EmployeeService {
    DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest request);

    DataResult<List<GetAllEmployeeResponse>> getAll();

    DataResult<GetEmployeeResponse> getById(int id);

    Result delete(int id);

    DataResult<UpdateEmployeeResponse> update(UpdateEmployeeRequest request, int id);
}