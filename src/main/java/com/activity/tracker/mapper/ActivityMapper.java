package com.activity.tracker.mapper;

import com.activity.tracker.entities.Activity;
import com.activity.tracker.model.ActivityModel;

import java.util.Optional;

public class ActivityMapper {

    /**
     * Get ActivityDao
     * @param optionalActivity activity object
     * @return Activity Entity
     */
    public static Activity getActivity(Optional<Object> optionalActivity) {
        Activity activityDao = new Activity();
        activityDao.setName(((ActivityModel) optionalActivity.get()).getName());
        activityDao.setActivityDef(((ActivityModel) optionalActivity.get()).getActivityDef());
        activityDao.setActivityType(((ActivityModel) optionalActivity.get()).getActivityType());
        activityDao.setStartTime(((ActivityModel) optionalActivity.get()).getStartTime());
        return activityDao;
    }
}
