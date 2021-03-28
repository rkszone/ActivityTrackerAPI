package com.activity.tracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * RecordModel has all required fields of Record Model
 */
@Data
@EqualsAndHashCode
@ToString
public class RecordModel {

    @JsonProperty("record_def")
    private String recordDefination;

    @JsonProperty("time")
    private Date time;

    @JsonProperty("distance")
    private int distance;

    @JsonProperty("power")
    private int power;

    @JsonProperty("cadence")
    private int cadence;

}
