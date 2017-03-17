package com.loh.tally.domain.database.user;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.loh.tally.domain.model.User;
import com.loh.tally.ui.base.dagger.scope.ApplicationScope;

import javax.inject.Inject;

/**
 * File: UserServiceImpl.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 */
@ApplicationScope
public class UserServiceImpl implements UserService {

    private static final String REF_USER = "user";

    private final FirebaseDatabase firebaseDatabase;
    private final DatabaseReference rootUserRef;

    @Inject
    public UserServiceImpl(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
        this.rootUserRef = firebaseDatabase.getReference(REF_USER);
    }

    @Override
    public void createUserEntry(FirebaseUser firebaseUser) {
        User user = new User(firebaseUser.getEmail(), firebaseUser.getUid());
        rootUserRef.child(firebaseUser.getUid()).setValue(user);

    }
}
