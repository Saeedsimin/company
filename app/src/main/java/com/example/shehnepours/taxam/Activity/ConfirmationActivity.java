package com.example.shehnepours.taxam.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shehnepours.taxam.ApiService;
import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.UserSharedPrefManager;
import com.example.shehnepours.taxam.constants.Variables;
import com.example.shehnepours.taxam.datamodel.User;
import com.example.shehnepours.taxam.parents.CustomButton;
import com.example.shehnepours.taxam.parents.CustomEditText;
import com.example.shehnepours.taxam.parents.CustomTextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ConfirmationActivity extends AppCompatActivity {

    // Final Variables
    private static final String USER_PHONE_KEY = "phone_number";
    private static final String USER_EMAIL_KEY = "email_address";
    private static final String USER_PASS_KEY = "password_key";
    private static final String USER_VAL_KEY = "validation_key";
    private static final String TAG = "Confirmation";


    // Hidden Variables
//    private ApiService apiService;
    private User user;
    private UserSharedPrefManager sharedPrefManager;
    private String phoneNumber;
    private String emailAddress;
    private String passKey;
    private int isValidated;
    private int buttonMode = 0;
    long millis = 12000; // setting timer to 2 minutes
    private CountDownTimer countDownTimer;


    // Resources
    private ProgressDialog dialog;
    private CustomEditText confirmEditText;
    private CustomButton finalSignupButton;
    private CustomTextView confirmationTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_confirmation);



        // Defining different fonts
//        Typeface sanstypeface = Typeface.createFromAsset(getAssets(),"fonts/irsans.ttf");
//        Typeface baykanTypeface = Typeface.createFromAsset(getAssets(),"fonts/byekan.ttf");

        // Getting components in layout
        confirmEditText = (CustomEditText) findViewById(R.id.confirm_edittext);
        finalSignupButton = (CustomButton) findViewById(R.id.final_signup_button);
        confirmationTextView = (CustomTextView) findViewById(R.id.confirm_text);

/*
        confirmationTextView.setTypeface(baykanTypeface);
        finalSignupButton.setTypeface(baykanTypeface);
*/



        // Getting user info from sharedPreference
        user = new User();
        sharedPrefManager = new UserSharedPrefManager(ConfirmationActivity.this);
        user =sharedPrefManager.getUser();
        phoneNumber = user.getPhone_number();
        emailAddress = user.getEmail();
        passKey = user.getPassword();
        isValidated = user.getIsValidated();


//        Intent in = getIntent();
//        phoneNumber = in.getStringExtra(USER_PHONE_KEY);
//        emailAddress = in.getStringExtra(USER_EMAIL_KEY);
//        passKey = in.getStringExtra(USER_PASS_KEY);

        setCountDownTimer();
        // if user hit final signup button
        finalSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonMode == 0) {
                    dialog = ProgressDialog.show(ConfirmationActivity.this, "",
                            getString(R.string.validaiton_waiting), true);
                    onRecieveConfirmation();
                }
                else {
                    dialog = ProgressDialog.show(ConfirmationActivity.this, "",
                            getString(R.string.please_wait), true);
                    setCountDownTimer();
                    onSendConfirmation();
                }
            }
        });


    }

    public void setCountDownTimer() {
        countDownTimer = new CountDownTimer(30*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                buttonMode = 0;
                int totalSeconds = (int) millisUntilFinished/1000;
                int minutes = totalSeconds/60;
                int seconds = totalSeconds%60;
                finalSignupButton.setText("ثبت نام نهایی ("+ minutes + ":"+seconds+")");
            }

            @Override
            public void onFinish() {
                finalSignupButton.setText("ارسال مجدد رمز عبور");
                buttonMode = 1;
            }
        };
        countDownTimer.start();

    }

    public void onSendConfirmation()
    {
        ApiService apiService = new ApiService(ConfirmationActivity.this);

        // Start to send phone number to server for sending confirmation code to user
        JSONObject requestJsonObject = new JSONObject();
        try {
            requestJsonObject.put(USER_PHONE_KEY,phoneNumber);
            requestJsonObject.put(USER_EMAIL_KEY,emailAddress);
            requestJsonObject.put(USER_PASS_KEY,passKey);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        apiService.onResendForgetPassword(requestJsonObject, new ApiService.OnResendForgetPasswordComplete() {
            @Override
            public void onResendConfirmation(int success) {
                // User's information updated successfully
                if(success == 3) // number is valid and has not been signed up
                {
                    dialog.dismiss();
                    Toast.makeText(ConfirmationActivity.this, R.string.send_confirmation_success,Toast.LENGTH_SHORT).show();
                }
                else if(success == 2) // number is valid but has been signed up before
                {
                    dialog.dismiss();
                    Toast.makeText(ConfirmationActivity.this, R.string.signedup_before,Toast.LENGTH_LONG).show();
                    Intent in = new Intent(ConfirmationActivity.this,LoginActivity.class);
                    startActivity(in);
                }
                // number is valid but something is wrong with server
                else if(success == 1) {
                    dialog.dismiss();
                    Toast.makeText(ConfirmationActivity.this, R.string.server_failure,Toast.LENGTH_LONG).show();
                }
                // number is not valid
                else {
                    dialog.dismiss();
                    Toast.makeText(ConfirmationActivity.this, R.string.not_valid_number,Toast.LENGTH_LONG).show();
                }


            }
        });

    }


    public void onRecieveConfirmation()
    {

        // Creating ApiService for Sending Entered Validation Key
        ApiService apiService = new ApiService(ConfirmationActivity.this);
        JSONObject jsonObject = new JSONObject();
        try {
            Log.i(TAG, "onRecieveConfirmation: "+phoneNumber);
            jsonObject.put(Variables.USER_PHONE_KEY,phoneNumber);
            jsonObject.put(Variables.USER_PASS_KEY,passKey);
            jsonObject.put(Variables.USER_EMAIL_KEY,emailAddress);
            jsonObject.put(Variables.USER_VAL_KEY,confirmEditText.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        apiService.onRecieveConfirmation(jsonObject, new ApiService.OnRecieveConfirmationComplete() {
            @Override
            public void onRecieveConfirmation(int success) {
                // user entered the right validation code
                if (success == 3) {
                    dialog.dismiss();
                    Toast.makeText(ConfirmationActivity.this, R.string.validation_confirmed,Toast.LENGTH_SHORT).show();
                    // Save user to sharedPreferences
                    user.setIsValidated(1);
                    sharedPrefManager.saveUser(user);
                    Intent in = new Intent(ConfirmationActivity.this, UserProfileActivity.class);
                    startActivity(in);
                }
                else if (success == 2) {
                    dialog.dismiss();
                    Toast.makeText(ConfirmationActivity.this, R.string.validation_failed,Toast.LENGTH_SHORT).show();
                }
                else {
                    dialog.dismiss();
                    Toast.makeText(ConfirmationActivity.this, R.string.server_failure,Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




}
