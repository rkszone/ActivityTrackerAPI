package com.activity.tracker.controller;

import com.activity.tracker.model.ActivityModel;
import com.activity.tracker.model.ActivitySummaryData;
import com.activity.tracker.model.FileUploadModel;
import com.activity.tracker.model.PageActivity;
import com.activity.tracker.service.IActivityService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.misusing.UnfinishedVerificationException;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ActivityControllerTest {

    @Mock
    private IActivityService activityService;

    @InjectMocks
    private ActivityController activityController;

    @Before
    public void setUp() {
        activityController = new ActivityController(activityService);
        activityController.setActivityService(activityService);
    }

    @org.junit.Test
    public void uploadMultipleFiles() {
        List<FileUploadModel> fileUploadModels = new ArrayList<>();
        FileUploadModel fileUploadModel =  new FileUploadModel();
        fileUploadModel.setStatus("success");
        fileUploadModel.setFileName("test");
        fileUploadModel.setFileType("csv");
        fileUploadModel.setSize(200);
        fileUploadModels.add(fileUploadModel);
        when(activityService.fileUploadModels(new MultipartFile[1])).thenReturn(fileUploadModels);
        List<FileUploadModel> fileUploadModels1 = activityController.uploadMultipleFiles(new MultipartFile[1]);
        Assert.assertNotNull(fileUploadModels1);
        Assert.assertEquals("success", fileUploadModels1.get(0).getStatus());
    }

    @org.junit.Test
    public void getActivities() {
        PageActivity pageActivity =  new PageActivity();
        pageActivity.setTotalElement(10);
        when(activityService.getActivities(0,1)).thenReturn(pageActivity);
        PageActivity pageActivity1 = activityController.getActivities(0,1);
        Assert.assertNotNull(pageActivity1);
        Assert.assertEquals(10, pageActivity1.getTotalElement());
    }

    @org.junit.Test
    public void getActivitySummaryData() {
        ActivitySummaryData activitySummaryData1 =  new ActivitySummaryData();
        ActivityModel activityModel =  new ActivityModel();
        activityModel.setName("test");
        activitySummaryData1.setActivity(activityModel);
        when(activityService.getActivitySummaryData(1)).thenReturn(activitySummaryData1);
        ActivitySummaryData activitySummaryData = activityController.getActivitySummaryData(1);
        Assert.assertNotNull(activitySummaryData);
        Assert.assertEquals("test", activitySummaryData.getActivity().getName());
    }

    @org.junit.Test
    public void deleteActivity() {
        activityController = new ActivityController(activityService);
        activityController.deleteActivity(Long.valueOf(1));
        try {
            Assert.assertNotNull(activityController.getActivityService());
            verify(activityService).deleteById(any());
            verify(activityService,atLeastOnce()).deleteById(any());
        } catch (UnfinishedVerificationException exception) {

        }
    }
}