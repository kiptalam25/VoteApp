package com.sqlite.orderapp.services.model;

import java.io.Serializable;

public class Orders implements Serializable {
    private int quantity;
    private String name;
    private String orderfrom;
    private String orderoto;
    private String price;
    private String imageurl;


    public Orders(int quantity, String name, String orderfrom, String orderoto,String price, String imageUrl) {
        this.quantity = quantity;
        this.name = name;
        this.orderfrom = orderfrom;
        this.orderoto = orderoto;
        this.price=price;
        this.imageurl=imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Orders() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderfrom() {
        return orderfrom;
    }

    public void setOrderfrom(String orderfrom) {
        this.orderfrom = orderfrom;
    }

    public String getOrderoto() {
        return orderoto;
    }

    public void setOrderoto(String orderoto) {
        this.orderoto = orderoto;
    }


}
