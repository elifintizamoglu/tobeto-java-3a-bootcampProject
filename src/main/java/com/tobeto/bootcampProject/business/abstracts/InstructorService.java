package com.tobeto.bootcampProject.business.abstracts;


import com.tobeto.bootcampProject.business.requests.create.instructor.CreateInstructorRequest;
import com.tobeto.bootcampProject.business.requests.update.instructor.UpdateInstructorRequest;
import com.tobeto.bootcampProject.business.responses.create.instructor.CreateInstructorResponse;
import com.tobeto.bootcampProject.business.responses.get.instructor.GetAllInstructorResponse;
import com.tobeto.bootcampProject.business.responses.get.instructor.GetInstructorResponse;
import com.tobeto.bootcampProject.business.responses.update.instructor.UpdateInstructorResponse;
import com.tobeto.bootcampProject.core.utilities.paging.PageDto;
import com.tobeto.bootcampProject.core.utilities.results.DataResult;
import com.tobeto.bootcampProject.core.utilities.results.Result;

import java.util.List;

public interface InstructorService {
    DataResult<CreateInstructorResponse> add(CreateInstructorRequest request);

    DataResult<List<GetAllInstructorResponse>> getAll();

    DataResult<GetInstructorResponse> getById(int id);

    Result delete(int id);

    DataResult<UpdateInstructorResponse> update(UpdateInstructorRequest request, int id);
    DataResult<List<GetAllInstructorResponse>> getAllPage(PageDto pageDto);
}
