package org.fasttrack.tema18.controller;

import org.fasttrack.tema18.exceptions.ApiException;
import org.fasttrack.tema18.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiException handleNotFound(EntityNotFoundException exception) {
        return new ApiException(exception.getMessage(), exception.getEntityId(), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ApiException handRuntime(RuntimeException exception) {
//        return new ApiException(exception.getMessage(), 0, HttpStatus.BAD_REQUEST);
//    }

}
