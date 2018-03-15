package com.example.shehnepours.taxam.SharedPreferenceManager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.shehnepours.taxam.constants.Variables;
import com.example.shehnepours.taxam.datamodel.TaxPicture;

/**
 * Created by shehnepour.s on 1/10/2018.
 */

public class TaxPictureSharedPreference {
    private static final String TAX_PICTURE_SHARED_PREF_NAME = "tax_picture_shared_pref";

    private SharedPreferences sharedPreferences;

    public TaxPictureSharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(TAX_PICTURE_SHARED_PREF_NAME,Context.MODE_PRIVATE);
    }


    public void saveTaxPicture(TaxPicture taxPicture) {
        if (taxPicture!= null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Variables.TAX_PICTURE_ITEM,taxPicture.getTaxPictureItem());
            editor.putString(Variables.DATE,taxPicture.getDate());
            editor.putString(Variables.YEAR_KEY,taxPicture.getSal());
            editor.putString(Variables.TAX_TYPE,taxPicture.getTax_type());
            editor.putInt(Variables.SHENASE_MELI,taxPicture.getShenase_melli());
            editor.apply();
        }
    }

    public TaxPicture getTaxPicture() {
        TaxPicture taxPicture = new TaxPicture();
        taxPicture.setTaxPictureItem(sharedPreferences.getString(Variables.TAX_PICTURE_ITEM,""));
        taxPicture.setDate(sharedPreferences.getString(Variables.DATE,""));
        taxPicture.setSal(sharedPreferences.getString(Variables.YEAR_KEY,""));
        taxPicture.setTax_type(sharedPreferences.getString(Variables.TAX_TYPE,""));
        taxPicture.setShenase_melli(sharedPreferences.getInt(Variables.SHENASE_MELI,0));
        return taxPicture;
    }

}
