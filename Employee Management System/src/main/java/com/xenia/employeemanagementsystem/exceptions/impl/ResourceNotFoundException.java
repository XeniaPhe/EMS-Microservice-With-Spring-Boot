package com.xenia.employeemanagementsystem.exceptions.impl;

import com.xenia.employeemanagementsystem.exceptions.CustomException;
import com.xenia.employeemanagementsystem.model.IEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceNotFoundException extends CustomException {
    public ResourceNotFoundException() {
        this(null);
    }

    public ResourceNotFoundException(String message) {
        super("Resource Not Found" + processMessage(message), HttpStatus.NOT_FOUND);
    }

    public static ResourceNotFoundException entityNotFoundException(Class<? extends IEntity> entityType, int id){
        return new ResourceNotFoundException(entityType.getSimpleName() + " with Id: " + id + " not found!");
    }
}
