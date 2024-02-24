package com.tobeto.bootcampProject.business.concretes;

import com.tobeto.bootcampProject.business.abstracts.ApplicantService;
import com.tobeto.bootcampProject.business.constants.ApplicantMessages;
import com.tobeto.bootcampProject.business.requests.create.applicant.CreateApplicantRequest;
import com.tobeto.bootcampProject.business.requests.update.applicant.UpdateApplicantRequest;
import com.tobeto.bootcampProject.business.responses.create.applicant.CreateApplicantResponse;
import com.tobeto.bootcampProject.business.responses.get.applicant.GetAllApplicantResponse;
import com.tobeto.bootcampProject.business.responses.get.applicant.GetApplicantResponse;
import com.tobeto.bootcampProject.business.responses.update.applicant.UpdateApplicantResponse;
import com.tobeto.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.tobeto.bootcampProject.core.utilities.results.DataResult;
import com.tobeto.bootcampProject.core.utilities.results.Result;
import com.tobeto.bootcampProject.core.utilities.results.SuccessDataResult;
import com.tobeto.bootcampProject.core.utilities.results.SuccessResult;
import com.tobeto.bootcampProject.dataAccess.abstracts.ApplicantRepository;
import com.tobeto.bootcampProject.entities.concretes.Applicant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicantManager implements ApplicantService {

    private ApplicantRepository applicantRepository;
    private ModelMapperService mapperService;

    @Override
    public DataResult<CreateApplicantResponse> add(CreateApplicantRequest request) {

        Applicant applicant = mapperService.forRequest().map(request, Applicant.class);
        applicantRepository.save(applicant);
        CreateApplicantResponse response = mapperService.forResponse().map(applicant, CreateApplicantResponse.class);

        return new SuccessDataResult<CreateApplicantResponse>(response, ApplicantMessages.ApplicantAdded);
    }

    @Override
    public DataResult<List<GetAllApplicantResponse>> getAll() {

        List<Applicant> applicants = applicantRepository.findAll();
        List<GetAllApplicantResponse> applicantResponses = applicants.stream().map(applicant -> mapperService.forResponse()
                .map(applicant, GetAllApplicantResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<GetAllApplicantResponse>>(applicantResponses, ApplicantMessages.AllApplicantsListed);
    }

    @Override
    public DataResult<GetApplicantResponse> getById(int id) {
        Applicant applicant = applicantRepository.getById(id);
        GetApplicantResponse response = mapperService.forResponse()
                .map(applicant, GetApplicantResponse.class);
        return new SuccessDataResult<GetApplicantResponse>(response, ApplicantMessages.ApplicantListed);
    }

    @Override
    public Result delete(int id) {
        Applicant applicant = applicantRepository.getById(id);
        applicantRepository.delete(applicant);
        return new SuccessResult(ApplicantMessages.ApplicantDeleted);
    }

    @Override
    public DataResult<UpdateApplicantResponse> update(UpdateApplicantRequest request, int id) {

        Applicant applicant = applicantRepository.findById(id).orElseThrow();
        Applicant updatedApplicant = mapperService.forRequest().map(request, Applicant.class);

        applicant.setFirstName(updatedApplicant.getFirstName() != null ? updatedApplicant.getFirstName() : applicant.getFirstName());
        applicant.setLastName(updatedApplicant.getLastName() != null ? updatedApplicant.getLastName() : applicant.getLastName());
        applicant.setAbout(updatedApplicant.getAbout() != null ? updatedApplicant.getAbout() : applicant.getAbout());
        applicant.setUserName(updatedApplicant.getUserName() != null ? updatedApplicant.getUserName() : applicant.getUserName());
        applicant.setNationalIdentity(updatedApplicant.getNationalIdentity() != null ? updatedApplicant.getNationalIdentity() : applicant.getNationalIdentity());
        applicant.setDateOfBirth((updatedApplicant.getDateOfBirth() != null ? updatedApplicant.getDateOfBirth() : applicant.getDateOfBirth()));
        applicantRepository.save(applicant);

        UpdateApplicantResponse response = mapperService.forResponse().map(applicant, UpdateApplicantResponse.class);

        return new SuccessDataResult<UpdateApplicantResponse>(response, ApplicantMessages.ApplicantUpdated);
    }
}
