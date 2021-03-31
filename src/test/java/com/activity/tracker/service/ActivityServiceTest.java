package com.activity.tracker.service;

import com.activity.tracker.entities.Activity;
import com.activity.tracker.model.ActivitySummaryData;
import com.activity.tracker.model.FileUploadModel;
import com.activity.tracker.model.PageActivity;
import com.activity.tracker.repository.IActivityRepository;
import com.activity.tracker.repository.IRecordRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.misusing.UnfinishedVerificationException;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ActivityServiceTest {

    @Mock
    IRecordRepository recordRepository;

    @Mock
    IActivityRepository activityRepository;

    @InjectMocks
    private ActivityService activityService;

    @Before
    public void setUp() {
        activityService = new ActivityService(activityRepository,recordRepository);
    }

    @org.junit.Test
    public void fileUploadModels() {
        Path path = Paths.get(System.getProperty("user.dir")+ "/src/test/resources/assignment_activity.csv");
        String name = "assignment_activity.csv";
        String originalFileName = "assignment_activity.csv";
        String contentType = "text/csv";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        MultipartFile[] files = new MultipartFile[1];
        files[0] = new MockMultipartFile(name,originalFileName, contentType, content);
        Activity activity =  new Activity();
        activity.setName("test");
        when(activityRepository.save(any())).thenReturn(activity);
        List<FileUploadModel> fileUploadModels1 = activityService.fileUploadModels(files);
        Assert.assertNotNull(fileUploadModels1);
    }

    @org.junit.Test
    public void fileUploadModelsWithExcption() {
        activityService = new ActivityService(activityRepository,recordRepository);
        activityService.setActivityRepository(activityRepository);
        activityService.setRecordRepository(recordRepository);
        try {
            List<FileUploadModel> fileUploadModels1 = activityService.fileUploadModels(new MultipartFile[1]);
            Assert.assertNotNull(activityService.getActivityRepository());
            Assert.assertNotNull(activityService.getRecordRepository());
            Assert.assertEquals("failed",fileUploadModels1.get(0).getStatus());
        } catch (Exception e) {

        }
    }

    @org.junit.Test
    public void getActivities() {
        Page<Activity> activityPage = new Page<Activity>() {
            @Override
            public int getTotalPages() {
                return 1;
            }

            @Override
            public long getTotalElements() {
                return 1;
            }

            @Override
            public <U> Page<U> map(Function<? super Activity, ? extends U> function) {
                return null;
            }

            @Override
            public int getNumber() {
                return 1;
            }

            @Override
            public int getSize() {
                return 1;
            }

            @Override
            public int getNumberOfElements() {
                return 1;
            }

            @Override
            public List<Activity> getContent() {
                List<Activity> activities = new ArrayList<>();
                Activity activity =  new Activity();
                activities.add(activity);
                return activities;
            }

            @Override
            public boolean hasContent() {
                return true;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<Activity> iterator() {
                return null;
            }
        };
        when(activityRepository.findAll((Pageable) any())).thenReturn(activityPage);
        PageActivity pageActivity = activityService.getActivities(1,10);
        Assert.assertNotNull(pageActivity);
    }

    @org.junit.Test
    public void getActivitySummaryData() {
        Activity activity =  new Activity();
        activity.setName("test");
        activity.setActivityDef("test");
        activity.setActivityType("test");
        activity.setCreated(new Date());
        activity.setId(1);
        activity.setIs_deleted(false);
        activity.setRecordList(new ArrayList<>());
        activity.setStartTime(new Date());
        Optional<Activity> optionalActivity = Optional.of(activity);
        when(activityRepository.findById(any())).thenReturn(optionalActivity);
        ActivitySummaryData activitySummaryData = activityService.getActivitySummaryData(1);
        Assert.assertNotNull(activitySummaryData);
    }

    @org.junit.Test
    public void getActivitySummaryDataWithEmpty() {
        Optional<Activity> optionalActivity = Optional.empty();
        when(activityRepository.findById(any())).thenReturn(optionalActivity);
        try {
            activityService.getActivitySummaryData(1);
            verify(activityRepository).findById(any());
            verify(activityRepository,atLeastOnce()).findById(any());
        } catch (NoSuchElementException exception) {

        }

    }

    @org.junit.Test
    public void deleteById() {
        activityService.deleteById(Long.valueOf(1));
        try {
            verify(activityRepository).deleteById(any());
            verify(activityRepository,atLeastOnce()).deleteById(any());
        } catch (UnfinishedVerificationException exception) {

        }
    }
}