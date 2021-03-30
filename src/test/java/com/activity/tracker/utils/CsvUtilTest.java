package com.activity.tracker.utils;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class CsvUtilTest {

    @org.junit.Test
    public void processFile() throws IOException {
        Path path = Paths.get(System.getProperty("user.dir")+ "/src/test/resources/assignment_activity.csv");
        String name = "assignment_activity.csv";
        String originalFileName = "assignment_activity.csv";
        String contentType = "text/csv";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        MultipartFile result = new MockMultipartFile(name,
                originalFileName, contentType, content);
        List<Object> objectList = CsvUtil.processFile(result);
        Assert.assertNotNull(objectList);
        Assert.assertEquals(11,objectList.size());
    }
}