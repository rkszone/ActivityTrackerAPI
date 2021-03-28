package com.activity.tracker.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * EntityExceptionHandler will handle all TrackerException.class, Exception.class
 */
@ControllerAdvice
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * handleConflict
     * @param ex exception
     * @param request webRequest
     * @return response entity with status code
     */
    @ExceptionHandler(value
            = { TrackerException.class, Exception.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
