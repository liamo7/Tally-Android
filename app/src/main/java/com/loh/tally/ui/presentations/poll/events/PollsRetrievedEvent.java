package com.loh.tally.ui.presentations.poll.events;

import com.loh.tally.domain.event.BaseEvent;
import com.loh.tally.domain.model.Poll;

import java.util.List;

/**
 * File: PollsRetrievedEvent.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public final class PollsRetrievedEvent implements BaseEvent {

    private final List<Poll> polls;

    public PollsRetrievedEvent(List<Poll> polls) {
        this.polls = polls;
    }

    public List<Poll> getPolls() {
        return polls;
    }
}
