package com.activity.tracker.controller;

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
 * Rest Resource for rate
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("activitytracker")
public class ActivityController {

    @Autowired
    IActivityService activityService;

    @PostMapping("/uploadFile")
    public List<FileUploadModel> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) throws Exception{
        return activityService.fileUploadModels(files);
    }

    @GetMapping(value = "/activities",produces = {MediaType.APPLICATION_JSON_VALUE})
    public PageActivity getActivities(@RequestParam(value = "pageNo",defaultValue = "1") int pageNo,
                                      @RequestParam(value = "pageSize",defaultValue = "10") int pageSize) throws Exception{
        return activityService.getActivities(pageNo,pageSize);
    }

    @GetMapping(value = "/summaryData/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ActivitySummaryData getActivitySummaryData(@PathVariable(value = "id",required = true) long id) throws Exception{
        return activityService.geActivitySummaryData(id);
    }

    @DeleteMapping("/activities/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        activityService.deleteById(id);
    }
}
