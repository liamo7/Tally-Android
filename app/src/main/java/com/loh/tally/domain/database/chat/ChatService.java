package com.loh.tally.domain.database.chat;

import android.support.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.loh.tally.domain.model.ChatMessage;

/**
 * File: ChatService.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 */
public interface ChatService {

    DatabaseReference getRootRef();

    DatabaseReference getModuleChat(String moduleID);

    void sendMessage(@NonNull String moduleID, ChatMessage message);
}
