package com.activity.tracker.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;

class RecordModelTest {

    RecordModel recordModel = new RecordModel();

    @Test
    void getRecordDefination() {
        recordModel.setRecordDefination("test");
        Assert.assertEquals("test", recordModel.getRecordDefination());
    }

    @Test
    void getTime() {
        Date date = new Date();
        recordModel.setTime(date);
        Assert.assertEquals(date, recordModel.getTime());
    }

    @Test
    void getDistance() {
        recordModel.setDistance(1);
        Assert.assertEquals(1, recordModel.getDistance());
    }

    @Test
    void getPower() {
        recordModel.setPower(1);
        Assert.assertEquals(1, recordModel.getPower());
    }

    @Test
    void getToString() {
        Assert.assertNotNull(recordModel.toString());
    }

    @Test
    void equalsSuperClass() {
        EqualsVerifier.forClass(RecordModel.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .withRedefinedSuperclass()
                .verify();
    }
}