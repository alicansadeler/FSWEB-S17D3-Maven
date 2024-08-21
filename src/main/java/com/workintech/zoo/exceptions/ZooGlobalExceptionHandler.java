package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
@Slf4j
public class ZooGlobalExceptionHandler {

    @ExceptionHandler(ZooException.class)
    public ResponseEntity<ZooErrorResponse> handleError(ZooException zooException) {
        ZooErrorResponse zooErrorResponse = new ZooErrorResponse(
                                            zooException.getHttpStatus().value(),
                                            zooException.getMessage(),
                                            System.currentTimeMillis());
        log.error("ZooException : {}", zooException.getMessage(), zooException);
        return new ResponseEntity<>(zooErrorResponse, zooException.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ZooErrorResponse> handleError(Exception exception) {
        ZooErrorResponse zooErrorResponse = new ZooErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),

                System.currentTimeMillis());
        log.error("Unexpected Exception: ", exception);
        return new ResponseEntity<>(zooErrorResponse, HttpStatus.BAD_REQUEST);
    }


}
