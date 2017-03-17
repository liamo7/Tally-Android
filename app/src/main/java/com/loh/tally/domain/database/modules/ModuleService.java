package com.loh.tally.domain.database.modules;

import com.google.firebase.database.DatabaseReference;
import com.loh.tally.ui.base.AsyncCallback;

/**
 * File: ModuleService.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 */
public interface ModuleService {

    DatabaseReference getModuleEnrolledReference(String userID);

    DatabaseReference getModuleCreatedReference(String userID);

    void enrollOnModule(String moduleID, String userID, AsyncCallback<Boolean> callback);
}
