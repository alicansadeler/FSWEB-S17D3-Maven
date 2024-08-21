package com.workintech.zoo.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class ZooException extends RuntimeException{

    public HttpStatus httpStatus;

    public ZooException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
