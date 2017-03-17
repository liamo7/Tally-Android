package com.loh.tally.domain.database.modules;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.loh.tally.domain.model.Module;
import com.loh.tally.ui.base.AsyncCallback;
import com.loh.tally.ui.base.dagger.scope.ApplicationScope;

import javax.inject.Inject;

/**
 * File: ModuleServiceImpl.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 */
@ApplicationScope
public class ModuleServiceImpl implements ModuleService {

    private final static String REF_USER_ENROLLED = "user-rooms";
    private final static String REF_USER_CREATED = "user-rooms-created";
    private final static String REF_MODULES = "rooms";

    private final FirebaseDatabase firebaseDatabase;
    private final DatabaseReference moduleReference;

    private DatabaseReference userEnrolledRef;
    private DatabaseReference userCreatedRef;

    @Inject
    public ModuleServiceImpl(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
        this.moduleReference = this.firebaseDatabase.getReference(REF_MODULES);
    }

    @Override
    public DatabaseReference getModuleEnrolledReference(String userID) {
        return userEnrolledRef = firebaseDatabase.getReference(REF_USER_ENROLLED).child(userID);
    }

    @Override
    public DatabaseReference getModuleCreatedReference(String userID) {
        return userCreatedRef = firebaseDatabase.getReference(REF_USER_CREATED).child(userID);
    }

    @Override
    public void enrollOnModule(String moduleID, String userID, AsyncCallback<Boolean> callback) {
        moduleReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean enrolled = false;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Module module = snapshot.getValue(Module.class);
                    module.setId(snapshot.getKey());
                    if (module.getCode().equalsIgnoreCase(moduleID)) {
                        enroll(module, userID);
                        enrolled = true;
                        callback.onSuccess(true);
                        break;
                    }
                }

                if (!enrolled) {
                    callback.onError("");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError(databaseError.getMessage());
            }
        });
    }

    private void enroll(Module module, String userID) {
        getModuleEnrolledReference(userID).child(module.getId()).setValue(module);
    }


}
