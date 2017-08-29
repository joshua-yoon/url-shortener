package com.kotelking.shorten.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    private HttpStatus httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;

    public ApiException(String msg){
        this(msg,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ApiException(Exception e){
        this(e,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ApiException(String msg, HttpStatus httpStatus){
        super(msg);
        this.httpStatus=httpStatus;
    }

    public ApiException(Exception e, HttpStatus httpStatus){
        super(e);
        this.httpStatus=httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
