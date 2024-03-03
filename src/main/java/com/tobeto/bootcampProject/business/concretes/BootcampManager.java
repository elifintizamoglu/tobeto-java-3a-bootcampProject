package com.tobeto.bootcampProject.business.concretes;

import com.tobeto.bootcampProject.business.abstracts.BootcampService;
import com.tobeto.bootcampProject.business.constants.BootcampMessages;
import com.tobeto.bootcampProject.business.requests.create.bootcamp.CreateBootcampRequest;
import com.tobeto.bootcampProject.business.requests.update.bootcamp.UpdateBootcampRequest;
import com.tobeto.bootcampProject.business.responses.create.bootcamp.CreateBootcampResponse;
import com.tobeto.bootcampProject.business.responses.get.bootcamp.GetAllBootcampResponse;
import com.tobeto.bootcampProject.business.responses.get.bootcamp.GetBootcampResponse;
import com.tobeto.bootcampProject.business.responses.update.bootcamp.UpdateBootcampResponse;
import com.tobeto.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.tobeto.bootcampProject.core.utilities.paging.PageDto;
import com.tobeto.bootcampProject.core.utilities.results.DataResult;
import com.tobeto.bootcampProject.core.utilities.results.Result;
import com.tobeto.bootcampProject.core.utilities.results.SuccessDataResult;
import com.tobeto.bootcampProject.core.utilities.results.SuccessResult;
import com.tobeto.bootcampProject.dataAccess.abstracts.BootcampRepository;
import com.tobeto.bootcampProject.entities.concretes.Bootcamp;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BootcampManager implements BootcampService {

    private BootcampRepository bootcampRepository;
    private ModelMapperService mapperService;

    @Override
    public DataResult<CreateBootcampResponse> add(CreateBootcampRequest request) {

        Bootcamp bootcamp = mapperService.forRequest().map(request, Bootcamp.class);
        bootcampRepository.save(bootcamp);
        CreateBootcampResponse response = mapperService.forResponse().map(bootcamp, CreateBootcampResponse.class);

        return new SuccessDataResult<CreateBootcampResponse>(response, BootcampMessages.BootcampAdded);
    }

    @Override
    public DataResult<List<GetAllBootcampResponse>> getAll() {

        List<Bootcamp> bootcamps = bootcampRepository.findAll();
        List<GetAllBootcampResponse> bootcampResponses = bootcamps.stream().map(bootcamp -> mapperService.forResponse()
                .map(bootcamp, GetAllBootcampResponse.class)).toList();

        return new SuccessDataResult<List<GetAllBootcampResponse>>(bootcampResponses, BootcampMessages.AllBootcampsListed);
    }

    @Override
    public DataResult<GetBootcampResponse> getById(int id) {
        Bootcamp bootcamp = bootcampRepository.getById(id);
        GetBootcampResponse response = mapperService.forResponse()
                .map(bootcamp, GetBootcampResponse.class);
        return new SuccessDataResult<GetBootcampResponse>(response, BootcampMessages.BootcampListed);
    }

    @Override
    public Result delete(int id) {

        Bootcamp bootcamp = bootcampRepository.getById(id);
        bootcampRepository.delete(bootcamp);
        return new SuccessResult(BootcampMessages.BootcampDeleted);
    }

    @Override
    public DataResult<UpdateBootcampResponse> update(UpdateBootcampRequest request) {

        Bootcamp bootcamp = bootcampRepository.getById(request.getId());
        Bootcamp updatedBootcamp = mapperService.forRequest().map(request, Bootcamp.class);

        bootcamp.setName(updatedBootcamp.getName() != null ? updatedBootcamp.getName() : bootcamp.getName());
        bootcamp.setInstructor(updatedBootcamp.getInstructor() != null ? updatedBootcamp.getInstructor() : bootcamp.getInstructor());
        bootcamp.setStartDate(updatedBootcamp.getStartDate() != null ? updatedBootcamp.getStartDate() : bootcamp.getStartDate());
        bootcamp.setEndDate(updatedBootcamp.getEndDate() != null ? updatedBootcamp.getEndDate() : bootcamp.getEndDate());
        bootcamp.setBootcampState(updatedBootcamp.getBootcampState() != null ? updatedBootcamp.getBootcampState() : bootcamp.getBootcampState());
        bootcampRepository.save(bootcamp);

        UpdateBootcampResponse response = mapperService.forResponse().map(bootcamp, UpdateBootcampResponse.class);
        return new SuccessDataResult<UpdateBootcampResponse>(response, BootcampMessages.BootcampUpdated);
    }

    @Override
    public DataResult<List<GetAllBootcampResponse>> getAllPage(PageDto pageDto) {
        Sort sort = Sort.by(Sort.Direction.fromString(pageDto.getSortDirection()), pageDto.getSortBy());
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize(), sort);
        Page<Bootcamp> bootcamps = bootcampRepository.findAll(pageable);
        List<GetAllBootcampResponse> responses = bootcamps.stream().map(bootcamp -> mapperService.forResponse().map(bootcamp, GetAllBootcampResponse.class)).toList();
        return new SuccessDataResult<List<GetAllBootcampResponse>>(responses);
    }
}
