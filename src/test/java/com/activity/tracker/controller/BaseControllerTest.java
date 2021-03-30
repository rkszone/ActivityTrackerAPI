package com.activity.tracker.controller;

import com.activity.tracker.exception.TrackerException;
import com.activity.tracker.model.Error;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class BaseControllerTest {

    @Mock
    TrackerException trackerException;

    @InjectMocks
    private BaseController baseController;

    @org.junit.Test(expected = TrackerException.class)
    public void handleGetPage_thenExpectationIsTrackerException() {
        BaseController baseController1 = spy(baseController);
        baseController1.handleGetAll();
    }

    @org.junit.Test
    public void TrackerException_returnsResponseEntityAsError() {
        BaseController baseController1 = spy(baseController);
        Error error = new Error(trackerException.getMessage(), HttpStatus.NOT_FOUND.toString(), "error");
        ResponseEntity responseEntity = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        Assert.assertEquals(responseEntity, baseController1.trackerException(trackerException));
    }
}