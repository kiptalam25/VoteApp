package com.sqlite.orderapp.models;

public class Ward {
    String wardId;
    String wardName;
    String subcountyId;

    public Ward() {
    }

    public Ward(String wardId, String wardName, String subcountyId) {
        this.wardId = wardId;
        this.wardName = wardName;
        this.subcountyId = subcountyId;
    }

    public Ward(String wardName, String subcountyId) {
        this.wardName = wardName;
        this.subcountyId = subcountyId;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getSubcountyId() {
        return subcountyId;
    }

    public void setSubcountyId(String subcountyId) {
        this.subcountyId = subcountyId;
    }
}
