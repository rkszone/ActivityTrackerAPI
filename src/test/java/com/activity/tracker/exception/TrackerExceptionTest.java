package com.activity.tracker.exception;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TrackerExceptionTest {

    @org.junit.Test
    public void testTrackerException() {
        Assert.assertEquals(new TrackerException("TrackerException").getMessage(), "TrackerException");
    }

}