package com.sqlite.orderapp.models;

public class Location {
    String LocationId;
    String LocationName;
    String WardId;

    public Location( String locationName, String wardId) {
        LocationName = locationName;
        WardId = wardId;
    }

    public Location(String locationId, String locationName, String wardId) {
        LocationId = locationId;
        LocationName = locationName;
        WardId = wardId;
    }

    public Location() {
    }

    public String getLocationId() {
        return LocationId;
    }

    public void setLocationId(String locationId) {
        LocationId = locationId;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
    }

    public String getWardId() {
        return WardId;
    }

    public void setWardId(String wardId) {
        WardId = wardId;
    }
}
