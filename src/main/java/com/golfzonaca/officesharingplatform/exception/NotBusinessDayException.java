package com.golfzonaca.officesharingplatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "error.room")
public class NotBusinessDayException extends NoSuchElementException {
    public NotBusinessDayException() {
    }

    public NotBusinessDayException(String msg) {
        super(msg);
    }
}
