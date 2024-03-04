package com.tobeto.bootcampProject.business.concretes;

import com.tobeto.bootcampProject.business.abstracts.EmployeeService;
import com.tobeto.bootcampProject.business.constants.EmployeeMessages;
import com.tobeto.bootcampProject.business.requests.create.employee.CreateEmployeeRequest;
import com.tobeto.bootcampProject.business.requests.update.employee.UpdateEmployeeRequest;
import com.tobeto.bootcampProject.business.responses.create.employee.CreateEmployeeResponse;
import com.tobeto.bootcampProject.business.responses.get.employee.GetAllEmployeeResponse;
import com.tobeto.bootcampProject.business.responses.get.employee.GetEmployeeResponse;
import com.tobeto.bootcampProject.business.responses.update.employee.UpdateEmployeeResponse;
import com.tobeto.bootcampProject.business.rules.UserBusinessRules;
import com.tobeto.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.tobeto.bootcampProject.core.utilities.paging.PageDto;
import com.tobeto.bootcampProject.core.utilities.results.DataResult;
import com.tobeto.bootcampProject.core.utilities.results.Result;
import com.tobeto.bootcampProject.core.utilities.results.SuccessDataResult;
import com.tobeto.bootcampProject.core.utilities.results.SuccessResult;
import com.tobeto.bootcampProject.dataAccess.abstracts.EmployeeRepository;
import com.tobeto.bootcampProject.entities.concretes.Employee;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapperService mapperService;
    private UserBusinessRules userBusinessRules;
    @Override
    public DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest request) {

        userBusinessRules.checkIfEmailExists(request.getEmail());

        Employee employee = mapperService.forRequest().map(request, Employee.class);
        employee.setCreatedDate(LocalDateTime.now());
        employeeRepository.save(employee);

        CreateEmployeeResponse response = mapperService.forResponse().
                map(employee, CreateEmployeeResponse.class);

        return new SuccessDataResult<CreateEmployeeResponse>
                (response, EmployeeMessages.EmployeeAdded);
    }

    @Override
    public DataResult<List<GetAllEmployeeResponse>> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<GetAllEmployeeResponse> employeeResponses = employees.stream()
                .map(employee -> mapperService.forResponse()
                .map(employee, GetAllEmployeeResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<GetAllEmployeeResponse>>
                (employeeResponses, EmployeeMessages.AllEmployeesListed);
    }

    @Override
    public DataResult<GetEmployeeResponse> getById(int id) {

        Employee employee = employeeRepository.getById(id);
        GetEmployeeResponse response = mapperService.forResponse()
                .map(employee, GetEmployeeResponse.class);

        return new SuccessDataResult<GetEmployeeResponse>
                (response, EmployeeMessages.EmployeeListed);
    }

    @Override
    public Result delete(int id) {

        Employee employee = employeeRepository.getById(id);
        employeeRepository.delete(employee);

        return new SuccessResult(EmployeeMessages.EmployeeDeleted);
    }

    @Override
    public DataResult<UpdateEmployeeResponse> update(UpdateEmployeeRequest request) {

        Employee employee = employeeRepository.findById(request.getId()).orElseThrow();
        Employee updatedEmployee = mapperService.forRequest().map(request, Employee.class);

        employee.setFirstName(updatedEmployee.getFirstName() != null ? updatedEmployee.getFirstName() : employee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName() != null ? updatedEmployee.getLastName() : employee.getLastName());
        employee.setPosition(updatedEmployee.getPosition() != null ? updatedEmployee.getPosition() : employee.getPosition());
        employee.setUserName(updatedEmployee.getUserName() != null ? updatedEmployee.getUserName() : employee.getUserName());
        employee.setEmail(updatedEmployee.getEmail() != null ? updatedEmployee.getEmail() : employee.getEmail());
        employee.setNationalIdentity(updatedEmployee.getNationalIdentity() != null ? updatedEmployee.getNationalIdentity() : employee.getNationalIdentity());
        employee.setDateOfBirth((updatedEmployee.getDateOfBirth() != null ? updatedEmployee.getDateOfBirth() : employee.getDateOfBirth()));
        employee.setUpdatedDate(LocalDateTime.now());
        employeeRepository.save(employee);

        UpdateEmployeeResponse response = mapperService.forResponse()
                .map(employee, UpdateEmployeeResponse.class);

        return new SuccessDataResult<UpdateEmployeeResponse>
                (response, EmployeeMessages.EmployeeUpdated);
    }

    @Override
    public DataResult<List<GetAllEmployeeResponse>> getAllPage(PageDto pageDto) {

        Sort sort = Sort.by(Sort.Direction.fromString(pageDto.getSortDirection()), pageDto.getSortBy());
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize(), sort);
        Page<Employee> employees = employeeRepository.findAll(pageable);

        List<GetAllEmployeeResponse> responses = employees.stream()
                .map(employee -> mapperService.forResponse()
                        .map(employee, GetAllEmployeeResponse.class)).toList();

        return new SuccessDataResult<List<GetAllEmployeeResponse>>(responses);
    }
}
