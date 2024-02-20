package com.tobeto.bootcampProject.business.concretes;

import com.tobeto.bootcampProject.business.abstracts.EmployeeService;
import com.tobeto.bootcampProject.business.requests.create.employee.CreateEmployeeRequest;
import com.tobeto.bootcampProject.business.requests.update.employee.UpdateEmployeeRequest;
import com.tobeto.bootcampProject.business.responses.create.employee.CreateEmployeeResponse;
import com.tobeto.bootcampProject.business.responses.delete.employee.DeleteEmployeeResponse;
import com.tobeto.bootcampProject.business.responses.get.employee.GetAllEmployeeResponse;
import com.tobeto.bootcampProject.business.responses.get.employee.GetEmployeeResponse;
import com.tobeto.bootcampProject.business.responses.update.employee.UpdateEmployeeResponse;
import com.tobeto.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.tobeto.bootcampProject.dataAccess.abstracts.EmployeeRepository;
import com.tobeto.bootcampProject.entities.concretes.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapperService mapperService;

    @Override
    public CreateEmployeeResponse add(CreateEmployeeRequest request) {
        Employee employee = mapperService.forRequest().map(request, Employee.class);
        employeeRepository.save(employee);

        CreateEmployeeResponse response = mapperService.forResponse().
                map(employee, CreateEmployeeResponse.class);

        return response;
    }

    @Override
    public List<GetAllEmployeeResponse> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<GetAllEmployeeResponse> employeeResponses =
                employees.stream().map(employee -> mapperService.forResponse()
                        .map(employee, GetAllEmployeeResponse.class)).collect(Collectors.toList());

        return employeeResponses;
    }

    @Override
    public GetEmployeeResponse getById(int id) {
        Employee employee = employeeRepository.getById(id);
        GetEmployeeResponse response = mapperService.forResponse()
                .map(employee, GetEmployeeResponse.class);
        return response;
    }

    @Override
    public DeleteEmployeeResponse delete(int id) {
        employeeRepository.deleteById(id);
        DeleteEmployeeResponse response = new DeleteEmployeeResponse("Applicant deleted.");
        return response;
    }

    @Override
    public UpdateEmployeeResponse update(UpdateEmployeeRequest request, int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();

        Employee updatedEmployee = mapperService.forRequest().map(request, Employee.class);
        employee.setId(id);
        employee.setFirstName(updatedEmployee.getFirstName() != null ? updatedEmployee.getFirstName() : employee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName() != null ? updatedEmployee.getLastName() : employee.getLastName());
        employee.setPosition(updatedEmployee.getPosition() != null ? updatedEmployee.getPosition() : employee.getPosition());
        employee.setUserName(updatedEmployee.getUserName() != null ? updatedEmployee.getUserName() : employee.getUserName());
        employee.setNationalIdentity(updatedEmployee.getNationalIdentity() != null ? updatedEmployee.getNationalIdentity() : employee.getNationalIdentity());
        employee.setDateOfBirth((updatedEmployee.getDateOfBirth() != null ? updatedEmployee.getDateOfBirth() : employee.getDateOfBirth()));

        employeeRepository.save(employee);
        UpdateEmployeeResponse response = mapperService.forResponse().map(employee, UpdateEmployeeResponse.class);

        return response;
    }
}
