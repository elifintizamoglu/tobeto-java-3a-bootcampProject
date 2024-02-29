package com.tobeto.bootcampProject.business.concretes;

import com.tobeto.bootcampProject.business.abstracts.BlacklistService;
import com.tobeto.bootcampProject.business.requests.create.blacklist.CreateBlacklistRequest;
import com.tobeto.bootcampProject.business.requests.update.blacklist.UpdateBlacklistRequest;
import com.tobeto.bootcampProject.business.responses.create.blacklist.CreateBlacklistResponse;
import com.tobeto.bootcampProject.business.responses.get.blacklist.GetAllBlacklistResponse;
import com.tobeto.bootcampProject.business.responses.get.blacklist.GetBlacklistResponse;
import com.tobeto.bootcampProject.business.responses.update.blacklist.UpdateBlacklistResponse;
import com.tobeto.bootcampProject.business.rules.BlacklistBusinessRules;
import com.tobeto.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.tobeto.bootcampProject.core.utilities.paging.PageDto;
import com.tobeto.bootcampProject.core.utilities.results.DataResult;
import com.tobeto.bootcampProject.core.utilities.results.Result;
import com.tobeto.bootcampProject.core.utilities.results.SuccessDataResult;
import com.tobeto.bootcampProject.core.utilities.results.SuccessResult;
import com.tobeto.bootcampProject.dataAccess.abstracts.BlacklistRepository;
import com.tobeto.bootcampProject.entities.concretes.Blacklist;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BlacklistManager implements BlacklistService {

    private BlacklistRepository blacklistRepository;
    private ModelMapperService mapperService;
    private BlacklistBusinessRules blacklistBusinessRules;

    @Override
    public DataResult<CreateBlacklistResponse> add(CreateBlacklistRequest request) {

        blacklistBusinessRules.checkIfApplicantInBlacklist(request.getApplicantId());

        Blacklist blacklist = mapperService.forRequest().map(request, Blacklist.class);
        blacklistRepository.save(blacklist);
        CreateBlacklistResponse response = mapperService.forResponse().map(blacklist, CreateBlacklistResponse.class);

        return new SuccessDataResult<CreateBlacklistResponse>(response, "Applicant added to blacklist.");
    }

    @Override
    public DataResult<List<GetAllBlacklistResponse>> getAll() {
        List<Blacklist> blacklists = blacklistRepository.findAll();
        List<GetAllBlacklistResponse> blacklistResponses = blacklists.stream().map(blacklist -> mapperService.forResponse()
                .map(blacklist, GetAllBlacklistResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<GetAllBlacklistResponse>>(blacklistResponses, "All applicants in blacklist listed.");
    }

    @Override
    public DataResult<GetBlacklistResponse> getById(int id) {
        Blacklist blacklist = blacklistRepository.getById(id);
        GetBlacklistResponse response = mapperService.forResponse()
                .map(blacklist, GetBlacklistResponse.class);
        return new SuccessDataResult<GetBlacklistResponse>(response, "Applicant in blacklist by id " + id + " listed.");
    }

    @Override
    public Result delete(int id) {
        Blacklist blacklist = blacklistRepository.getById(id);
        blacklistRepository.delete(blacklist);
        return new SuccessResult("Applicant is deleted from blacklist.");
    }

    @Override
    public DataResult<UpdateBlacklistResponse> update(UpdateBlacklistRequest request, int id) {
        Blacklist blacklist = blacklistRepository.getById(id);
        Blacklist updatedblacklist = mapperService.forRequest().map(request, Blacklist.class);

        blacklist.setReason(updatedblacklist.getReason() != null ? updatedblacklist.getReason() : blacklist.getReason());
        blacklistRepository.save(blacklist);

        UpdateBlacklistResponse response = mapperService.forResponse().map(blacklist, UpdateBlacklistResponse.class);

        return new SuccessDataResult<UpdateBlacklistResponse>(response,"Applicant information in blacklist updated.");
    }

    @Override
    public DataResult<List<GetAllBlacklistResponse>> getAllPage(PageDto pageDto) {
        Sort sort = Sort.by(Sort.Direction.fromString(pageDto.getSortDirection()), pageDto.getSortBy());
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize(), sort);
        Page<Blacklist> blacklists = blacklistRepository.findAll(pageable);
        List<GetAllBlacklistResponse> responses = blacklists.stream().map(blacklist -> mapperService.forResponse().map(blacklist, GetAllBlacklistResponse.class)).toList();
        return new SuccessDataResult<List<GetAllBlacklistResponse>>(responses);
    }
}
