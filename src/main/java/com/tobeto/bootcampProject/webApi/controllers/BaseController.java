package com.tobeto.bootcampProject.webApi.controllers;

import com.tobeto.bootcampProject.core.utilities.results.DataResult;
import org.springframework.http.ResponseEntity;

public class BaseController {
    public ResponseEntity<?> handleDataResult(DataResult<?> dataResult) {
        if(dataResult.isSuccess()) {
            return ResponseEntity.ok(dataResult);
        }
        return ResponseEntity.badRequest().body(dataResult);
    }
}
