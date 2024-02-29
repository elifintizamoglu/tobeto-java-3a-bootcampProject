package com.tobeto.bootcampProject.business.concretes;

import com.tobeto.bootcampProject.business.abstracts.ApplicantService;
import com.tobeto.bootcampProject.business.constants.ApplicantMessages;
import com.tobeto.bootcampProject.business.requests.create.applicant.CreateApplicantRequest;
import com.tobeto.bootcampProject.business.requests.update.applicant.UpdateApplicantRequest;
import com.tobeto.bootcampProject.business.responses.create.applicant.CreateApplicantResponse;
import com.tobeto.bootcampProject.business.responses.get.applicant.GetAllApplicantResponse;
import com.tobeto.bootcampProject.business.responses.get.applicant.GetApplicantResponse;
import com.tobeto.bootcampProject.business.responses.update.applicant.UpdateApplicantResponse;
import com.tobeto.bootcampProject.business.rules.UserBusinessRules;
import com.tobeto.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.tobeto.bootcampProject.core.utilities.paging.PageDto;
import com.tobeto.bootcampProject.core.utilities.results.DataResult;
import com.tobeto.bootcampProject.core.utilities.results.Result;
import com.tobeto.bootcampProject.core.utilities.results.SuccessDataResult;
import com.tobeto.bootcampProject.core.utilities.results.SuccessResult;
import com.tobeto.bootcampProject.dataAccess.abstracts.ApplicantRepository;
import com.tobeto.bootcampProject.entities.concretes.Applicant;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicantManager implements ApplicantService {

    private ApplicantRepository applicantRepository;
    private ModelMapperService mapperService;
    private UserBusinessRules userBusinessRules;

    @Override
    public DataResult<CreateApplicantResponse> add(CreateApplicantRequest request) {

        userBusinessRules.checkIfEmailExists(request.getEmail());
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
    public DataResult<UpdateApplicantResponse> update(UpdateApplicantRequest request) {

        Applicant applicant = applicantRepository.findById(request.getId()).orElseThrow();
        Applicant updatedApplicant = mapperService.forRequest().map(request, Applicant.class);

        applicant.setFirstName(updatedApplicant.getFirstName() != null ? updatedApplicant.getFirstName() : applicant.getFirstName());
        applicant.setLastName(updatedApplicant.getLastName() != null ? updatedApplicant.getLastName() : applicant.getLastName());
        applicant.setAbout(updatedApplicant.getAbout() != null ? updatedApplicant.getAbout() : applicant.getAbout());
        applicant.setEmail(updatedApplicant.getEmail() != null ? updatedApplicant.getEmail() : applicant.getEmail());
        applicant.setUserName(updatedApplicant.getUserName() != null ? updatedApplicant.getUserName() : applicant.getUserName());
        applicant.setNationalIdentity(updatedApplicant.getNationalIdentity() != null ? updatedApplicant.getNationalIdentity() : applicant.getNationalIdentity());
        applicant.setDateOfBirth((updatedApplicant.getDateOfBirth() != null ? updatedApplicant.getDateOfBirth() : applicant.getDateOfBirth()));
        applicantRepository.save(applicant);

        UpdateApplicantResponse response = mapperService.forResponse().map(applicant, UpdateApplicantResponse.class);

        return new SuccessDataResult<UpdateApplicantResponse>(response, ApplicantMessages.ApplicantUpdated);
    }

    @Override
    public DataResult<List<GetAllApplicantResponse>> getAllPage(PageDto pageDto) {
        Sort sort = Sort.by(Sort.Direction.fromString(pageDto.getSortDirection()), pageDto.getSortBy());
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize(), sort);
        Page<Applicant> applicants = applicantRepository.findAll(pageable);
        List<GetAllApplicantResponse> responses = applicants.stream().map(applicant -> mapperService.forResponse().map(applicant, GetAllApplicantResponse.class)).toList();
        return new SuccessDataResult<List<GetAllApplicantResponse>>(responses);
    }
}
