package com.sqlite.orderapp.models;

public class Agent {

    String AgentId;
    String AgentName;
    String AgentIdNo;
    String AgentPhone;
    String AgentEmail;
    String CountyId;
    String SubcountyId;
    String WardId;
    String LocationId;
    String StationId;
    String UserTypeId;
    String ImageUrl;

    public Agent() {
    }

    public Agent(String agentId, String agentName, String agentIdNo, String agentPhone, String agentEmail, String countyId, String subcountyId, String wardId, String locationId, String stationId, String userTypeId, String imageUrl) {
        AgentId = agentId;
        AgentName = agentName;
        AgentIdNo = agentIdNo;
        AgentPhone = agentPhone;
        AgentEmail = agentEmail;
        CountyId = countyId;
        SubcountyId = subcountyId;
        WardId = wardId;
        LocationId = locationId;
        StationId = stationId;
        UserTypeId = userTypeId;
        ImageUrl = imageUrl;
    }

    public Agent(String agentName, String agentIdNo, String agentPhone, String agentEmail, String countyId, String subcountyId, String wardId, String locationId, String stationId, String userTypeId, String imageUrl) {
        AgentName = agentName;
        AgentIdNo = agentIdNo;
        AgentPhone = agentPhone;
        AgentEmail = agentEmail;
        CountyId = countyId;
        SubcountyId = subcountyId;
        WardId = wardId;
        LocationId = locationId;
        StationId = stationId;
        UserTypeId = userTypeId;
        ImageUrl = imageUrl;
    }

    public String getAgentId() {
        return AgentId;
    }

    public void setAgentId(String agentId) {
        AgentId = agentId;
    }

    public String getAgentName() {
        return AgentName;
    }

    public void setAgentName(String agentName) {
        AgentName = agentName;
    }

    public String getAgentIdNo() {
        return AgentIdNo;
    }

    public void setAgentIdNo(String agentIdNo) {
        AgentIdNo = agentIdNo;
    }

    public String getAgentPhone() {
        return AgentPhone;
    }

    public void setAgentPhone(String agentPhone) {
        AgentPhone = agentPhone;
    }

    public String getAgentEmail() {
        return AgentEmail;
    }

    public void setAgentEmail(String agentEmail) {
        AgentEmail = agentEmail;
    }

    public String getCountyId() {
        return CountyId;
    }

    public void setCountyId(String countyId) {
        CountyId = countyId;
    }

    public String getSubcountyId() {
        return SubcountyId;
    }

    public void setSubcountyId(String subcountyId) {
        SubcountyId = subcountyId;
    }

    public String getWardId() {
        return WardId;
    }

    public void setWardId(String wardId) {
        WardId = wardId;
    }

    public String getLocationId() {
        return LocationId;
    }

    public void setLocationId(String locationId) {
        LocationId = locationId;
    }

    public String getStationId() {
        return StationId;
    }

    public void setStationId(String stationId) {
        StationId = stationId;
    }

    public String getUserTypeId() {
        return UserTypeId;
    }

    public void setUserTypeId(String userTypeId) {
        UserTypeId = userTypeId;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
