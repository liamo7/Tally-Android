package com.loh.tally.domain.authentication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.loh.tally.ui.authentication.event.AuthenticationErrorEvent;
import com.loh.tally.ui.authentication.event.AuthenticationSuccessEvent;
import com.loh.tally.ui.base.dagger.scope.ApplicationScope;
import com.squareup.otto.Bus;

/**
 * File: AuthenticationManagerImpl.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
@ApplicationScope
public class AuthenticationManagerImpl implements AuthenticationManager {

    private final FirebaseAuth firebaseAuth;
    private final Bus bus;

    public AuthenticationManagerImpl(FirebaseAuth firebaseAuth, Bus bus) {
        this.firebaseAuth = firebaseAuth;
        this.bus = bus;
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
                .addOnSuccessListener(authResult -> {
                    bus.post(new AuthenticationSuccessEvent());
                })
                .addOnFailureListener(authResult -> {
                    bus.post(new AuthenticationErrorEvent(authResult.getMessage()));
                });
    }

    // TODO: 10/03/2017 Create USER entry within USER db
    @Override
    public void register(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    bus.post(new AuthenticationSuccessEvent());
                })
                .addOnFailureListener(authResult -> {
                    // failure, report message
                    bus.post(new AuthenticationErrorEvent(authResult.getMessage()));
                });
    }
}
