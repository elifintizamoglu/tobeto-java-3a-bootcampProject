package com.tobeto.bootcampProject.business.abstracts;

import com.tobeto.bootcampProject.business.requests.create.application.CreateApplicationRequest;
import com.tobeto.bootcampProject.business.requests.update.application.UpdateApplicationRequest;
import com.tobeto.bootcampProject.business.responses.create.application.CreateApplicationResponse;
import com.tobeto.bootcampProject.business.responses.get.application.GetAllApplicationResponse;
import com.tobeto.bootcampProject.business.responses.get.application.GetApplicationResponse;
import com.tobeto.bootcampProject.business.responses.update.application.UpdateApplicationResponse;
import com.tobeto.bootcampProject.core.utilities.paging.PageDto;
import com.tobeto.bootcampProject.core.utilities.results.DataResult;
import com.tobeto.bootcampProject.core.utilities.results.Result;

import java.util.List;

public interface ApplicationService {
    DataResult<CreateApplicationResponse> add(CreateApplicationRequest request);

    DataResult<List<GetAllApplicationResponse>> getAll();

    DataResult<GetApplicationResponse> getById(int id);

    Result delete(int id);

    DataResult<UpdateApplicationResponse> update(UpdateApplicationRequest request, int id);

    DataResult<List<GetAllApplicationResponse>> getAllPage(PageDto pageDto);
}
