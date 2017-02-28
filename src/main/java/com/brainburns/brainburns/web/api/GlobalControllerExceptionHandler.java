package com.brainburns.brainburns.web.api;

import com.brainburns.brainburns.service.DuplicateDeskTitleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by arthan on 28.02.2017. | Project brainburns
 */

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(DuplicateDeskTitleException.class)
    public ResponseEntity<Object> handleDuplicateDeskTitleException(DuplicateDeskTitleException ex) {
        Map<String, Object> data = new HashMap<>();
        data.put("status", "error");
        data.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
    }
}
