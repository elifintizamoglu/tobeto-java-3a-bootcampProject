package com.tobeto.bootcampProject.core.exceptions.problemDetails;

import org.springframework.http.HttpStatus;

public class InternalServerErrorProblemDetail extends ProblemDetails {
    public InternalServerErrorProblemDetail() {
        setTitle("Internal Server Rule Violation");
        setType("http://tobeto.com/exceptions/internal");
        setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
    }
}
