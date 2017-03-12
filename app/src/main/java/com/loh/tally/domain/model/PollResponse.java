package com.loh.tally.domain.model;

import java.util.List;

/**
 * File: PollResponse.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
public class PollResponse {

    private List<Integer> responses;
    private List<String> formResposne;

    public PollResponse() {

    }

    public List<Integer> getResponses() {
        return responses;
    }

    public List<String> getFormResposne() {
        return formResposne;
    }
}