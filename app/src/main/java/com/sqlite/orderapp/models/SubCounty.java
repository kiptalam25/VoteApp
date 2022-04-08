package com.sqlite.orderapp.models;

public class SubCounty {
    String SubCountyId;
    String CountyId;
    String SubCountyName;

    public SubCounty() {
    }

    public SubCounty(String countyId, String SubCountyName) {
        this.SubCountyName=SubCountyName;
        this.CountyId=countyId;
    }

    public String getCountyId() {
        return CountyId;
    }

    public void setCountyId(String countyId) {
        CountyId = countyId;
    }



    public SubCounty(String subCountyName) {
        SubCountyName = subCountyName;
    }

    public String getSubCountyId() {
        return SubCountyId;
    }

    public void setSubCountyId(String subCountyId) {
        SubCountyId = subCountyId;
    }

    public String getSubCountyName() {
        return SubCountyName;
    }

    public void setSubCountyName(String subCountyName) {
        SubCountyName = subCountyName;
    }
}
