package com.tobeto.bootcampProject.business.abstracts;

import com.tobeto.bootcampProject.business.requests.create.blacklist.CreateBlacklistRequest;
import com.tobeto.bootcampProject.business.requests.update.blacklist.UpdateBlacklistRequest;
import com.tobeto.bootcampProject.business.responses.create.blacklist.CreateBlacklistResponse;
import com.tobeto.bootcampProject.business.responses.get.blacklist.GetAllBlacklistResponse;
import com.tobeto.bootcampProject.business.responses.get.blacklist.GetBlacklistResponse;
import com.tobeto.bootcampProject.business.responses.update.blacklist.UpdateBlacklistResponse;
import com.tobeto.bootcampProject.core.utilities.paging.PageDto;
import com.tobeto.bootcampProject.core.utilities.results.DataResult;
import com.tobeto.bootcampProject.core.utilities.results.Result;

import java.util.List;

public interface BlacklistService {
    DataResult<CreateBlacklistResponse> add(CreateBlacklistRequest request);

    DataResult<List<GetAllBlacklistResponse>> getAll();

    DataResult<GetBlacklistResponse> getById(int id);

    Result delete(int id);

    DataResult<UpdateBlacklistResponse> update(UpdateBlacklistRequest request);

    DataResult<List<GetAllBlacklistResponse>> getAllPage(PageDto pageDto);

}
