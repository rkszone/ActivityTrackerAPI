package com.activity.tracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class ActivitySummaryData {

    @JsonProperty("activity")
    private ActivityModel activity;

    @JsonProperty("total_distance")
    private long totalDistance;

    @JsonProperty("average_power")
    private double avgPower;

    @JsonProperty("average_cadence")
    private double avgCadence;
}
