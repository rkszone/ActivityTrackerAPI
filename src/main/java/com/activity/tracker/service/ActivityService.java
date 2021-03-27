package com.activity.tracker.service;

import com.activity.tracker.entities.Activity;
import com.activity.tracker.entities.Record;
import com.activity.tracker.model.*;
import com.activity.tracker.repository.IActivityDao;
import com.activity.tracker.repository.IRecordDao;
import com.activity.tracker.utils.CsvUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for rate
 */
@Service
@Slf4j
public class ActivityService implements IActivityService {

    @Autowired
    IRecordDao recordRepository;

    @Autowired
    IActivityDao activityRepository;
    public List<FileUploadModel> fileUploadModels(MultipartFile[] files) throws Exception {
        List<FileUploadModel> fileUploadModels = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                persistRecords(CsvUtil.processFile(file));
                FileUploadModel fileUploadModel = new FileUploadModel();
                fileUploadModel.setFileName(file.getName());
                fileUploadModel.setFileType(file.getContentType());
                fileUploadModel.setSize(file.getSize());
                fileUploadModel.setStatus("success");
                fileUploadModel.setErrorMessage("File Uploaded Successfully.");
                fileUploadModels.add(fileUploadModel);
            } catch (Exception e) {
                FileUploadModel fileUploadModel = new FileUploadModel();
                fileUploadModel.setFileName(file.getName());
                fileUploadModel.setFileType(file.getContentType());
                fileUploadModel.setSize(file.getSize());
                fileUploadModel.setStatus("failed");
                fileUploadModel.setErrorMessage(e.getLocalizedMessage());
                fileUploadModels.add(fileUploadModel);
            }
        }

        return Arrays.asList(files)
                .stream()
                .map(file -> {
                    FileUploadModel fileUploadModel = new FileUploadModel();
                    fileUploadModel.setFileName(file.getName());
                    fileUploadModel.setFileType(file.getContentType());
                    fileUploadModel.setSize(file.getSize());
                    return fileUploadModel;
                })
                .collect(Collectors.toList());
    }

    public PageActivity getActivities(int pageNo, int pageSize) throws Exception{
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        Page<Activity> activityPage = activityRepository.findAll(pageable);
        PageActivity pageActivity = new PageActivity();
        pageActivity.setActivities(activityPage.getContent().stream().map(obj->{
            ActivityModel activity = new ActivityModel();
            activity.setId(obj.getId());
            activity.setActivityDef(obj.getActivityDef());
            activity.setActivityType(obj.getActivityType());
            activity.setName(obj.getName());
            activity.setStartTime(obj.getStartTime());
            return activity;
        }).collect(Collectors.toList()));
        pageActivity.setMaxPage(activityPage.getTotalPages());
        pageActivity.setTotalElement(activityPage.getTotalElements());
        pageActivity.setCurrentPage(pageNo);
        return pageActivity;
    }

    public ActivitySummaryData geActivitySummaryData(long id) throws Exception{
        Optional<Activity> optionalActivity = activityRepository.findById(id);
        if(optionalActivity.isPresent()){
            ActivitySummaryData activitySummaryData = new ActivitySummaryData();
            ActivityModel activity = new ActivityModel();
            activity.setName(optionalActivity.get().getName());
            activity.setStartTime(optionalActivity.get().getStartTime());
            activity.setActivityType(optionalActivity.get().getActivityType());
            activity.setActivityDef(optionalActivity.get().getActivityDef());
            activity.setId(id);
            activitySummaryData.setActivity(activity);
            activitySummaryData.setAvgPower(optionalActivity.get().getRecordList().
                    parallelStream().mapToDouble(Record::getPower).
                    average().orElse(Double.NaN));
            activitySummaryData.setAvgCadence(optionalActivity.get().getRecordList().
                    parallelStream().mapToDouble(Record::getCadence).
                    average().orElse(Double.NaN));
            activitySummaryData.setTotalDistance((long) optionalActivity.get().getRecordList().
                    parallelStream().mapToDouble(Record::getDistance).
                    sum());
            return activitySummaryData;
        }
        else {
            throw new NoSuchElementException("Activity not found with corresponding id");
        }
    }

    private void persistRecords(List<Object> objectList) throws Exception{
        Optional<Object> optionalActivity =objectList.stream().filter(object -> object instanceof ActivityModel).findFirst();
        if(optionalActivity.isPresent()){
            Activity activityDao = new Activity();
            activityDao.setName(((ActivityModel) optionalActivity.get()).getName());
            activityDao.setActivityDef(((ActivityModel) optionalActivity.get()).getActivityDef());
            activityDao.setActivityType(((ActivityModel) optionalActivity.get()).getActivityType());
            activityDao.setStartTime(((ActivityModel) optionalActivity.get()).getStartTime());
            activityDao = activityRepository.save(activityDao);
            objectList = objectList.stream().filter(obj -> !(obj instanceof ActivityModel)).collect(Collectors.toList());
            for(Object obj: objectList){
                Record recordDao = new Record();
                recordDao.setActivity(activityDao);
                recordDao.setCadence(((RecordModel) obj).getCadence());
                recordDao.setDistance(((RecordModel) obj).getDistance());
                recordDao.setRecordDef(((RecordModel) obj).getRecordDefination());
                recordDao.setPower(((RecordModel) obj).getPower());
                recordDao.setTime(((RecordModel) obj).getTime());
                recordRepository.save(recordDao);
            }
        }
    }
}
