package com.loh.tally.ui.authentication.event;

import com.loh.tally.domain.event.BaseEvent;

/**
 * File: AuthenticationChoiceEvent.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 */
public final class AuthenticationChoiceEvent implements BaseEvent {

    private final AuthenticationChoice choice;

    public AuthenticationChoiceEvent(AuthenticationChoice choice) {
        this.choice = choice;
    }

    public AuthenticationChoice getChoice() {
        return choice;
    }

    public enum AuthenticationChoice {
        REGISTER,
        LOGIN
    }
}
