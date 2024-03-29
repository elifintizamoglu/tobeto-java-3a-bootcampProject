package com.tobeto.bootcampProject.business.concretes;

import com.tobeto.bootcampProject.business.abstracts.InstructorService;
import com.tobeto.bootcampProject.business.constants.InstructorMessages;
import com.tobeto.bootcampProject.business.requests.create.instructor.CreateInstructorRequest;
import com.tobeto.bootcampProject.business.requests.update.instructor.UpdateInstructorRequest;
import com.tobeto.bootcampProject.business.responses.create.instructor.CreateInstructorResponse;
import com.tobeto.bootcampProject.business.responses.get.instructor.GetAllInstructorResponse;
import com.tobeto.bootcampProject.business.responses.get.instructor.GetInstructorResponse;
import com.tobeto.bootcampProject.business.responses.update.instructor.UpdateInstructorResponse;
import com.tobeto.bootcampProject.business.rules.BootcampBusinessRules;
import com.tobeto.bootcampProject.business.rules.UserBusinessRules;
import com.tobeto.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.tobeto.bootcampProject.core.utilities.paging.PageDto;
import com.tobeto.bootcampProject.core.utilities.results.DataResult;
import com.tobeto.bootcampProject.core.utilities.results.Result;
import com.tobeto.bootcampProject.core.utilities.results.SuccessDataResult;
import com.tobeto.bootcampProject.core.utilities.results.SuccessResult;
import com.tobeto.bootcampProject.dataAccess.abstracts.InstructorRepository;
import com.tobeto.bootcampProject.entities.concretes.Instructor;
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
public class InstructorManager implements InstructorService {

    private InstructorRepository instructorRepository;
    private ModelMapperService mapperService;
    private UserBusinessRules userBusinessRules;
    private BootcampBusinessRules bootcampBusinessRules;

    @Override
    public DataResult<CreateInstructorResponse> add(CreateInstructorRequest request) {

        userBusinessRules.checkIfEmailExists(request.getEmail());

        Instructor instructor = mapperService.forRequest().map(request, Instructor.class);
        instructor.setCreatedDate(LocalDateTime.now());
        instructorRepository.save(instructor);

        CreateInstructorResponse response = mapperService.forResponse()
                .map(instructor, CreateInstructorResponse.class);

        return new SuccessDataResult<CreateInstructorResponse>(response, InstructorMessages.InstructorAdded);
    }

    @Override
    public DataResult<List<GetAllInstructorResponse>> getAll() {

        List<Instructor> instructors = instructorRepository.findAll();
        List<GetAllInstructorResponse> instructorResponses = instructors.stream()
                .map(instructor -> mapperService.forResponse()
                        .map(instructor, GetAllInstructorResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<GetAllInstructorResponse>>
                (instructorResponses, InstructorMessages.AllInstructorsListed);
    }

    @Override
    public DataResult<GetInstructorResponse> getById(int id) {

        Instructor instructor = instructorRepository.getById(id);
        GetInstructorResponse response = mapperService.forResponse()
                .map(instructor, GetInstructorResponse.class);

        return new SuccessDataResult<GetInstructorResponse>
                (response, InstructorMessages.InstructorListed);
    }

    @Override
    public Result delete(int id) {

        Instructor instructor = instructorRepository.getById(id);
        bootcampBusinessRules.checkIfInstructorHasBootcamps(id);
        instructorRepository.delete(instructor);

        return new SuccessResult(InstructorMessages.InstructorDeleted);
    }

    @Override
    public DataResult<UpdateInstructorResponse> update(UpdateInstructorRequest request) {

        Instructor instructor = instructorRepository.findById(request.getId()).orElseThrow();
        Instructor updatedInstructor = mapperService.forRequest().map(request, Instructor.class);

        instructor.setFirstName(updatedInstructor.getFirstName() != null ? updatedInstructor.getFirstName() : instructor.getFirstName());
        instructor.setLastName(updatedInstructor.getLastName() != null ? updatedInstructor.getLastName() : instructor.getLastName());
        instructor.setCompanyName(updatedInstructor.getCompanyName() != null ? updatedInstructor.getCompanyName() : instructor.getCompanyName());
        instructor.setUserName(updatedInstructor.getUserName() != null ? updatedInstructor.getUserName() : instructor.getUserName());
        instructor.setEmail(updatedInstructor.getEmail() != null ? updatedInstructor.getEmail() : instructor.getEmail());
        instructor.setNationalIdentity(updatedInstructor.getNationalIdentity() != null ? updatedInstructor.getNationalIdentity() : instructor.getNationalIdentity());
        instructor.setDateOfBirth((updatedInstructor.getDateOfBirth() != null ? updatedInstructor.getDateOfBirth() : instructor.getDateOfBirth()));
        instructor.setUpdatedDate(LocalDateTime.now());
        instructorRepository.save(instructor);

        UpdateInstructorResponse response = mapperService.forResponse()
                .map(instructor, UpdateInstructorResponse.class);

        return new SuccessDataResult<UpdateInstructorResponse>
                (response, InstructorMessages.InstructorUpdated);
    }

    @Override
    public DataResult<List<GetAllInstructorResponse>> getAllPage(PageDto pageDto) {

        Sort sort = Sort.by(Sort.Direction.fromString(pageDto.getSortDirection()), pageDto.getSortBy());
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize(), sort);
        Page<Instructor> instructors = instructorRepository.findAll(pageable);

        List<GetAllInstructorResponse> responses = instructors.stream()
                .map(instructor -> mapperService.forResponse()
                        .map(instructor, GetAllInstructorResponse.class)).toList();

        return new SuccessDataResult<List<GetAllInstructorResponse>>(responses);
    }
}
