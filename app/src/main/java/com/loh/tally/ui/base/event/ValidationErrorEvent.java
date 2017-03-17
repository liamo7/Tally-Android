package com.loh.tally.ui.base.event;

import com.loh.tally.domain.event.BaseEvent;

/**
 * File: ValidationErrorEvent.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 */
public final class ValidationErrorEvent implements BaseEvent {

    private final String message;

    public ValidationErrorEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
