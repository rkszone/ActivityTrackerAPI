package com.activity.tracker.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PageActivityTest {

    PageActivity pageActivity =  new PageActivity();
    @Test
    void getActivities() {
        pageActivity.setActivities(new ArrayList<>());
        Assert.assertNotNull(pageActivity.getActivities());
    }

    @Test
    void getMaxPage() {
        pageActivity.setMaxPage(1);
        Assert.assertEquals(1,pageActivity.getMaxPage());
    }

    @Test
    void getCurrentPage() {
        pageActivity.setCurrentPage(1);
        Assert.assertEquals(1,pageActivity.getCurrentPage());
    }

    @Test
    void getTotalElement() {
        pageActivity.setTotalElement(1);
        Assert.assertEquals(1,pageActivity.getTotalElement());
    }

    @Test
    void getToString() {
        Assert.assertNotNull(pageActivity.toString());
    }

    @Test
    void equalsSuperClass() {
        EqualsVerifier.forClass(PageActivity.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .withRedefinedSuperclass()
                .verify();
    }
}