package com.xenia.employeemanagementsystem.exceptions;

import com.xenia.employeemanagementsystem.utility.BusinessUtility;
import org.springframework.http.HttpStatus;

public abstract class CustomException extends RuntimeException{
    public final HttpStatus HTTP_STATUS;
    private String message;

    public CustomException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.HTTP_STATUS = status;
    }

    protected static String processMessage(String message){
        return BusinessUtility.isNullOrEmpty(message) ? "!" : ": " + message;
    }

    public String getErrorMessage(){
        return message;
    }
}
