package com.activity.tracker.controller;

import com.activity.tracker.service.IActivityService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ActivityControllerTest {

    @Mock
    private IActivityService activityService;

    @InjectMocks
    private ActivityController activityController;

    @Test
    void uploadMultipleFiles() {
//        when(activityService.fileUploadModels()).thenReturn(localization);
//        List<RelevantContent> relevantContentList = new ArrayList<>();
//        RelevantContent relevantContent = new RelevantContent();
//        relevantContent.setTitle("RC 1");
//        relevantContentList.add(relevantContent);
//        when(contentApiService.retrieveContent(any(), any())).thenReturn(relevantContentList);
//        ResponseEntity<List<RelevantContent>> relevantContentEntities = contentApiController.getContent(Arrays.asList("3582"), "ODA");
//        Assert.assertNotNull(relevantContentEntities);
//        Assert.assertEquals(HttpStatus.OK, relevantContentEntities.getStatusCode());
//        Assert.assertEquals("RC 1", relevantContentEntities.getBody().get(0).getTitle());
    }

    @Test
    void getActivities() {
    }

    @Test
    void getActivitySummaryData() {
    }

    @Test
    void deleteActivity() {
    }
}