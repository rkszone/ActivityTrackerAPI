package com.activity.tracker.service;

import com.activity.tracker.exception.TrackerException;
import com.activity.tracker.model.ActivitySummaryData;
import com.activity.tracker.model.FileUploadModel;
import com.activity.tracker.model.PageActivity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * IActivityService contain all activity service methods
 */
public interface IActivityService {

    /**
     * file Upload Models
     * @param files csv file
     * @return List of FileUploadModel
     */
    List<FileUploadModel> fileUploadModels(MultipartFile[] files) throws TrackerException;

    /**
     * Get Activities
     * @param pageNo start page
     * @param pageSize numbers of page
     * @return PageActivity
     */
    PageActivity getActivities(int pageNo, int pageSize) throws TrackerException;

    /**
     * Get Activity Summary Data
     * @param id activity id
     * @return activity summary data
     */
    ActivitySummaryData getActivitySummaryData(long id) throws TrackerException;

    /**
     * Delete activity by Id
     * @param id activity Id
     */
    void deleteById(Long id);
}
