package com.sqlite.orderapp.services.model;

public class Users {

    public Users(String id, String username, String emailId, String timestamp, String imageUrl, String bio, String status, Double longitude, Double latitude,String designation) {
        this.id = id;
        this.username = username;
        this.emailId = emailId;
        this.timestamp = timestamp;
        this.imageUrl = imageUrl;
        this.bio = bio;
        this.status = status;
        this.longitude = longitude;
        this.latitude = latitude;
        this.designation=designation;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    private String id;
    private String username;
    private String emailId;
    private String timestamp;
    private String imageUrl;
    private String bio;
    private String status;
    private Double longitude;
    private Double latitude;
    private String designation;



    public Users() {

    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


}
