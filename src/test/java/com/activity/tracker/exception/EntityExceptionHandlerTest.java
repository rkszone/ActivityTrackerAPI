package com.activity.tracker.exception;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

@RunWith(MockitoJUnitRunner.class)
public class EntityExceptionHandlerTest {

    @Mock
    private WebRequest request;

    private EntityExceptionHandler entityExceptionHandler = new EntityExceptionHandler();

    @InjectMocks
    private EntityExceptionHandler mockService= Mockito.spy(entityExceptionHandler);

    @org.junit.Test
    public void testhandleConflict() {
        TrackerException trackerException = new TrackerException("test");
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity errorResponseEntity = mockService.handleConflict(trackerException,request);
        Assert.assertEquals(responseEntity.getStatusCode(),errorResponseEntity.getStatusCode());
    }
}