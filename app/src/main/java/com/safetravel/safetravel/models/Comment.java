package com.safetravel.safetravel.models;

import java.util.Date;

public class Comment {
    private final long id;
    private final String text;
    private final long userId;
    private final Date date;

    public Comment(String text, long userId, Date date) {
        id = System.currentTimeMillis();
        this.text = text;
        this.userId = userId;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public long getUserId() {
        return userId;
    }

    public Date getDate() {
        return date;
    }
}
