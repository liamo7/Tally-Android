package com.loh.tally.ui.presentations.poll.fragment;

import com.loh.tally.domain.event.BaseEvent;

/**
 * File: ChatFabEvent.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
public final class ChatFabEvent implements BaseEvent {

    private final boolean show;

    public ChatFabEvent(boolean show) {
        this.show = show;
    }

    public boolean isShow() {
        return show;
    }
}
