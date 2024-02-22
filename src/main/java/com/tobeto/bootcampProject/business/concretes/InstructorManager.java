package com.tobeto.bootcampProject.business.concretes;

import com.tobeto.bootcampProject.business.abstracts.InstructorService;
import com.tobeto.bootcampProject.business.requests.create.instructor.CreateInstructorRequest;
import com.tobeto.bootcampProject.business.requests.update.instructor.UpdateInstructorRequest;
import com.tobeto.bootcampProject.business.responses.create.instructor.CreateInstructorResponse;
import com.tobeto.bootcampProject.business.responses.delete.instructor.DeleteInstructorResponse;
import com.tobeto.bootcampProject.business.responses.get.instructor.GetAllInstructorResponse;
import com.tobeto.bootcampProject.business.responses.get.instructor.GetInstructorResponse;
import com.tobeto.bootcampProject.business.responses.update.instructor.UpdateInstructorResponse;
import com.tobeto.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.tobeto.bootcampProject.dataAccess.abstracts.InstructorRepository;
import com.tobeto.bootcampProject.entities.concretes.Instructor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InstructorManager implements InstructorService {

    private InstructorRepository instructorRepository;
    private ModelMapperService mapperService;

    @Override
    public CreateInstructorResponse add(CreateInstructorRequest request) {
        Instructor instructor = mapperService.forRequest().map(request, Instructor.class);
        instructorRepository.save(instructor);

        CreateInstructorResponse response = mapperService.forResponse()
                .map(instructor, CreateInstructorResponse.class);
        return response;
    }

    @Override
    public List<GetAllInstructorResponse> getAll() {
        List<Instructor> instructors = instructorRepository.findAll();
        List<GetAllInstructorResponse> instructorResponses =
                instructors.stream().map(instructor -> mapperService.forResponse()
                        .map(instructor, GetAllInstructorResponse.class)).collect(Collectors.toList());

        return instructorResponses;
    }

    @Override
    public GetInstructorResponse getById(int id) {
        Instructor instructor = instructorRepository.getById(id);
        GetInstructorResponse response = mapperService.forResponse()
                .map(instructor, GetInstructorResponse.class);
        return response;
    }

    @Override
    public DeleteInstructorResponse delete(int id) {
        instructorRepository.deleteById(id);
        DeleteInstructorResponse response = new DeleteInstructorResponse("Instructor deleted.");
        return response;
    }

    @Override
    public UpdateInstructorResponse update(UpdateInstructorRequest request, int id) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow();

        Instructor updatedInstructor = mapperService.forRequest().map(request, Instructor.class);
        instructor.setId(id);
        instructor.setFirstName(updatedInstructor.getFirstName() != null ? updatedInstructor.getFirstName() : instructor.getFirstName());
        instructor.setLastName(updatedInstructor.getLastName() != null ? updatedInstructor.getLastName() : instructor.getLastName());
        instructor.setCompanyName(updatedInstructor.getCompanyName() != null ? updatedInstructor.getCompanyName() : instructor.getCompanyName());
        instructor.setUserName(updatedInstructor.getUserName() != null ? updatedInstructor.getUserName() : instructor.getUserName());
        instructor.setNationalIdentity(updatedInstructor.getNationalIdentity() != null ? updatedInstructor.getNationalIdentity() : instructor.getNationalIdentity());
        instructor.setDateOfBirth((updatedInstructor.getDateOfBirth() != null ? updatedInstructor.getDateOfBirth() : instructor.getDateOfBirth()));

        instructorRepository.save(instructor);
        UpdateInstructorResponse response = mapperService.forResponse().map(instructor, UpdateInstructorResponse.class);

        return response;
    }
}
