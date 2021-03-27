package com.activity.tracker.utils;

import com.activity.tracker.model.ActivityModel;
import com.activity.tracker.model.RecordModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CsvUtil {

    public static List<Object> processFile(MultipartFile file) throws Exception {
        List<Object> objectList = new ArrayList<>();
        BufferedReader fileReader = new BufferedReader(new
                InputStreamReader(new BOMInputStream(file.getInputStream()), "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.DEFAULT.withHeader());
        Iterable<CSVRecord> csvRecords = csvParser.getRecords();
        Map<String, Integer> headerMap = csvParser.getHeaderMap();
        String type = "header";
        for (CSVRecord csvRecord : csvRecords) {
            System.out.println(csvRecord.toString());
            boolean flag = true;
            if (headerMap.get("activty_def") != null && csvRecord.get(headerMap.get("activty_def")).equals("record_def")) {
                headerMap.clear();
                type = "record";
                for (int inc = 0; inc < csvRecord.size(); ++inc) {
                    headerMap.put(csvRecord.get(inc), inc);
                }
                flag = false;
            }
            if(flag){
                Map<String, String> record = new HashMap<>();
                for (String stringSet : headerMap.keySet()) {
                    if(!stringSet.isEmpty()){
                        record.put(stringSet, csvRecord.get(headerMap.get(stringSet)));
                    }
                }
                ObjectMapper objectMapper = new ObjectMapper();
                Object objRecord;
                if (type.equals("header")) {
                    objRecord = objectMapper.convertValue(record, ActivityModel.class);
                } else {
                    objRecord = objectMapper.convertValue(record, RecordModel.class);
                }
                objectList.add(objRecord);
            }
        }
        return objectList;
    }
}
