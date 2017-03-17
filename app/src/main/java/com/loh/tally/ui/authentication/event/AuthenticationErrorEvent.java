package com.loh.tally.ui.authentication.event;

import com.loh.tally.domain.event.BaseEvent;

/**
 * File: AuthenticationErrorEvent.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 */
public final class AuthenticationErrorEvent implements BaseEvent {

    private final String message;

    public AuthenticationErrorEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
