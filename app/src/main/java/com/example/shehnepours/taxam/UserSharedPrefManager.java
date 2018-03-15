package com.example.shehnepours.taxam;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.shehnepours.taxam.constants.Variables;
import com.example.shehnepours.taxam.datamodel.User;

/**
 * Created by shehnepour.s on 10/18/2017.
 */

public class UserSharedPrefManager {
    // Creating XML file with user_shared_pref name
    private static final String USER_SHARED_PREF_NAME = "user_shared_pref";

    // Creating shared preference object
    private SharedPreferences sharedPreferences;

    // Defining keys for different field of data model
//    private static final String KEY_EMAIL = "email";
//    private static final String KEY_phone = "phone_number";
//    private static final String KEY_PASSWORD = "password";

    // If there is a shared preference it will be overwrited, otherwise it will be created
    public UserSharedPrefManager(Context context)
    {
        // Determining preference privilage (Mode private indicates that no one can access file except application)
        sharedPreferences = context.getSharedPreferences(USER_SHARED_PREF_NAME,Context.MODE_PRIVATE);
    }



    // save user to shared preference
    public void saveUser(User user){
        if(user!=null)
        {
            // Defining Editor to put fields value in shared preference
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Variables.USER_PHONE_KEY,user.getPhone_number());
            editor.putString(Variables.USER_EMAIL_KEY,user.getEmail());
            editor.putString(Variables.USER_PASS_KEY,user.getPassword());
            editor.putInt(Variables.USER_VAL_KEY,user.getIsValidated());
            // apply() is more optimal than commit(), since it will be executed in background
            editor.apply();
        }
    }

    public User getUser()
    {
        User user = new User();
        user.setEmail(sharedPreferences.getString(Variables.USER_EMAIL_KEY,""));
        user.setPassword(sharedPreferences.getString(Variables.USER_PASS_KEY,""));
        user.setPhone_number(sharedPreferences.getString(Variables.USER_PHONE_KEY,""));
        user.setIsValidated(sharedPreferences.getInt(Variables.USER_VAL_KEY,0));
        return user;
    }

}
