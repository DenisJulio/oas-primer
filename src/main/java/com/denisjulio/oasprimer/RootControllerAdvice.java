package com.denisjulio.oasprimer;

import com.denisjulio.oasprimer.rest.ValidationProblemDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RootControllerAdvice {

    private static Logger log = LoggerFactory.getLogger(RootControllerAdvice.class);

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleException(HttpMessageNotReadableException ex) {
        return ProblemDetail.forStatus(200);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationError(MethodArgumentNotValidException ex) {
        var problemDetail = ValidationProblemDetail.forStatus(422);
        problemDetail.setTitle("Validation error in the Request body");
        var bindRes = ex.getBindingResult();
        if (bindRes.hasErrors()) {
            var fieldErrors = bindRes.getFieldErrors();
            fieldErrors
                    .forEach(fe -> problemDetail.setInvalidField(
                            fe.getField(), fe.getRejectedValue(), fe.getDefaultMessage()
                    ));
        }
        log.info("{}", problemDetail);
        return problemDetail;
    }
}
