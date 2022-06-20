package com.safetravel.safetravel.models;

import java.util.ArrayList;

public class Forum {
    private final long id;
    private final ArrayList<Comment> comments;

    public Forum() {
        id = System.currentTimeMillis();
        comments = new ArrayList<>();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public long getId() {
        return id;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
