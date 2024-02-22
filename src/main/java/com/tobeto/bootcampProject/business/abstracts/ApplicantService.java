package com.tobeto.bootcampProject.business.abstracts;

import com.tobeto.bootcampProject.business.requests.create.applicant.CreateApplicantRequest;
import com.tobeto.bootcampProject.business.requests.update.applicant.UpdateApplicantRequest;
import com.tobeto.bootcampProject.business.responses.create.applicant.CreateApplicantResponse;
import com.tobeto.bootcampProject.business.responses.delete.applicant.DeleteApplicantResponse;
import com.tobeto.bootcampProject.business.responses.get.applicant.GetAllApplicantResponse;
import com.tobeto.bootcampProject.business.responses.get.applicant.GetApplicantResponse;
import com.tobeto.bootcampProject.business.responses.update.applicant.UpdateApplicantResponse;
import com.tobeto.bootcampProject.core.utilities.results.DataResult;

import java.util.List;

public interface ApplicantService {
    DataResult<CreateApplicantResponse> add(CreateApplicantRequest request);
    DataResult<List<GetAllApplicantResponse>> getAll();

    DataResult<GetApplicantResponse> getById(int id);

    DeleteApplicantResponse delete(int id);

    UpdateApplicantResponse update(UpdateApplicantRequest request, int id);

}
