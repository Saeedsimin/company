package com.example.shehnepours.taxam.datamodel;

import android.content.Context;

/**
 * Created by shehnepour.s on 1/10/2018.
 */

public class TaxPicture {
    private String date = "";
    private String tax_type = "";
    private String sal = "";
    private int shenase_melli = 0;
    private String taxPictureItem = "";

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTax_type() {
        return tax_type;
    }

    public void setTax_type(String tax_type) {
        this.tax_type = tax_type;
    }

    public String getSal() {
        return sal;
    }

    public void setSal(String sal) {
        this.sal = sal;
    }

    public int getShenase_melli() {
        return shenase_melli;
    }

    public void setShenase_melli(int shenase_melli) {
        this.shenase_melli = shenase_melli;
    }

    public String getTaxPictureItem() {
        return taxPictureItem;
    }

    public void setTaxPictureItem(String taxPictureItem) {
        this.taxPictureItem = taxPictureItem;
    }
}
