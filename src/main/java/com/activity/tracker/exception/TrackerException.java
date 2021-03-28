package com.activity.tracker.exception;

/**
 * Tracker Exception when something is wrong in API
 */
public class TrackerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor TrackerException
     * @param message the message of the error
     */
    public TrackerException(String message) {
        super(message);
    }
}
