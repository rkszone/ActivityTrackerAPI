package com.activity.tracker.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileUploadModelTest {

    FileUploadModel fileUploadModel =  new FileUploadModel();
    @Test
    void getFileName() {
        fileUploadModel.setFileName("test");
        Assert.assertEquals("test",fileUploadModel.getFileName());
    }

    @Test
    void getStatus() {
        fileUploadModel.setStatus("test");
        Assert.assertEquals("test",fileUploadModel.getStatus());
    }

    @Test
    void getFileType() {
        fileUploadModel.setFileType("test");
        Assert.assertEquals("test",fileUploadModel.getFileType());
    }

    @Test
    void getSize() {
        fileUploadModel.setSize(0);
        Assert.assertEquals(0,fileUploadModel.getSize());
    }

    @Test
    void getErrorMessage() {
        fileUploadModel.setErrorMessage("test");
        Assert.assertEquals("test",fileUploadModel.getErrorMessage());
    }

    @Test
    void getToString() {
        Assert.assertNotNull(fileUploadModel.toString());
    }

    @Test
    void equalsSuperClass() {
        EqualsVerifier.forClass(FileUploadModel.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .withRedefinedSuperclass()
                .verify();
    }
}