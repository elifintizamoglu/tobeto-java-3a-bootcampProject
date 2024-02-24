package com.tobeto.bootcampProject.business.concretes;

import com.tobeto.bootcampProject.business.abstracts.BootcampStateService;
import com.tobeto.bootcampProject.business.constants.BootcampStateMessages;
import com.tobeto.bootcampProject.business.requests.create.bootcampState.CreateBootcampStateRequest;
import com.tobeto.bootcampProject.business.requests.update.bootcampState.UpdateBootcampStateRequest;
import com.tobeto.bootcampProject.business.responses.create.bootcampState.CreateBootcampStateResponse;
import com.tobeto.bootcampProject.business.responses.get.bootcampState.GetAllBootcampStateResponse;
import com.tobeto.bootcampProject.business.responses.get.bootcampState.GetBootcampStateResponse;
import com.tobeto.bootcampProject.business.responses.update.bootcampState.UpdateBootcampStateResponse;
import com.tobeto.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.tobeto.bootcampProject.core.utilities.results.DataResult;
import com.tobeto.bootcampProject.core.utilities.results.Result;
import com.tobeto.bootcampProject.core.utilities.results.SuccessDataResult;
import com.tobeto.bootcampProject.core.utilities.results.SuccessResult;
import com.tobeto.bootcampProject.dataAccess.abstracts.BootcampStateRepository;
import com.tobeto.bootcampProject.entities.concretes.BootcampState;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BootcampStateManager implements BootcampStateService {

    private BootcampStateRepository bootcampStateRepository;
    private ModelMapperService mapperService;

    @Override
    public DataResult<CreateBootcampStateResponse> add(CreateBootcampStateRequest request) {

        BootcampState bootcampState = mapperService.forRequest().map(request, BootcampState.class);
        bootcampStateRepository.save(bootcampState);
        CreateBootcampStateResponse response = mapperService.forResponse().map(bootcampState, CreateBootcampStateResponse.class);

        return new SuccessDataResult<CreateBootcampStateResponse>(response, BootcampStateMessages.BootcampStateAdded);
    }

    @Override
    public DataResult<List<GetAllBootcampStateResponse>> getAll() {

        List<BootcampState> bootcampStates = bootcampStateRepository.findAll();
        List<GetAllBootcampStateResponse> bootcampStateResponses = bootcampStates.stream().map(bootcampState -> mapperService.forResponse()
                .map(bootcampState, GetAllBootcampStateResponse.class)).toList();

        return new SuccessDataResult<List<GetAllBootcampStateResponse>>(bootcampStateResponses, BootcampStateMessages.AllBootcampStatesListed);
    }

    @Override
    public DataResult<GetBootcampStateResponse> getById(int id) {

        BootcampState bootcampState = bootcampStateRepository.getById(id);
        GetBootcampStateResponse response = mapperService.forResponse()
                .map(bootcampState, GetBootcampStateResponse.class);

        return new SuccessDataResult<GetBootcampStateResponse>(response, BootcampStateMessages.BootcampStateListed);
    }

    @Override
    public Result delete(int id) {

        BootcampState bootcampState = bootcampStateRepository.getById(id);
        bootcampStateRepository.delete(bootcampState);
        return new SuccessResult(BootcampStateMessages.BootcampStateDeleted);
    }

    @Override
    public DataResult<UpdateBootcampStateResponse> update(UpdateBootcampStateRequest request, int id) {

        BootcampState bootcampState = bootcampStateRepository.getById(id);
        BootcampState updatedBootcampState = mapperService.forRequest().map(request, BootcampState.class);

        bootcampState.setName(updatedBootcampState.getName() != null ? updatedBootcampState.getName() : bootcampState.getName());
        bootcampStateRepository.save(bootcampState);

        UpdateBootcampStateResponse response = mapperService.forResponse().map(bootcampState, UpdateBootcampStateResponse.class);
        return new SuccessDataResult<UpdateBootcampStateResponse>(response, BootcampStateMessages.BootcampStateUpdated);
    }
}