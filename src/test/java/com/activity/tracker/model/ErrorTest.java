package com.activity.tracker.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorTest {

    Error error =  new Error("test","404","test");
    @Test
    void getMessage() {
        Assert.assertEquals("test",error.getMessage());
    }

    @Test
    void getCode() {
        Assert.assertEquals("404",error.getCode());
    }

    @Test
    void getType() {
        Assert.assertEquals("test",error.getType());
    }
}