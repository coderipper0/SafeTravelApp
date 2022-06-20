package com.safetravel.safetravel.models;

import java.util.ArrayList;
import java.util.Date;

public class Details {
    private final long id;
    private final String details;
    private final long userId;
    private final Date date;
    private final ArrayList<Integer> imageIds;

    public Details(String details, long userId, Date date, ArrayList<Integer> imageIds) {
        id = System.currentTimeMillis();
        this.details = details;
        this.userId = userId;
        this.date = date;
        this.imageIds = imageIds;
    }

    public long getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public long getUserId() {
        return userId;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<Integer> getImageIds() {
        return imageIds;
    }
}
