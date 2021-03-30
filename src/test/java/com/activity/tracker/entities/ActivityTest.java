package com.activity.tracker.entities;

import com.activity.tracker.model.ActivitySummaryData;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;


class ActivityTest {

    Activity activity = new Activity();

    @Test
    void getActivityId() {
        activity.setId(0);
        Assert.assertEquals(0, activity.getId());
    }

    @Test
    void getActivityDef() {
        activity.setActivityDef("test");
        Assert.assertEquals("test", activity.getActivityDef());
    }

    @Test
    void getName() {
        activity.setName("test");
        Assert.assertEquals("test", activity.getName());
    }

    @Test
    void getActivityType() {
        activity.setActivityType("test");
        Assert.assertEquals("test", activity.getActivityType());
    }

    @Test
    void getStartTime() {
        Date date = new Date();
        activity.setStartTime(date);
        Assert.assertEquals(date, activity.getStartTime());
    }


    @Test
    void getCreated() {
        Date date = new Date();
        activity.setCreated(date);
        Assert.assertEquals(date, activity.getCreated());
    }

    @Test
    void getIs_deleted() {
        activity.setIs_deleted(false);
        Assert.assertEquals(false, activity.getIs_deleted());
    }

    @Test
    void getRecordList() {
        activity.setRecordList(new ArrayList<>());
        Assert.assertNotNull(activity.getRecordList());
    }

    @Test
    void getToString() {
        Assert.assertEquals("Activity(id=0, activityDef=null, name=null, activityType=null, startTime=null, created=null, is_deleted=false, recordList=[])",activity.toString());
    }

    @Ignore
    void equalsSuperClass() {
        EqualsVerifier.forClass(Activity.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .withRedefinedSuperclass()
                .verify();
    }
}