package com.loh.tally.domain.authentication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.loh.tally.domain.database.user.UserService;
import com.loh.tally.ui.authentication.event.AuthenticationErrorEvent;
import com.loh.tally.ui.authentication.event.AuthenticationLogoutEvent;
import com.loh.tally.ui.authentication.event.AuthenticationSuccessEvent;
import com.loh.tally.ui.base.dagger.scope.ApplicationScope;
import com.squareup.otto.Bus;

import javax.inject.Inject;

/**
 * File: AuthenticationManagerImpl.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Handles all user authentication within the application. Communicates with Firebase
 * authentication.
 */
@ApplicationScope
public class AuthenticationManagerImpl implements AuthenticationManager {

    private final FirebaseAuth firebaseAuth;
    private final Bus bus;
    private final UserService userService;

    @Inject
    public AuthenticationManagerImpl(FirebaseAuth firebaseAuth, Bus bus, UserService userService) {
        this.firebaseAuth = firebaseAuth;
        this.bus = bus;
        this.userService = userService;
    }

    @Override
    public boolean isLoggedIn() {
        return firebaseAuth.getCurrentUser() != null;
    }

    @Override
    public FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }

    @Override
    public void login(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> bus.post(new AuthenticationSuccessEvent()))
                .addOnFailureListener(authResult -> bus.post(new AuthenticationErrorEvent(authResult.getMessage())));
    }

    @Override
    public void register(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    userService.createUserEntry(authResult.getUser());
                    bus.post(new AuthenticationSuccessEvent());
                })
                .addOnFailureListener(authResult -> bus.post(new AuthenticationErrorEvent(authResult.getMessage())));
    }

    @Override
    public void logout() {
        firebaseAuth.signOut();
        bus.post(new AuthenticationLogoutEvent());
    }
}
