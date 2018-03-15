package com.example.shehnepours.taxam.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class LoginActivity extends AppCompatActivity {



    // Getting layout resources
    private CustomEditText phoneEditText;
    private CustomEditText passEditText;
    private CustomButton loginButton;
    private CustomTextView forgetTextView;
    private ProgressDialog dialog;

    // Hidden variables
    private UserSharedPrefManager sharedPrefManager;
    private User user;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_login);

        // Defining different fonts
//        Typeface sanstypeface = Typeface.createFromAsset(getAssets(),"fonts/irsans.ttf");
//        Typeface baykanTypeface = Typeface.createFromAsset(getAssets(),"fonts/byekan.ttf");


        // Getting components in layout
        phoneEditText = (CustomEditText) findViewById(R.id.phone_login_edittext);
        passEditText = (CustomEditText) findViewById(R.id.password_login_edittext);
        loginButton = (CustomButton) findViewById(R.id.login_button);
        forgetTextView = (CustomTextView) findViewById(R.id.login_text);

        // Setting fonts for different component in layout
//        phoneEditText.setTypeface(baykanTypeface);
//        passEditText.setTypeface(baykanTypeface);
//        loginButton.setTypeface(baykanTypeface);
//        forgetTextView.setTypeface(baykanTypeface);

        // Getting last user logged in from sharedpreference
        sharedPrefManager = new UserSharedPrefManager(LoginActivity.this);
        user = sharedPrefManager.getUser();

        // Filling fields using sharedPreference informations
        phoneEditText.setText(user.getPhone_number());
        passEditText.setText(user.getPassword());

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginErrorChecker();
            }
        });

        forgetTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                startActivity(in);
            }
        });







    }

    public void onLoginErrorChecker()
    {
        if (phoneEditText.getText().toString().length()==0 ||
                passEditText.getText().toString().length()==0)
        {
            Toast.makeText(LoginActivity.this, R.string.fill_all_fields_warning,Toast.LENGTH_SHORT).show();
        }
        else {
            String passwordText = passEditText.getText().toString();
            if(passwordText.length()<6 || passwordText.length()>16)
            {
                Toast.makeText(LoginActivity.this, R.string.password_length_warning,Toast.LENGTH_SHORT).show();
            }
            else {
                dialog = ProgressDialog.show(LoginActivity.this,"",getString(R.string.please_wait));
                onLogin();
            }
        }





    }
    public void onLogin() {


        apiService = new ApiService(LoginActivity.this);
        JSONObject requestJsonObject = new JSONObject();
        try {
            requestJsonObject.put(Variables.USER_PHONE_KEY,phoneEditText.getText().toString());
            requestJsonObject.put(Variables.USER_PASS_KEY,passEditText.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        apiService.onLoginConfirmation(requestJsonObject, new ApiService.OnLoginConfirmationCompelte() {
            @Override
            public void onLoginConfirmation(int success) {
                // User found on server
                if (success == 5) {
                    dialog.dismiss();
                    saveToSharedPreferences();
                    Intent in = new Intent(LoginActivity.this, UserProfileActivity.class);
                    startActivity(in);
                }

                // User is found on server but not activated
                if (success == 4) {
                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this, R.string.activate_your_account,Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(LoginActivity.this, ConfirmationActivity.class);
                    startActivity(in);
                }
                // User, pass is incorrect
                else if (success == 3) {
                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this, R.string.wrong_user_pass,Toast.LENGTH_SHORT).show();
                }
                // There is no such user
                else if(success == 2) {
                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this, R.string.not_signup,Toast.LENGTH_SHORT).show();

                }
                // Server error
                else {
                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this,R.string.server_failure,Toast.LENGTH_SHORT).show();
                }
            }
        });



}

    private void saveToSharedPreferences() {
        user = new User();
        user.setPhone_number(phoneEditText.getText().toString());
        user.setPassword(passEditText.getText().toString());
        user.setIsValidated(0);
        sharedPrefManager = new UserSharedPrefManager(LoginActivity.this);
        sharedPrefManager.saveUser(user);

    }

}
