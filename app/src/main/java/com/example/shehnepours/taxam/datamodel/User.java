package com.example.shehnepours.taxam.datamodel;

/**
 * Created by shehnepour.s on 10/18/2017.
 */

public class User {

    private String phone_number = "";
    private String email = "";
    private String password = "";
    private int isValidated = 0;

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsValidated() {
        return isValidated;
    }

    public void setIsValidated(int isValidated) {
        this.isValidated = isValidated;
    }

}
