package com.example.shehnepours.taxam.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shehnepours.taxam.ApiService;
import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.constants.Variables;
import com.example.shehnepours.taxam.parents.CustomButton;
import com.example.shehnepours.taxam.parents.CustomEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgetPasswordActivity extends AppCompatActivity {

    // Getting layout resources
    private CustomEditText phoneEditText;
    private CustomButton sendPasswordButton;
    private ProgressDialog dialog;

    // Hidden variables and objects
    private ApiService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_forget_password);

        // Storing each resource in corresponding element
        phoneEditText = (CustomEditText) findViewById(R.id.phone_forgetpage_edittext);
        sendPasswordButton = (CustomButton) findViewById(R.id.sendpassword_forgetpage_button);

        // Defining different fonts
//        Typeface sanstypeface = Typeface.createFromAsset(getAssets(),"fonts/irsans.ttf");
//        Typeface baykanTypeface = Typeface.createFromAsset(getAssets(),"fonts/byekan.ttf");


        // Setting font for button
//        sendPasswordButton.setTypeface(baykanTypeface);



        // Set action on button

        sendPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSendPasswordErrorChecker();
            }
        });






    }

    public void onSendPasswordErrorChecker() {

        // check if user has entered the phone nubmer
        if (phoneEditText.getText().toString().length()==0) {
            Toast.makeText(ForgetPasswordActivity.this, R.string.enter_phone_number,Toast.LENGTH_SHORT);
        }
        else {
            dialog = ProgressDialog.show(ForgetPasswordActivity.this,"",getString(R.string.sending_password));

            // start to send new password to user using api
            apiService = new ApiService(ForgetPasswordActivity.this);
            JSONObject requestJsonObject = new JSONObject();
            try {
                requestJsonObject.put(Variables.USER_PHONE_KEY,phoneEditText.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            apiService.onSendForgetPassword(requestJsonObject, new ApiService.OnSendForgetPasswordComplete() {
                @Override
                public void onLoginConfirmation(int success) {
                    // password has been sent successfully
                    if (success == 3) {
                        dialog.dismiss();
                        Toast.makeText(ForgetPasswordActivity.this,getString(R.string.password_sent),Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(ForgetPasswordActivity.this,LoginActivity.class);
                        startActivity(in);
                    }
                    // there is no such
                    else if (success == 2) {
                        dialog.dismiss();
                        Toast.makeText(ForgetPasswordActivity.this, R.string.no_such_phone_number,Toast.LENGTH_SHORT).show();
                    }
                    else if (success == 1) {
                        dialog.dismiss();
                        Toast.makeText(ForgetPasswordActivity.this, R.string.server_failure,Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

}
