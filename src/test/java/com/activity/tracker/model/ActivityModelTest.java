package com.activity.tracker.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;

class ActivityModelTest {

    ActivityModel activityModel = new ActivityModel();
    @Test
    void getId() {
        activityModel.setId(0);
        Assert.assertEquals(0, activityModel.getId());
    }

    @Test
    void getActivityDef() {
        activityModel.setActivityDef("test");
        Assert.assertEquals("test", activityModel.getActivityDef());
    }

    @Test
    void getName() {
        activityModel.setName("test");
        Assert.assertEquals("test", activityModel.getName());
    }

    @Test
    void getActivityType() {
        activityModel.setActivityType("test");
        Assert.assertEquals("test", activityModel.getActivityType());
    }

    @Test
    void getStartTime() {
        Date date = new Date();
        activityModel.setStartTime(date);
        Assert.assertEquals(date, activityModel.getStartTime());
    }

    @Test
    void getToString() {
        Assert.assertNotNull(activityModel.toString());
    }

    @Test
    void equalsSuperClass() {
        EqualsVerifier.forClass(ActivityModel.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .withRedefinedSuperclass()
                .verify();
    }
}