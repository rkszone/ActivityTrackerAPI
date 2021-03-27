package com.activity.tracker.service;

import com.activity.tracker.model.ActivitySummaryData;
import com.activity.tracker.model.FileUploadModel;
import com.activity.tracker.model.PageActivity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IActivityService {

    List<FileUploadModel> fileUploadModels(MultipartFile[] files) throws Exception;

    PageActivity getActivities(int pageNo, int pageSize) throws Exception;

    ActivitySummaryData geActivitySummaryData(long id) throws Exception;
}
