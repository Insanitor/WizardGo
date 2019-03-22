package com.Troldmandgo.wizardgo.Helpers;

public class LocationDataSet {
    long enjoyerId;
    float longitude;
    float latitude;

    public LocationDataSet(long enjoyerId, float longitude, float latitude) {
        this.enjoyerId = enjoyerId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public long getEnjoyerId() {
        return enjoyerId;
    }

    public void setEnjoyerId(long enjoyerId) {
        this.enjoyerId = enjoyerId;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
