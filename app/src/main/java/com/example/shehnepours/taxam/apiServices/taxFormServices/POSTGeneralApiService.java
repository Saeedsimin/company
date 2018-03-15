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
 * Created by shehnepour.s on 1/14/2018.
 */

public class POSTGeneralApiService {


    public int success;
    private String requestURL;
    private String TAG;
    private Context context;


    public POSTGeneralApiService(Context context, String ApiTAG, String URL) {
        this.context = context;
        requestURL = URL;
        TAG = ApiTAG;

    }

    // send Tax Form to Server
    public void onPOSTConfirmation(JSONObject requestJSONObject, final POSTGeneralApiService.OnPOSTConfirmationComplete onPOSTConfirmationComplete){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, requestURL, requestJSONObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: " + requestURL);
                Log.i(TAG, "onResponse: "+ response.toString());
                try {
                    success = response.getInt("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onPOSTConfirmationComplete.onPOSTConfirmation(success);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: "+error.toString());
                onPOSTConfirmationComplete.onPOSTConfirmation(0);
            }
        });

        // add request to queue
        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }


    public interface OnPOSTConfirmationComplete{
        void onPOSTConfirmation(int success);
    }



}
