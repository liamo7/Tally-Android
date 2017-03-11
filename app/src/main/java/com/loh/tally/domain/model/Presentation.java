package com.loh.tally.domain.model;

/**
 * File: Presentation.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public class Presentation {

    private String id;
    private String name;
    private String roomID;
    private int currentPoll;
    private int numPolls;
    private long dateCreated;
    private long dateLastModified;

    public Presentation() {
    }

    public Presentation(String name, String roomID, int currentPoll, int numPolls, long dateCreated, long dateLastModified) {
        this.name = name;
        this.roomID = roomID;
        this.currentPoll = currentPoll;
        this.numPolls = numPolls;
        this.dateCreated = dateCreated;
        this.dateLastModified = dateLastModified;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRoomID() {
        return roomID;
    }

    public int getCurrentPoll() {
        return currentPoll;
    }

    public int getNumPolls() {
        return numPolls;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public long getDateLastModified() {
        return dateLastModified;
    }
}
