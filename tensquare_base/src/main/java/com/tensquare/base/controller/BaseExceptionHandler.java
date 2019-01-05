package com.tensquare.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public Result exception(Exception c){
        return new Result(false, StatusCode.ERROR,c.getMessage());
    }
}
