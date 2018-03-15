package com.example.shehnepours.taxam.apiServices.taxFormServices.taxAgreementService;

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

public class SendAgreementInfoApiService {
    public int success;

    private static final String TAG = "AGREEMENT_API_SERVICE";
    private Context context;
    public SendAgreementInfoApiService(Context context) {this.context = context;}


    // send Tax Form to Server
    public void onSendAgreementInfoConfirmation(JSONObject requestJSONObject, final SendAgreementInfoApiService.OnSendAgreementInfoConfirmationComplete onSendAgreementInfoConfirmationComplete){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, ServiceScriptsAdresses.SEND_AGREEMENT_INFO_ADDRESS, requestJSONObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: " + ServiceScriptsAdresses.SEND_AGREEMENT_INFO_ADDRESS);
                Log.i(TAG, "onResponse: "+ response.toString());
                try {
                    success = response.getInt("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onSendAgreementInfoConfirmationComplete.onSendAgreementInfoConfirmation(success);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: "+error.toString());
                onSendAgreementInfoConfirmationComplete.onSendAgreementInfoConfirmation(0);
            }
        });

        // add request to queue
        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }


    public interface OnSendAgreementInfoConfirmationComplete{
        void onSendAgreementInfoConfirmation(int success);
    }


}
