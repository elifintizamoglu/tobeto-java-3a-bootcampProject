package com.tobeto.bootcampProject.webApi.controllers;

import com.tobeto.bootcampProject.business.abstracts.BlacklistService;
import com.tobeto.bootcampProject.business.requests.create.blacklist.CreateBlacklistRequest;
import com.tobeto.bootcampProject.business.requests.update.blacklist.UpdateBlacklistRequest;
import com.tobeto.bootcampProject.core.utilities.paging.PageDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blacklists")
@AllArgsConstructor
public class BlacklistController extends BaseController{
    private BlacklistService blacklistService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CreateBlacklistRequest request) {
        return handleDataResult(blacklistService.add(request));
    }

    @GetMapping("getall")
    public ResponseEntity<?> getAll() {
        return handleDataResult(blacklistService.getAll());
    }

    @GetMapping("getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return handleDataResult(blacklistService.getById(id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return handleResult(blacklistService.delete(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody UpdateBlacklistRequest request, @PathVariable int id) {
        return handleDataResult(blacklistService.update(request, id));
    }

    @GetMapping("sort")
    public ResponseEntity<?> getAllPage(@RequestBody PageDto pageDto) {
        return handleDataResult(blacklistService.getAllPage(pageDto));
    }
}
