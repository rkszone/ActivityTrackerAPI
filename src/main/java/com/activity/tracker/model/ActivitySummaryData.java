package com.activity.tracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * ActivitySummaryData has all required fields of Activity Summary Data
 */
@Data
@EqualsAndHashCode
@ToString
public class ActivitySummaryData {

    @JsonProperty("activity")
    private ActivityModel activity;

    @JsonProperty("total_distance")
    private long totalDistance;

    @JsonProperty("total_duration")
    private long totalDuration;

    @JsonProperty("average_power")
    private double avgPower;

    @JsonProperty("average_cadence")
    private double avgCadence;
}
