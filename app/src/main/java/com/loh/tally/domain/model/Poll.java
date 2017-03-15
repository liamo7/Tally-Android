package com.loh.tally.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.List;

/**
 * File: Poll.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public class Poll implements Parcelable {

    private String id;
    private String question;
    private String questionType;
    private String chartType;
    private boolean profanityFilter;
    private List<String> choices;
    private boolean singleChoice;
    private HashMap<String, Boolean> submission;
    private String presID;

    public Poll() {
    }

    public Poll(String id, String question, String questionType, String chartType, boolean profanityFilter, List<String> choices, boolean singleChoice) {
        this.id = id;
        this.question = question;
        this.questionType = questionType;
        this.chartType = chartType;
        this.profanityFilter = profanityFilter;
        this.choices = choices;
        this.singleChoice = singleChoice;
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

    public void setSingleChoice(boolean singleChoice) {
        this.singleChoice = singleChoice;
    }

    public boolean isSingleChoice() {
        return singleChoice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.question);
        dest.writeString(this.questionType);
        dest.writeString(this.chartType);
        dest.writeByte(this.profanityFilter ? (byte) 1 : (byte) 0);
        dest.writeStringList(this.choices);
        dest.writeSerializable(this.submission);
        dest.writeString(this.presID);
        dest.writeByte(this.singleChoice ? (byte) 1 : (byte) 0);
    }

    protected Poll(Parcel in) {
        this.id = in.readString();
        this.question = in.readString();
        this.questionType = in.readString();
        this.chartType = in.readString();
        this.profanityFilter = in.readByte() != 0;
        this.choices = in.createStringArrayList();
        this.submission = (HashMap<String, Boolean>) in.readSerializable();
        this.presID = in.readString();
        this.singleChoice = in.readByte() != 0;
    }

    public static final Creator<Poll> CREATOR = new Creator<Poll>() {
        @Override
        public Poll createFromParcel(Parcel source) {
            return new Poll(source);
        }

        @Override
        public Poll[] newArray(int size) {
            return new Poll[size];
        }
    };
}