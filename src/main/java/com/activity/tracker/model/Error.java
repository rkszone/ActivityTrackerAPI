package com.activity.tracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Error {

    @JsonProperty
    private String message;

    @JsonProperty
    private String code;

    @JsonProperty
    private String type;

    /**
     * Constructor
     * @param message message of the error
     * @param code code of the error
     * @param type type of the error
     */
    public Error(String message, String code, String type) {
        this.message = message;
        this.code = code;
        this.type = type;
    }
}