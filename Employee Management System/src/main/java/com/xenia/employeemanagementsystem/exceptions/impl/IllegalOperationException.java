package com.xenia.employeemanagementsystem.exceptions.impl;

import com.xenia.employeemanagementsystem.exceptions.CustomException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class IllegalOperationException extends CustomException {
    public IllegalOperationException() {
        this(null);
    }
    public IllegalOperationException(String message) {
        super("Illegal Operation" + processMessage(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
