package com.tobeto.bootcampProject.business.abstracts;

import com.tobeto.bootcampProject.business.requests.create.bootcampState.CreateBootcampStateRequest;
import com.tobeto.bootcampProject.business.requests.update.bootcampState.UpdateBootcampStateRequest;
import com.tobeto.bootcampProject.business.responses.create.bootcampState.CreateBootcampStateResponse;
import com.tobeto.bootcampProject.business.responses.get.bootcampState.GetAllBootcampStateResponse;
import com.tobeto.bootcampProject.business.responses.get.bootcampState.GetBootcampStateResponse;
import com.tobeto.bootcampProject.business.responses.update.bootcampState.UpdateBootcampStateResponse;
import com.tobeto.bootcampProject.core.utilities.paging.PageDto;
import com.tobeto.bootcampProject.core.utilities.results.DataResult;
import com.tobeto.bootcampProject.core.utilities.results.Result;

import java.util.List;

public interface BootcampStateService {
    DataResult<CreateBootcampStateResponse> add(CreateBootcampStateRequest request);

    DataResult<List<GetAllBootcampStateResponse>> getAll();

    DataResult<GetBootcampStateResponse> getById(int id);

    Result delete(int id);

    DataResult<UpdateBootcampStateResponse> update(UpdateBootcampStateRequest request);

    DataResult<List<GetAllBootcampStateResponse>> getAllPage(PageDto pageDto);
}
