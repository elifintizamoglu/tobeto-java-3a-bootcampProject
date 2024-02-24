package com.tobeto.bootcampProject.business.abstracts;

import com.tobeto.bootcampProject.business.requests.create.bootcamp.CreateBootcampRequest;
import com.tobeto.bootcampProject.business.requests.update.bootcamp.UpdateBootcampRequest;
import com.tobeto.bootcampProject.business.responses.create.bootcamp.CreateBootcampResponse;
import com.tobeto.bootcampProject.business.responses.get.bootcamp.GetAllBootcampResponse;
import com.tobeto.bootcampProject.business.responses.get.bootcamp.GetBootcampResponse;
import com.tobeto.bootcampProject.business.responses.update.bootcamp.UpdateBootcampResponse;
import com.tobeto.bootcampProject.core.utilities.results.DataResult;
import com.tobeto.bootcampProject.core.utilities.results.Result;

import java.util.List;

public interface BootcampService {

    DataResult<CreateBootcampResponse> add(CreateBootcampRequest request);

    DataResult<List<GetAllBootcampResponse>> getAll();

    DataResult<GetBootcampResponse> getById(int id);

    Result delete(int id);

    DataResult<UpdateBootcampResponse> update(UpdateBootcampRequest request, int id);
}
