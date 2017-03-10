package com.loh.tally.domain.model;

/**
 * File: Module.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public class Module {

    private String id;
    private String name;
    private String code;
    private long dateCreated;
    private long dateLastModified;
    private String creator;

    public Module() {
    }

    public Module(String id, String name, String code, long dateCreated, long dateLastModified, String creator) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.dateCreated = dateCreated;
        this.dateLastModified = dateLastModified;
        this.creator = creator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public long getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(long dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
