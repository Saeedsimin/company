package com.example.shehnepours.taxam;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shehnepour.s on 10/24/2017.
 */

public class ApiService {

    private static final String TAG = "ApiService";
    private Context context;
    public ApiService(Context context) {this.context = context;}


    // send confirmation
    public void onSendConfirmation(JSONObject requestJSONObject, final OnSendConfirmationComplete onSendConfirmationComplete){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://192.168.10.54/TAXAM/mySendVerificationCode.php", requestJSONObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: "+response.toString());
                int status = 0;
                try {
                    status = response.getInt("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onSendConfirmationComplete.onSendConfirmation(status);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: "+error.toString());
                onSendConfirmationComplete.onSendConfirmation(0);
            }
        });

        // add request to queue
        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }


    public interface OnSendConfirmationComplete{
        void onSendConfirmation(int success);
    }


    // receive confirmation
    public void onRecieveConfirmation(JSONObject requestJSONObject, final OnRecieveConfirmationComplete onRecieveConfirmationComplete){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://192.168.10.54/TAXAM/myRecieveVerificationCode.php", requestJSONObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: "+response.toString());
                int status = 0;
                try {
                    status = response.getInt("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onRecieveConfirmationComplete.onRecieveConfirmation(status);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: "+error.toString());
                onRecieveConfirmationComplete.onRecieveConfirmation(0);
            }
        });

        // add request to queue
        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);

    }
    public interface OnRecieveConfirmationComplete{
        void onRecieveConfirmation(int success);

    }


    // sending login information to server
    public void onLoginConfirmation(JSONObject requestJsonObject, final OnLoginConfirmationCompelte onLoginConfirmationCompelte){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://192.168.10.54/TAXAM/loginService.php", requestJsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: "+response.toString());
                int status = 0;
                try {
                    status = response.getInt("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onLoginConfirmationCompelte.onLoginConfirmation(status);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: "+error.toString());
                onLoginConfirmationCompelte.onLoginConfirmation(0);
            }
        });

        // add request to queue
        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);


    }


    public interface OnLoginConfirmationCompelte{

        void onLoginConfirmation(int success);
    }


    // sending new password to user phone
    public void onSendForgetPassword(JSONObject requestJsonObject, final OnSendForgetPasswordComplete onSendForgetPasswordComplete){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://192.168.10.54/TAXAM/sendForgetPassword.php", requestJsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: "+response.toString());
                int status = 0;
                try {
                    status = response.getInt("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onSendForgetPasswordComplete.onLoginConfirmation(status);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: "+error.toString());
                onSendForgetPasswordComplete.onLoginConfirmation(0);
            }
        });

        // add request to queue
        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);


    }


    public interface OnSendForgetPasswordComplete{

        void onLoginConfirmation(int success);
    }



    // resending new password to user phone
    public void onResendForgetPassword(JSONObject requestJsonObject, final OnResendForgetPasswordComplete onResendForgetPasswordComplete){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://192.168.10.54/TAXAM/resendVerificationCode.php", requestJsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: "+response.toString());
                int status = 0;
                try {
                    status = response.getInt("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onResendForgetPasswordComplete.onResendConfirmation(status);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: "+error.toString());
                onResendForgetPasswordComplete.onResendConfirmation(0);
            }
        });

        // add request to queue
        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);


    }


    public interface OnResendForgetPasswordComplete{
        void onResendConfirmation(int success);
    }








}
