package com.loh.tally.domain.database.presentation;

import com.google.firebase.database.DatabaseReference;

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
}
