package com.tobeto.bootcampProject.business.concretes;

import com.tobeto.bootcampProject.business.abstracts.ApplicationService;
import com.tobeto.bootcampProject.business.constants.ApplicationMessages;
import com.tobeto.bootcampProject.business.requests.create.application.CreateApplicationRequest;
import com.tobeto.bootcampProject.business.requests.update.application.UpdateApplicationRequest;
import com.tobeto.bootcampProject.business.responses.create.application.CreateApplicationResponse;
import com.tobeto.bootcampProject.business.responses.get.application.GetAllApplicationResponse;
import com.tobeto.bootcampProject.business.responses.get.application.GetApplicationResponse;
import com.tobeto.bootcampProject.business.responses.update.application.UpdateApplicationResponse;
import com.tobeto.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.tobeto.bootcampProject.core.utilities.results.DataResult;
import com.tobeto.bootcampProject.core.utilities.results.Result;
import com.tobeto.bootcampProject.core.utilities.results.SuccessDataResult;
import com.tobeto.bootcampProject.core.utilities.results.SuccessResult;
import com.tobeto.bootcampProject.dataAccess.abstracts.ApplicationRepository;
import com.tobeto.bootcampProject.entities.concretes.Application;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationManager implements ApplicationService {

    private ApplicationRepository applicationRepository;
    private ModelMapperService mapperService;

    @Override
    public DataResult<CreateApplicationResponse> add(CreateApplicationRequest request) {

        Application application = mapperService.forRequest().map(request, Application.class);
        applicationRepository.save(application);
        CreateApplicationResponse response = mapperService.forResponse().map(application, CreateApplicationResponse.class);

        return new SuccessDataResult<CreateApplicationResponse>(response, ApplicationMessages.ApplicationAdded);
    }

    @Override
    public DataResult<List<GetAllApplicationResponse>> getAll() {
        List<Application> applications = applicationRepository.findAll();
        List<GetAllApplicationResponse> applicationResponses = applications.stream().map(application -> mapperService.forResponse()
                .map(application, GetAllApplicationResponse.class)).toList();
        return new SuccessDataResult<List<GetAllApplicationResponse>>(applicationResponses, ApplicationMessages.AllApplicationsListed);
    }

    @Override
    public DataResult<GetApplicationResponse> getById(int id) {
        Application application = applicationRepository.getById(id);
        GetApplicationResponse response = mapperService.forResponse().map(application, GetApplicationResponse.class);
        return new SuccessDataResult<GetApplicationResponse>(response, ApplicationMessages.ApplicationListed);
    }

    @Override
    public Result delete(int id) {
        Application application = applicationRepository.getById(id);
        applicationRepository.delete(application);
        return new SuccessResult(ApplicationMessages.ApplicationDeleted);
    }

    @Override
    public DataResult<UpdateApplicationResponse> update(UpdateApplicationRequest request, int id) {

        Application application = applicationRepository.getById(id);
        Application updatedApplication = mapperService.forRequest().map(request, Application.class);

        application.setApplicant(updatedApplication.getApplicant() != null ? updatedApplication.getApplicant() : application.getApplicant());
        application.setBootcamp(updatedApplication.getBootcamp() != null ? updatedApplication.getBootcamp() : application.getBootcamp());
        application.setApplicationState(updatedApplication.getApplicationState() != null ? updatedApplication.getApplicationState() : application.getApplicationState());

        applicationRepository.save(application);

        UpdateApplicationResponse response = mapperService.forResponse().map(application, UpdateApplicationResponse.class);
        return new SuccessDataResult<UpdateApplicationResponse>(response, ApplicationMessages.ApplicationUpdated);
    }
}
