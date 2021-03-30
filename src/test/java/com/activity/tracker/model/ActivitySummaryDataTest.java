package com.activity.tracker.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivitySummaryDataTest {

    ActivitySummaryData activitySummaryData =  new ActivitySummaryData();
    @Test
    void getActivity() {
        activitySummaryData.setActivity(new ActivityModel());
        Assert.assertNotNull(activitySummaryData.getActivity());
    }

    @Test
    void getTotalDistance() {
        activitySummaryData.setTotalDistance(0);
        Assert.assertEquals(0,activitySummaryData.getTotalDistance());
    }

    @Test
    void getTotalDuration() {
        activitySummaryData.setTotalDuration(0);
        Assert.assertEquals(0,activitySummaryData.getTotalDuration());
    }

    @Test
    void getAvgPower() {
        activitySummaryData.setAvgPower(0);
        Assert.assertNotNull(activitySummaryData.getAvgPower());
    }

    @Test
    void getAvgCadence() {
        activitySummaryData.setAvgCadence(0);
        Assert.assertNotNull(activitySummaryData.getAvgCadence());
    }

    @Test
    void getToString() {
        Assert.assertNotNull(activitySummaryData.toString());
    }

    @Test
    void equalsSuperClass() {
        EqualsVerifier.forClass(ActivitySummaryData.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .withRedefinedSuperclass()
                .verify();
    }
}