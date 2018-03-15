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
 * Created by shehnepour.s on 1/7/2018.
 */

public class TaxFormApiService {

    private static final String TAG = "TAX_FORM_API_SERVICE";
    private Context context;
    public TaxFormApiService(Context context) {this.context = context;}


    // send Tax Form to Server
    public void onSendTaxFormConfirmation(JSONObject requestJSONObject, final TaxFormApiService.OnSendTaxFormConfirmationComplete onSendTaxFormConfirmationComplete){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, ServiceScriptsAdresses.SEND_FORM_ADDRESS, requestJSONObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: "+response.toString());
                int status = 0;
                try {
                    status = response.getInt("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onSendTaxFormConfirmationComplete.onSendTaxFormConfirmation(status);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: "+error.toString());
                onSendTaxFormConfirmationComplete.onSendTaxFormConfirmation(0);
            }
        });

        // add request to queue
        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }


    public interface OnSendTaxFormConfirmationComplete{
        void onSendTaxFormConfirmation(int success);
    }


}
