package com.activity.tracker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class FileUploadModel {
    private String fileName;
    private String status;
    private String fileType;
    private long size;
    private String errorMessage;
}
