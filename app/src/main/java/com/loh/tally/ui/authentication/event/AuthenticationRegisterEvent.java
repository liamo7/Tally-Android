package com.loh.tally.ui.authentication.event;

import com.loh.tally.domain.event.BaseEvent;

/**
 * File: AuthenticationRegisterEvent.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 */
public final class AuthenticationRegisterEvent implements BaseEvent {

    private final String email;
    private final String password;


    public AuthenticationRegisterEvent(String email, String password) {
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
