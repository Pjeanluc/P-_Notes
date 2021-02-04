package com.ocr.axa.jlp.notes.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ControllerException extends RuntimeException {
    
    public ControllerException(String s) {
        super("ERROR : " + s);
    }
}
