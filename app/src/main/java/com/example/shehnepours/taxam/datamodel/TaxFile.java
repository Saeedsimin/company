package com.example.shehnepours.taxam.datamodel;

/**
 * Created by shehnepour.s on 1/10/2018.
 */

public class TaxFile {

    private String sal = "";
    private int maliat_ebrazi = 0;
    private int maliat_tashkhisi = 0;
    private String letter_type = "";
    private String marhale = "";
    private int agreement_amount = 0;
    private int shenase_melli = 0;
    private int tax = 0;
    private String tax_type = "";
    private int avarez = 0;
    private int jarime = 0;
    private String tarikh_eblagh = "";


    public void setSal(String sal) {
        this.sal = sal;
    }

    public int getMaliat_ebrazi() {
        return maliat_ebrazi;
    }

    public void setMaliat_ebrazi(int maliat_ebrazi) {
        this.maliat_ebrazi = maliat_ebrazi;
    }

    public String getSal() {
        return sal;
    }

    public int getMaliat_tashkhisi() {
        return maliat_tashkhisi;
    }

    public void setMaliat_tashkhisi(int maliat_tashkhisi) {
        this.maliat_tashkhisi = maliat_tashkhisi;
    }

    public String getLetter_type() {
        return letter_type;
    }

    public void setLetter_type(String letter_type) {
        this.letter_type = letter_type;
    }

    public String getMarhale() {
        return marhale;
    }

    public void setMarhale(String marhale) {
        this.marhale = marhale;
    }

    public int getAgreement_amount() {
        return agreement_amount;
    }

    public void setAgreement_amount(int agreement_amount) {
        this.agreement_amount = agreement_amount;
    }

    public int getShenase_melli() {
        return shenase_melli;
    }

    public void setShenase_melli(int shenase_melli) {
        this.shenase_melli = shenase_melli;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public String getTax_type() {
        return tax_type;
    }

    public void setTax_type(String tax_type) {
        this.tax_type = tax_type;
    }

    public int getAvarez() {
        return avarez;
    }

    public void setAvarez(int avarez) {
        this.avarez = avarez;
    }

    public int getJarime() {
        return jarime;
    }

    public void setJarime(int jarime) {
        this.jarime = jarime;
    }

    public String getTarikh_eblagh() {
        return tarikh_eblagh;
    }

    public void setTarikh_eblagh(String tarikh_eblagh) {
        this.tarikh_eblagh = tarikh_eblagh;
    }
}
