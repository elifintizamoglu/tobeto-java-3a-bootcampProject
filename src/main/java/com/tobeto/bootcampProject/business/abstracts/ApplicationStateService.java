package com.tobeto.bootcampProject.business.abstracts;

import com.tobeto.bootcampProject.business.requests.create.applicationState.CreateApplicationStateRequest;
import com.tobeto.bootcampProject.business.requests.update.applicationState.UpdateApplicationStateRequest;
import com.tobeto.bootcampProject.business.responses.create.applicationState.CreateApplicationStateResponse;
import com.tobeto.bootcampProject.business.responses.get.applicationState.GetAllApplicationStateResponse;
import com.tobeto.bootcampProject.business.responses.get.applicationState.GetApplicationStateResponse;
import com.tobeto.bootcampProject.business.responses.update.applicationState.UpdateApplicationStateResponse;
import com.tobeto.bootcampProject.core.utilities.paging.PageDto;
import com.tobeto.bootcampProject.core.utilities.results.DataResult;
import com.tobeto.bootcampProject.core.utilities.results.Result;

import java.util.List;

public interface ApplicationStateService {

    DataResult<CreateApplicationStateResponse> add(CreateApplicationStateRequest request);

    DataResult<List<GetAllApplicationStateResponse>> getAll();

    DataResult<GetApplicationStateResponse> getById(int id);

    Result delete(int id);

    DataResult<UpdateApplicationStateResponse> update(UpdateApplicationStateRequest request, int id);

    DataResult<List<GetAllApplicationStateResponse>> getAllPage(PageDto pageDto);
}
