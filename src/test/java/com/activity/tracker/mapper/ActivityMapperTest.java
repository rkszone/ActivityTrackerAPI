package com.activity.tracker.mapper;

import com.activity.tracker.entities.Activity;
import com.activity.tracker.model.ActivityModel;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ActivityMapperTest {

    @org.junit.Test
    public  void getActivity() {
        ActivityModel activityModel = new ActivityModel();
        activityModel.setActivityDef("test");
        activityModel.setActivityType("test");
        activityModel.setName("test");
        activityModel.setStartTime(new Date());
        activityModel.setId(0);
        Optional<Object> optionalActivity = Optional.of(activityModel);
        Activity activity = ActivityMapper.getActivity(optionalActivity);
        Assert.assertNotNull(activity);
        Assert.assertEquals("test",activity.getName());
    }
}