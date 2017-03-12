package com.loh.tally.domain.model;

import java.util.HashMap;
import java.util.List;

/**
 * File: Poll.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public class Poll {

    private String id;
    private String question;
    private String questionType;
    private String chartType;
    private boolean profanityFilter;
    private List<String> choices;
    private HashMap<String, Boolean> submission;
    private String presID;

    public Poll() {
    }

    public Poll(String id, String question, String questionType, String chartType, boolean profanityFilter, List<String> choices) {
        this.id = id;
        this.question = question;
        this.questionType = questionType;
        this.chartType = chartType;
        this.profanityFilter = profanityFilter;
        this.choices = choices;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getChartType() {
        return chartType;
    }

    public boolean isProfanityFilter() {
        return profanityFilter;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setID(String id) {
        this.id = id;
    }

    public HashMap<String, Boolean> getSubmission() {
        return submission;
    }

    public String getPresID() {
        return presID;
    }

    public void setPresID(String presID) {
        this.presID = presID;
    }
}