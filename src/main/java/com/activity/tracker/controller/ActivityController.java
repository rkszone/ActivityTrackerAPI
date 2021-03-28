package com.activity.tracker.controller;

import com.activity.tracker.exception.TrackerException;
import com.activity.tracker.model.ActivitySummaryData;
import com.activity.tracker.model.FileUploadModel;
import com.activity.tracker.model.PageActivity;
import com.activity.tracker.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Activity Controller has all activity resources
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("activitytracker")
public class ActivityController {

    @Autowired
    IActivityService activityService;

    /**
     * Upload Multiple Files
     * @param files csv file
     * @return List of FileUploadModel
     * @throws TrackerException when something went wrong;
     */
    @PostMapping("/uploadFile")
    public List<FileUploadModel> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) throws TrackerException {
        return activityService.fileUploadModels(files);
    }

    /**
     * Get Activities
     * @param pageNo start page
     * @param pageSize numbers of page
     * @return PageActivity
     * @throws TrackerException when something went wrong;
     */
    @GetMapping(value = "/activities",produces = {MediaType.APPLICATION_JSON_VALUE})
    public PageActivity getActivities(@RequestParam(value = "pageNo",defaultValue = "1") int pageNo,
                                      @RequestParam(value = "pageSize",defaultValue = "10") int pageSize) throws TrackerException {
        return activityService.getActivities(pageNo,pageSize);
    }

    /**
     * Get Activity Summary Data
     * @param id Id of activity
     * @return ActivitySummaryData
     * @throws TrackerException when something went wrong;
     */
    @GetMapping(value = "/summaryData/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ActivitySummaryData getActivitySummaryData(@PathVariable(value = "id",required = true) long id) throws TrackerException {
        return activityService.getActivitySummaryData(id);
    }

    /**
     * Delete activity
     * @param id Id of activity
     * @throws TrackerException when something went wrong;
     */
    @DeleteMapping("/activities/{id}")
    public void deleteActivity(@PathVariable Long id) throws TrackerException {
        activityService.deleteById(id);
    }
}
