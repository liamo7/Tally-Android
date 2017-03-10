package com.loh.tally.domain.database.user;

import com.google.firebase.auth.FirebaseUser;

/**
 * File: UserService.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
public interface UserService {

    void createUserEntry(FirebaseUser firebaseUser);
}
