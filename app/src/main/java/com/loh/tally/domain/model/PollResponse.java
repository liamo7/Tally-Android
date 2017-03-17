package com.loh.tally.domain.model;

import java.util.List;

/**
 * File: PollResponse.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 */
public class PollResponse {

    private List<Integer> responses;
    private int position;

    public PollResponse() {

    }

    public PollResponse(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Integer> getResponses() {
        return responses;
    }

    public void setResponses(List<Integer> responses) {
        this.responses = responses;
    }
}