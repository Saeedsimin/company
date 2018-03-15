package com.example.shehnepours.taxam.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shehnepours.taxam.ApiService;
import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.UserSharedPrefManager;
import com.example.shehnepours.taxam.datamodel.User;
import com.example.shehnepours.taxam.parents.CustomButton;
import com.example.shehnepours.taxam.parents.CustomEditText;
import com.example.shehnepours.taxam.parents.CustomTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class SignupActivity extends AppCompatActivity {


    private static final String USER_PHONE_KEY = "phone_number";
    private static final String USER_EMAIL_KEY = "email_address";
    private static final String USER_PASS_KEY = "password_key";


    // Hidden Variables
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private ProgressDialog dialog;
    private User user;
    private UserSharedPrefManager sharedPrefManager;

    // Getting EditTexts( phone_number, email, password, retype password, ...)
    private CustomEditText phoneEditText;
    private CustomEditText passEditText;
    private CustomEditText repassEditText;
    private CustomButton signupButton;
    private CustomTextView signupText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_signup);

        Locale locale = new Locale("fa-IR");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);

        // Defining different fonts
//        Typeface sanstypeface = Typeface.createFromAsset(getAssets(),"fonts/irsans.ttf");
//        Typeface baykanTypeface = Typeface.createFromAsset(getAssets(),"fonts/byekan.ttf");

        phoneEditText = (CustomEditText)findViewById(R.id.phone_signup_edittext);
        passEditText = (CustomEditText)findViewById(R.id.password_signup_edittext);
        repassEditText = (CustomEditText)findViewById(R.id.repassword_signup_edittext);
        signupButton = (CustomButton) findViewById(R.id.signup_button);
        signupText = (CustomTextView) findViewById(R.id.signup_text);
        // Setting font type for edit texts and signup button

        // Ensuring first digit of phone number is equal to 0
        phoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String sString = s.toString();
                char[] sArray = sString.toCharArray();

                // Check if it is the first character
                if (start == 0);
                {
                    // Checking if user has deleted the phone number
                    if(sArray.length!=0)
                    {
                        // Checking if user is entering 0'th character for first character
                        if (sArray[start] != '0')
                        {
                            Toast.makeText(SignupActivity.this, R.string.phone_firstdigit_warning,Toast.LENGTH_LONG).show();

                            // Do not let user to enter another character except 0
                            phoneEditText.setText("");
                        }
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        // Ensuring length of password
        passEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Checking if password length satisfy the condition
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        repassEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        // Ensuring both password and retyped password are same
        repassEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
           }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignupErrorChecker();
            }
        });

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }


    // Checking User input errors
    public void onSignupErrorChecker()
    {
        if (phoneEditText.getText().toString().length()==0 ||
            passEditText.getText().toString().length()==0  ||
            repassEditText.getText().toString().length() ==0    )
        {
            Toast.makeText(SignupActivity.this, R.string.fill_all_fields_warning,Toast.LENGTH_SHORT).show();
        }
        else {
            String passwordText = passEditText.getText().toString();
            if(passwordText.length()<6 || passwordText.length()>16)
            {
                Toast.makeText(SignupActivity.this, R.string.password_length_warning,Toast.LENGTH_SHORT).show();
            }
            else {
                if (!(passEditText.getText().toString().equals(repassEditText.getText().toString()))) {
                    Toast.makeText(SignupActivity.this, R.string.password_notsame_warning, Toast.LENGTH_SHORT).show();
                } else {
                    dialog = ProgressDialog.show(SignupActivity.this, "",
                            getString(R.string.please_wait), true);
                    onSendConfirmation();
                }
            }
        }





    }


    // Checking mobile number for sending verification code
    public void onSendConfirmation()
    {
        ApiService apiService = new ApiService(SignupActivity.this);

        // Start to send phone number to server for sending confirmation code to user
        JSONObject requestJsonObject = new JSONObject();
        try {
            requestJsonObject.put(USER_PHONE_KEY,phoneEditText.getText().toString());
            requestJsonObject.put(USER_PASS_KEY,passEditText.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        apiService.onSendConfirmation(requestJsonObject, new ApiService.OnSendConfirmationComplete() {
            @Override
            public void onSendConfirmation(int success) {
                // User has been added to table successfully
                if(success == 3) // number is valid and has not been signed up
                {
                    dialog.dismiss();
                    saveToSharedPreferences();
                    Intent in = new Intent(SignupActivity.this,ConfirmationActivity.class);
                    in.putExtra(USER_PHONE_KEY,phoneEditText.getText().toString());
                    in.putExtra(USER_PASS_KEY,passEditText.getText().toString());
                    Toast.makeText(SignupActivity.this, R.string.send_confirmation_success,Toast.LENGTH_SHORT).show();
                    startActivity(in);
                }
                else if(success == 2) // number is valid but has been signed up before
                {
                    dialog.dismiss();
                    Toast.makeText(SignupActivity.this, R.string.signedup_before,Toast.LENGTH_LONG).show();
                    Intent in = new Intent(SignupActivity.this,LoginActivity.class);
                    startActivity(in);
                }
                // number is valid but something is wrong with server
                else if(success == 1) {
                    dialog.dismiss();
                    Toast.makeText(SignupActivity.this, R.string.server_failure,Toast.LENGTH_LONG).show();
                }
                // number is not valid
                else {
                    dialog.dismiss();
                    Toast.makeText(SignupActivity.this, R.string.not_valid_number,Toast.LENGTH_LONG).show();
                }


            }
        });

    }
    public void saveToSharedPreferences()
    {
        user = new User();
        user.setPhone_number(phoneEditText.getText().toString());
        user.setPassword(passEditText.getText().toString());
        user.setIsValidated(1);
        sharedPrefManager = new UserSharedPrefManager(SignupActivity.this);
        sharedPrefManager.saveUser(user);
    }


}

