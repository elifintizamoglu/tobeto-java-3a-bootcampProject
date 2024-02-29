package com.tobeto.bootcampProject.core.exceptions.problemDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.Map;
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ValidationProblemDetails extends ProblemDetails {

    public ValidationProblemDetails() {
        setTitle("Validation Rule Violation");
        setType("http://tobeto.com/exceptions/validation");
        setDetail("Validation Problem");
        setStatus(HttpStatus.BAD_REQUEST.toString());
    }

    private Map<String, String> errors;  // hangi alanda ne hatası var
}
