package com.loh.tally.domain.database.chat;

import com.google.firebase.database.DatabaseReference;

/**
 * File: ChatService.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 */
public interface ChatService {

    DatabaseReference getRootRef();

    DatabaseReference getModuleChat(String moduleID);
}
