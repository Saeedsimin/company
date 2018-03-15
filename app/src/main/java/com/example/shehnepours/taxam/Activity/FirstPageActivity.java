package com.example.shehnepours.taxam.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.UserSharedPrefManager;
import com.example.shehnepours.taxam.datamodel.User;

public class FirstPageActivity extends AppCompatActivity {


    private Handler waitHandler = new Handler();
    private User user;
    private UserSharedPrefManager prefManager;
    private Intent intent;
    public static final String TAG = "Checking";
    public static final int TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_first_page);


        // Defining different fonts
        Typeface sanstypeface = Typeface.createFromAsset(getAssets(),"fonts/irsans.ttf");
        Typeface baykanTypeface = Typeface.createFromAsset(getAssets(),"fonts/byekan.ttf");

        // Getting user from shared preference
        prefManager = new UserSharedPrefManager(this);
        user = prefManager.getUser();



        // Checking if user exists
        if (user.getPassword().isEmpty())
        {
            Log.i(TAG, "onCreate: emptyEmail"+user.getPhone_number()+user.getPassword()+user.getIsValidated());
            // waiting a few seconds before signup page shows up
            waitHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // No user has logged in before, so user is directed to signup page
                    intent = new Intent(FirstPageActivity.this,SignupActivity.class);
                    startActivity(intent);
                }
            },TIME_OUT);
        }
        else
        {
            // if user has signed up, but not validated
            if(user.getIsValidated() == 0)
            {
                // User has to be notified by receiving a validation code
                Log.i(TAG, "onCreate: notValidatedEmail"+user.getPhone_number());
                waitHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        intent = new Intent(FirstPageActivity.this,ConfirmationActivity.class);
                        startActivity(intent);
                    }
                },TIME_OUT);

            }

            // if user has signed up and validated
            else
            {
                Log.i(TAG, "onCreate: validatedEmail"+user.getPhone_number());

                waitHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        intent = new Intent(FirstPageActivity.this,UserProfileActivity.class);
                        startActivity(intent);
                    }
                },TIME_OUT);

            }
        }











    }
}
