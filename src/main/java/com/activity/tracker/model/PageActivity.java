package com.activity.tracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * PageActivity has all required fields of Page Activity
 */
@Data
@EqualsAndHashCode
@ToString
public class PageActivity {

    @JsonProperty("activities")
    private List<ActivityModel> activities;

    @JsonProperty("maxPage")
    private int maxPage;

    @JsonProperty("currentPage")
    private int currentPage;

    @JsonProperty("totalElement")
    private long totalElement;
}
