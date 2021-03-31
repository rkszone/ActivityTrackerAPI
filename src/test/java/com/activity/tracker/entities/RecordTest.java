package com.activity.tracker.entities;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RecordTest {

    Record record =  new Record();

    @Test
    void getActivityId() {
        record.setId(0);
        Assert.assertEquals(0, record.getId());
    }

    @Test
    void getActivity() {
        record.setActivity(new Activity());
        Assert.assertNotNull(record.getActivity());
    }

    @Test
    void getRecordDef() {
        record.setRecordDef("test");
        Assert.assertEquals("test", record.getRecordDef());
    }

    @Test
    void getTime() {
        Date date = new Date();
        record.setTime(date);
        Assert.assertEquals(date, record.getTime());
    }

    @Test
    void getDistance() {
        record.setDistance(1);
        Assert.assertEquals(1, record.getDistance());
    }

    @Test
    void getPower() {
        record.setPower(1);
        Assert.assertEquals(1, record.getPower());;
    }

    @Test
    void getCadence() {
        record.setCadence(1);
        Assert.assertEquals(1, record.getCadence());;
    }

    @Test
    void getCreated() {
        Date date = new Date();
        record.setCreated(date);
        Assert.assertEquals(date, record.getCreated());
    }

    @Test
    void getIs_deleted() {
        record.setIs_deleted(false);
        Assert.assertEquals(false, record.getIs_deleted());
    }

    @Test
    void getToString() {
        Assert.assertEquals("Record(id=0, recordDef=null, time=null, distance=0, power=0, cadence=0, created=null, is_deleted=false)", record.toString());
    }

    @Test
    void equalsSuperClass() {
        Assert.assertTrue(record.equals(new Record()));
        Assert.assertNotNull(record.hashCode());
    }
}