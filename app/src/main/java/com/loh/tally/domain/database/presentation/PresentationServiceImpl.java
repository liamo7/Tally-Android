package com.loh.tally.domain.database.presentation;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.loh.tally.ui.base.DataChangeCallback;
import com.loh.tally.ui.base.TransactionCallback;
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

    @Override
    public void submitMultipleChoiceResponse(String pollID, String userID, int index, boolean singleChoice, TransactionCallback callback) {
        String rIndex = String.valueOf(index);

        getPollResponsesReference(pollID).child("values").child(rIndex)
                .runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(MutableData mutableData) {
                        long value = 0;

                        if (mutableData.getValue() != null) {
                            value = (long) mutableData.getValue();
                        }

                        value++;
                        mutableData.setValue(value);

                        if (singleChoice) {
                            setUserSubmission(pollID, userID, index);
                        }

                        return Transaction.success(mutableData);
                    }

                    @Override
                    public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                        if (committed) {
                            callback.onSuccess();
                        } else {
                            callback.onError(databaseError.getMessage());
                        }
                    }
                });
    }

    @Override
    public void submitOpenFormResponse(String pollID, String message) {
        getPollResponsesReference(pollID).child("values").push().setValue(message);
    }

    @Override
    public void setUserSubmission(String pollID, String userID, int index) {
        getPollResponsesReference(pollID)
                .child("submission")
                .child(userID)
                .setValue(index);
    }

    @Override
    public void detectSubmission(String pollID, String userID, DataChangeCallback<Integer> callback) {
        getPollResponsesReference(pollID)
                .child("submission")
                .child(userID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int index = dataSnapshot.getValue(Integer.class);
                            callback.onChange(index);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}
