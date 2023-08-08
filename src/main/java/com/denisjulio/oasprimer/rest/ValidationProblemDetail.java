package com.denisjulio.oasprimer.rest;

import org.springframework.http.ProblemDetail;

import java.util.*;

public class ValidationProblemDetail extends ProblemDetail {

    private final ArrayList<InvalidField> invalidFields;

    public ValidationProblemDetail(ProblemDetail problemDetail) {
        super(problemDetail);
        this.invalidFields = new ArrayList<>();
        this.setProperty("invalidFields", this.invalidFields);
    }

    public static ValidationProblemDetail forStatus(int status) {
        return new ValidationProblemDetail(ProblemDetail.forStatus(status));
    }

    public void setInvalidField(String field, Object argument, String reason) {
        var invalidField = new InvalidField(field, argument, reason);
        this.invalidFields.add(invalidField);
    }

    public record InvalidField(String field, Object argument, String reason) {

    }

}
