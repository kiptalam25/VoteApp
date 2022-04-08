

package com.sqlite.orderapp.models;

public class VotingStation {
    String StationId;
    String StationName;
    String LocationId;

    public VotingStation() {
    }

    public VotingStation(String stationId, String stationName, String locationId) {
        StationId = stationId;
        StationName = stationName;
        LocationId = locationId;
    }

    public VotingStation(String stationName, String locationId) {
        StationName = stationName;
        LocationId = locationId;
    }

    public String getStationId() {
        return StationId;
    }

    public void setStationId(String stationId) {
        StationId = stationId;
    }

    public String getStationName() {
        return StationName;
    }

    public void setStationName(String stationName) {
        StationName = stationName;
    }

    public String getLocationId() {
        return LocationId;
    }

    public void setLocationId(String locationId) {
        LocationId = locationId;
    }
}
