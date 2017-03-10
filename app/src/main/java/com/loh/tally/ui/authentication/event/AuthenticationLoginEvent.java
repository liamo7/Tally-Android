package com.loh.tally.ui.authentication.event;

import com.loh.tally.domain.event.BaseEvent;

/**
 * File: AuthenticationLoginEvent.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public final class AuthenticationLoginEvent implements BaseEvent {

    private final String email;
    private final String password;


    public AuthenticationLoginEvent(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
