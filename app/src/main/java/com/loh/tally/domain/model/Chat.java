package com.loh.tally.domain.model;

import java.util.List;

/**
 * File: Chat.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 */
public class Chat {

    private List<ChatMessage> messages;

    public Chat() {
    }

    public Chat(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }
}
