package com.loh.tally.domain.database.presentation;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.loh.tally.ui.base.dagger.scope.ApplicationScope;

import javax.inject.Inject;

/**
 * File: PresentationServiceImpl.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 */
@ApplicationScope
public class PresentationServiceImpl implements PresentationService {

    private static final String REF_PRESENTATIONS = "presentations";
    private static final String REF_PRESENTATIONS_MODULE = "presentation-rooms";
    private static final String REF_POLLS = "polls";
    private static final String REF_POLL_RESPONSES = "poll-responses";

    private final FirebaseDatabase firebaseDatabase;
    private DatabaseReference presentationRef;
    private DatabaseReference presentationModuleRef;
    private DatabaseReference pollRef;
    private DatabaseReference pollResponseRef;

    @Inject
    public PresentationServiceImpl(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
        this.presentationRef = this.firebaseDatabase.getReference(REF_PRESENTATIONS);
        this.presentationModuleRef = this.firebaseDatabase.getReference(REF_PRESENTATIONS_MODULE);
        this.pollRef = this.firebaseDatabase.getReference(REF_POLLS);
        this.pollResponseRef = this.firebaseDatabase.getReference(REF_POLL_RESPONSES);
    }

    @Override
    public DatabaseReference getPresentationReference(String presentationID) {
        return this.presentationRef.child(presentationID);
    }

    @Override
    public DatabaseReference getPresentationModuleReference(String moduleID) {
        return this.presentationModuleRef.child(moduleID);
    }

    @Override
    public DatabaseReference getPollsReference(String presentationID) {
        return this.pollRef.child(presentationID);
    }

    @Override
    public DatabaseReference getPollResponsesReference(String pollID) {
        return this.pollResponseRef.child(pollID);
    }
}
