package com.sqlite.orderapp.models;

public class County {

    String CountyId;
    String CountyName;

    public County() {
    }

    public County(String countyName) {
        CountyName = countyName;
    }

    public County(String countyId, String countyName) {
        CountyId = countyId;
        CountyName = countyName;
    }

    public String getCountyId() {
        return CountyId;
    }

    public void setCountyId(String countyId) {
        CountyId = countyId;
    }

    public String getCountyName() {
        return CountyName;
    }

    public void setCountyName(String countyName) {
        CountyName = countyName;
    }
}
