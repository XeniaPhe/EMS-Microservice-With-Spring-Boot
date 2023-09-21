package com.xenia.employeemanagementsystem.exceptions.impl;

import com.xenia.employeemanagementsystem.exceptions.CustomException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidValueException extends CustomException {
    public InvalidValueException() {
        this(null);
    }

    public InvalidValueException(String message) {
        super("Invalid Value" + processMessage(message), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
