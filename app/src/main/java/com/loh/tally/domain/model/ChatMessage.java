package com.loh.tally.domain.model;

/**
 * File: ChatMessage.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public class ChatMessage {

    private long date;
    private String message;

    public ChatMessage() {
    }

    public ChatMessage(long date, String message) {
        this.date = date;
        this.message = message;
    }

    public long getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
}
