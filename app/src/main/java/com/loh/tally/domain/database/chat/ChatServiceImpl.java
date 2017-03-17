package com.loh.tally.domain.database.chat;

import android.support.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.loh.tally.domain.model.ChatMessage;
import com.loh.tally.ui.base.dagger.scope.ApplicationScope;

import javax.inject.Inject;

/**
 * File: ChatServiceImpl.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 */
@ApplicationScope
public class ChatServiceImpl implements ChatService {

    private static final String REF_CHAT = "chats";

    private final FirebaseDatabase firebaseDatabase;
    private final DatabaseReference chatRef;

    @Inject
    public ChatServiceImpl(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
        this.chatRef = firebaseDatabase.getReference(REF_CHAT);
    }

    @Override
    public DatabaseReference getRootRef() {
        return this.chatRef;
    }

    @Override
    public DatabaseReference getModuleChat(String moduleID) {
        return this.chatRef.child(moduleID);
    }

    @Override
    public void sendMessage(@NonNull String moduleID, ChatMessage message) {
        getModuleChat(moduleID).push().setValue(message);
    }
}
