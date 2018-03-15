package com.example.shehnepours.taxam.utils;

/**
 * Created by shehnepour.s on 3/13/2018.
 */

public class UserAccount {


    private String accountName;
    private String businessName;
    private int isSelected;

    public UserAccount(String accountName, String businessName, int isSelected){
        this.accountName = accountName;
        this.businessName = businessName;
        this.isSelected = isSelected;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }
}
