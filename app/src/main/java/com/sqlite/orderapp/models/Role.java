package com.sqlite.orderapp.models;

public class Role {
    String rolename;

    public Role(String rolename) {
        this.rolename = rolename;
    }

    public Role() {

    }


    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
