package com.example.shehnepours.taxam.SharedPreferenceManager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.shehnepours.taxam.constants.Variables;
import com.example.shehnepours.taxam.datamodel.TaxFile;
import com.example.shehnepours.taxam.datamodel.User;

/**
 * Created by shehnepour.s on 1/10/2018.
 */

public class TaxFileSharedPrefManager {

    private static final String TAX_FILE_SHARED_PREF_NAME = "tax_file_shared_pref";

    private SharedPreferences sharedPreferences;

    public TaxFileSharedPrefManager(Context context)
    {
        sharedPreferences = context.getSharedPreferences(TAX_FILE_SHARED_PREF_NAME,Context.MODE_PRIVATE);
    }



    // save user to shared preference
    public void saveTaxFile(TaxFile taxFile){
        if(taxFile!=null)
        {
            // Defining Editor to put fields value in shared preference
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Variables.TARIKH_EBLAGH,taxFile.getTarikh_eblagh());
            editor.putString(Variables.YEAR_KEY,taxFile.getSal());
            editor.putInt(Variables.MALIAT_EBRAZI,taxFile.getMaliat_ebrazi());
            editor.putInt(Variables.MALIAT_TASHKHISI,taxFile.getMaliat_tashkhisi());
            editor.putString(Variables.LETTER_TYPE,taxFile.getLetter_type());
            editor.putString(Variables.MARHALE,taxFile.getMarhale());
            editor.putInt(Variables.AGREEMENT_AMOUNT,taxFile.getAgreement_amount());
            editor.putInt(Variables.SHENASE_MELI,taxFile.getShenase_melli());
            editor.putInt(Variables.TAX,taxFile.getTax());
            editor.putString(Variables.TAX_TYPE,taxFile.getTax_type());
            editor.putInt(Variables.AVAREZ,taxFile.getAvarez());
            editor.putInt(Variables.JARIME,taxFile.getJarime());
            // apply() is more optimal than commit(), since it will be executed in background
            editor.apply();
        }
    }

    public TaxFile getTaxFile()
    {
        TaxFile taxFile = new TaxFile();
        taxFile.setTarikh_eblagh(sharedPreferences.getString(Variables.TARIKH_EBLAGH,""));
        taxFile.setSal(sharedPreferences.getString(Variables.YEAR_KEY,""));
        taxFile.setMaliat_ebrazi(sharedPreferences.getInt(Variables.MALIAT_EBRAZI,0));
        taxFile.setMaliat_tashkhisi(sharedPreferences.getInt(Variables.MALIAT_TASHKHISI,0));
        taxFile.setLetter_type(sharedPreferences.getString(Variables.LETTER_TYPE,""));
        taxFile.setMarhale(sharedPreferences.getString(Variables.MARHALE,""));
        taxFile.setAgreement_amount(sharedPreferences.getInt(Variables.AGREEMENT_AMOUNT,0));
        taxFile.setShenase_melli(sharedPreferences.getInt(Variables.SHENASE_MELI,0));
        taxFile.setTax(sharedPreferences.getInt(Variables.TAX,0));
        taxFile.setTax_type(sharedPreferences.getString(Variables.TAX_TYPE,""));
        taxFile.setAvarez(sharedPreferences.getInt(Variables.AVAREZ,0));
        taxFile.setJarime(sharedPreferences.getInt(Variables.JARIME,0));
        return taxFile;
    }

}
