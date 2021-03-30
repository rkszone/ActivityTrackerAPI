package com.activity.tracker.controller;

import com.activity.tracker.exception.TrackerException;
import com.activity.tracker.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Base Controller
 * Provides functionality for handling all request for Activity Tracker
 */
@Controller
@RequestMapping(value = {"/**"},method = {RequestMethod.GET, RequestMethod.POST})
public class BaseController {

    public BaseController() {
    }

    @RequestMapping(
            value = {"/**"},
            produces = {"application/json"}
    )
    @GetMapping
    @PostMapping
    public String handleGetAll() throws TrackerException{
        throw new TrackerException("Activity resource not found");
    }

    /**
     * Exception The trackerException method
     * @param exception the exception caught from handleGetAll
     * @return ResponseEntity with not found message
     */
    @ExceptionHandler(value = TrackerException.class)
    public ResponseEntity<Error> trackerException(TrackerException exception) {
        Error error = new Error(exception.getMessage(), HttpStatus.NOT_FOUND.toString(), "error");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
