package com.tobeto.bootcampProject.business.concretes;

import com.tobeto.bootcampProject.business.abstracts.ApplicationStateService;
import com.tobeto.bootcampProject.business.constants.ApplicationStateMessages;
import com.tobeto.bootcampProject.business.requests.create.applicationState.CreateApplicationStateRequest;
import com.tobeto.bootcampProject.business.requests.update.applicationState.UpdateApplicationStateRequest;
import com.tobeto.bootcampProject.business.responses.create.applicationState.CreateApplicationStateResponse;
import com.tobeto.bootcampProject.business.responses.get.applicationState.GetAllApplicationStateResponse;
import com.tobeto.bootcampProject.business.responses.get.applicationState.GetApplicationStateResponse;
import com.tobeto.bootcampProject.business.responses.update.applicationState.UpdateApplicationStateResponse;
import com.tobeto.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.tobeto.bootcampProject.core.utilities.results.DataResult;
import com.tobeto.bootcampProject.core.utilities.results.Result;
import com.tobeto.bootcampProject.core.utilities.results.SuccessDataResult;
import com.tobeto.bootcampProject.core.utilities.results.SuccessResult;
import com.tobeto.bootcampProject.dataAccess.abstracts.ApplicationStateRepository;
import com.tobeto.bootcampProject.entities.concretes.ApplicationState;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationStateManager implements ApplicationStateService {

    private ApplicationStateRepository applicationStateRepository;
    private ModelMapperService mapperService;

    @Override
    public DataResult<CreateApplicationStateResponse> add(CreateApplicationStateRequest request) {

        ApplicationState applicationState = mapperService.forRequest().map(request, ApplicationState.class);
        applicationStateRepository.save(applicationState);

        CreateApplicationStateResponse response = mapperService.forResponse().map(applicationState, CreateApplicationStateResponse.class);

        return new SuccessDataResult<CreateApplicationStateResponse>(response, ApplicationStateMessages.ApplicationStateAdded);
    }

    @Override
    public DataResult<List<GetAllApplicationStateResponse>> getAll() {

        List<ApplicationState> applicationStates = applicationStateRepository.findAll();
        List<GetAllApplicationStateResponse> applicationStateResponses = applicationStates.stream()
                .map(applicationState -> mapperService.forResponse().map(applicationState, GetAllApplicationStateResponse.class)).toList();

        return new SuccessDataResult<List<GetAllApplicationStateResponse>>(applicationStateResponses, ApplicationStateMessages.AllApplicationStatesListed);
    }

    @Override
    public DataResult<GetApplicationStateResponse> getById(int id) {

        ApplicationState applicationState = applicationStateRepository.getById(id);
        GetApplicationStateResponse response = mapperService.forResponse().map(applicationState, GetApplicationStateResponse.class);

        return new SuccessDataResult<GetApplicationStateResponse>(response, ApplicationStateMessages.ApplicationStateListed);
    }

    @Override
    public Result delete(int id) {

        ApplicationState applicationState = applicationStateRepository.getById(id);
        applicationStateRepository.delete(applicationState);

        return new SuccessResult(ApplicationStateMessages.ApplicationStateDeleted);
    }

    @Override
    public DataResult<UpdateApplicationStateResponse> update(UpdateApplicationStateRequest request, int id) {

        ApplicationState applicationState = applicationStateRepository.getById(id);
        ApplicationState updatedApplicationState = mapperService.forRequest().map(request, ApplicationState.class);

        applicationState.setName(updatedApplicationState.getName() != null ? updatedApplicationState.getName() : applicationState.getName());
        applicationStateRepository.save(applicationState);

        UpdateApplicationStateResponse response = mapperService.forResponse().map(applicationState, UpdateApplicationStateResponse.class);

        return new SuccessDataResult<UpdateApplicationStateResponse>(response, ApplicationStateMessages.ApplicationStateUpdated);
    }
}
