package com.safetravel.safetravel.models;

public class Sector {
    private final long id;
    private final String name;
    private final long forumId;
    private final long detailsId;
    private final double latitude;
    private final double longitude;

    public Sector(String name, long forumId, long detailsId, double latitude, double longitude) {
        id = System.currentTimeMillis();
        this.name = name;
        this.forumId = forumId;
        this.detailsId = detailsId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getForumId() {
        return forumId;
    }

    public long getDetailsId() {
        return detailsId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
