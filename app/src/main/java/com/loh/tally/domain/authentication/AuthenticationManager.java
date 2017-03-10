package com.loh.tally.domain.authentication;

import com.google.firebase.auth.FirebaseUser;

/**
 * File: AuthenticationManager.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public interface AuthenticationManager {

    boolean isLoggedIn();

    FirebaseUser getCurrentUser();

    void login(String email, String password);

    void register(String email, String password);
}
