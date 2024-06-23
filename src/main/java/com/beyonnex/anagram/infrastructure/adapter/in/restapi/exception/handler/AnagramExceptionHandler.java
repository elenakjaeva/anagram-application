package com.beyonnex.anagram.infrastructure.adapter.in.restapi.exception.handler;

import com.beyonnex.anagram.domain.exception.AnagramInputIsNullException;
import com.beyonnex.anagram.domain.exception.AnagramInputNotSavedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AnagramExceptionHandler {

    @ExceptionHandler(AnagramInputIsNullException.class)
    public ProblemDetail handleFailedToReturnRentedVideoException(AnagramInputIsNullException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(AnagramInputNotSavedException.class)
    public ProblemDetail handlePayedAmountIncorrectException(AnagramInputNotSavedException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}
