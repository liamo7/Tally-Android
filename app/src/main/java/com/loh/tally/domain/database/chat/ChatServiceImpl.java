package com.loh.tally.domain.database.chat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * File: ChatServiceImpl.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public class ChatServiceImpl implements ChatService {

    private static final String REF_CHAT = "chats";

    private final FirebaseDatabase firebaseDatabase;
    private final DatabaseReference chatRef;

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
}
