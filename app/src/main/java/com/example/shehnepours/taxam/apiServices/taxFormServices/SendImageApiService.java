package com.example.shehnepours.taxam.apiServices.taxFormServices;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.shehnepours.taxam.constants.ServiceScriptsAdresses;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shehnepour.s on 1/8/2018.
 */

public class SendImageApiService {
    public int success;

    private static final String TAG = "IMAGE_API_SERVICE";
    private Context context;
    public SendImageApiService(Context context) {this.context = context;}


    // send Tax Form to Server
    public void onSendImageConfirmation(JSONObject requestJSONObject, final SendImageApiService.OnSendImageConfirmationComplete onSendImageConfirmationComplete){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, ServiceScriptsAdresses.SEND_IMAGE_ADDRESS, requestJSONObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: " + ServiceScriptsAdresses.SEND_IMAGE_ADDRESS);
                Log.i(TAG, "onResponse: "+ response.toString());
                try {
                    success = response.getInt("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onSendImageConfirmationComplete.onSendImageConfirmation(success);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: "+error.toString());
                onSendImageConfirmationComplete.onSendImageConfirmation(0);
            }
        });

        // add request to queue
        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }


    public interface OnSendImageConfirmationComplete{
        void onSendImageConfirmation(int success);
    }


}
