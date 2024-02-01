package com.salinas.salinasdovouga.Model;

import com.salinas.salinasdovouga.Users.UserType;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String name;
    private String citizenCardNumber;
    private String fiscalNumber;
    private String phone;
    private String address;
    private String locality;
    private UserType userType;

    public User(String username, String password, String name, String citizenCardNumber, String fiscalNumber,
                String phone, String address, String locality, UserType userType) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.citizenCardNumber = citizenCardNumber;
        this.fiscalNumber = fiscalNumber;
        this.phone = phone;
        this.address = address;
        this.locality = locality;
        this.userType = userType;
    }

    // Add getters for the attributes

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getCitizenCardNumber() {
        return citizenCardNumber;
    }

    public String getFiscalNumber() {
        return fiscalNumber;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getLocality() {
        return locality;
    }

    public UserType getUserType() {
        return userType;
    }
}
