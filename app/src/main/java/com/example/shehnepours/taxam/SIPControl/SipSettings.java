package com.example.shehnepours.taxam.SIPControl;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.sip.SipAudioCall;
import android.net.sip.SipException;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.net.sip.SipRegistrationListener;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;

/**
 * Created by shehnepour.s on 2/5/2018.
 */
import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.example.shehnepours.taxam.R;

/**
 * Handles SIP authentication settings for the Walkie Talkie app.
 */
public class SipSettings extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Note that none of the preferences are actually defined here.
        // They're all in the XML file res/xml/preferences.xml.
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}