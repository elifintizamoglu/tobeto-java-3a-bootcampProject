package com.tobeto.bootcampProject.business.abstracts;

import com.tobeto.bootcampProject.business.requests.create.applicant.CreateApplicantRequest;
import com.tobeto.bootcampProject.business.requests.update.applicant.UpdateApplicantRequest;
import com.tobeto.bootcampProject.business.responses.create.applicant.CreateApplicantResponse;
import com.tobeto.bootcampProject.business.responses.get.applicant.GetAllApplicantResponse;
import com.tobeto.bootcampProject.business.responses.get.applicant.GetApplicantResponse;
import com.tobeto.bootcampProject.business.responses.update.applicant.UpdateApplicantResponse;
import com.tobeto.bootcampProject.core.utilities.paging.PageDto;
import com.tobeto.bootcampProject.core.utilities.results.DataResult;
import com.tobeto.bootcampProject.core.utilities.results.Result;

import java.util.List;

public interface ApplicantService {
    DataResult<CreateApplicantResponse> add(CreateApplicantRequest request);

    DataResult<List<GetAllApplicantResponse>> getAll();

    DataResult<GetApplicantResponse> getById(int id);

    Result delete(int id);

    DataResult<UpdateApplicantResponse> update(UpdateApplicantRequest request);

    DataResult<List<GetAllApplicantResponse>> getAllPage(PageDto pageDto);

}
