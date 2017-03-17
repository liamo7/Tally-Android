package com.loh.tally.domain.database.presentation;

import com.google.firebase.database.DatabaseReference;
import com.loh.tally.ui.base.DataChangeCallback;
import com.loh.tally.ui.base.TransactionCallback;

/**
 * File: PresentationService.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 */
public interface PresentationService {

    DatabaseReference getPresentationReference(String presentationID);

    DatabaseReference getPresentationModuleReference(String moduleID);

    DatabaseReference getPollsReference(String presentationID);

    DatabaseReference getPollResponsesReference(String pollID);

    void submitMultipleChoiceResponse(String pollID, String userID, int index, boolean singleChoice, TransactionCallback callback);

    void submitOpenFormResponse(String pollID, String message);

    void setUserSubmission(String pollID, String userID, int index);

    void detectSubmission(String pollID, String userID, DataChangeCallback<Integer> callback);
}
