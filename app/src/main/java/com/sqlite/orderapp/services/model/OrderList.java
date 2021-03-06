package com.sqlite.orderapp.services.model;

public class OrderList {

    private String id;
    private String timestamp;

    public OrderList(String id, String timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public OrderList() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
