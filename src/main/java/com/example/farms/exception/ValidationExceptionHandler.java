package com.example.farms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Map<String,String> showCustomMessage(Exception e){
        Map<String,String> response = new HashMap<>();
        response.put("status","error");
        response.put("error","VEH-001");//input not valid
        response.put("reason",e.getMessage());
        return response;
    }
}
