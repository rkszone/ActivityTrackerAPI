package com.activity.tracker.service;

import com.activity.tracker.entities.Activity;
import com.activity.tracker.entities.Record;
import com.activity.tracker.mapper.ActivityMapper;
import com.activity.tracker.model.*;
import com.activity.tracker.repository.IActivityRepository;
import com.activity.tracker.repository.IRecordRepository;
import com.activity.tracker.utils.CsvUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Service class for activity
 */
@Data
@Service
@Slf4j
public class ActivityService implements IActivityService {

    @Autowired
    private IRecordRepository recordRepository;

    @Autowired
    private IActivityRepository activityRepository;

    public ActivityService (IActivityRepository activityRepository, IRecordRepository recordRepository) {
        this.activityRepository = activityRepository;
        this.recordRepository = recordRepository;
    }

    /**
     * file Upload Models
     * @param files csv file
     * @return List of FileUploadModel
     */
    public List<FileUploadModel> fileUploadModels(MultipartFile[] files) {
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
                fileUploadModel.setFileName(file != null ? file.getName() : "");
                fileUploadModel.setFileType(file != null ? file.getContentType() : "");
                fileUploadModel.setSize(file != null ? file.getSize() : 0);
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

    /**
     * Get Activities
     * @param pageNo start page
     * @param pageSize numbers of page
     * @return PageActivity
     */
    public PageActivity getActivities(int pageNo, int pageSize) {
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

    /**
     * Get Activity Summary Data
     * @param id activity id
     * @return activity summary data
     */
    public ActivitySummaryData getActivitySummaryData(long id) {
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

            long startTime = optionalActivity.get().getStartTime().getTime();
            long endTime = optionalActivity.get().getRecordList().size() > 0 ? Collections.max(optionalActivity.get().getRecordList(), Comparator.comparing(Record::getTime)).getTime().getTime() : 0;
            activitySummaryData.setTotalDuration(TimeUnit.MILLISECONDS.toMinutes(endTime - startTime));

            activitySummaryData.setTotalDistance((long) optionalActivity.get().getRecordList().
                    parallelStream().mapToDouble(Record::getDistance).
                    sum());
            return activitySummaryData;
        }
        else {
            throw new NoSuchElementException("Activity not found with corresponding id");
        }
    }

    /**
     * Delete activity by Id
     * @param id activity Id
     */
    @Override
    public void deleteById(Long id) {
        activityRepository.deleteById(id);
    }

    /**
     * Save activity with records
     * @param objectList csv parsed data object
     */
    private void persistRecords(List<Object> objectList) {
        Optional<Object> optionalActivity =objectList.stream().filter(object -> object instanceof ActivityModel).findFirst();
        if(optionalActivity.isPresent()){
            Activity activityDao = ActivityMapper.getActivity(optionalActivity);
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
