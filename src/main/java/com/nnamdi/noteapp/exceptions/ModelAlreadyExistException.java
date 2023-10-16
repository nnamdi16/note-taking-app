package com.nnamdi.noteapp.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@EqualsAndHashCode(callSuper = true)
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ModelAlreadyExistException extends RuntimeException {
    public ModelAlreadyExistException(String message){
        super(message);
    }

}
