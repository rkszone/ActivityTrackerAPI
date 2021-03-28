package com.activity.tracker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * FileUploadModel has all required fields of File Upload Model
 */
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
