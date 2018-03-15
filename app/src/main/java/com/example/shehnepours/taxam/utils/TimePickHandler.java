package com.example.shehnepours.taxam.utils;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.example.shehnepours.taxam.faragments.taxFileFragments.NewTaxFormFragment;
import com.example.shehnepours.taxam.parents.CustomButton;
import com.example.shehnepours.taxam.parents.CustomTextView;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

/**
 * Created by shehnepour.s on 1/2/2018.
 */

public class TimePickHandler implements DatePickerDialog.OnDateSetListener {

    private Activity activity;
    private TextView textView;
    private String date="";
    private String persianYear = "";
    private String persianMonth = "" ;
    private String persianDay = "";


    public TimePickHandler (Activity activity,TextView textView) {
        this.textView = textView;
        this.activity = activity;
    }

    public void onRecieveDate() {

        PersianCalendar persianCalendar = new PersianCalendar();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                this,
                persianCalendar.getPersianYear(),
                persianCalendar.getPersianMonth(),
                persianCalendar.getPersianDay());
        datePickerDialog.show(activity.getFragmentManager(), "Datepickerdialog");
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        int month = monthOfYear+1;

        for (int i = 0; i< Integer.toString(year).length(); i++) {
            persianYear = persianYear.concat(En2FaNumber.En2FaChanger(Integer.toString(year).substring(i,i+1)));
        }
        for (int i = 0; i< Integer.toString(month).length(); i++) {
            persianMonth = persianMonth.concat(En2FaNumber.En2FaChanger(Integer.toString(month).substring(i,i+1)));
        }
        for (int i = 0; i< Integer.toString(dayOfMonth).length(); i++) {
            persianDay = persianDay.concat(En2FaNumber.En2FaChanger(Integer.toString(dayOfMonth).substring(i,i+1)));
        }
        date = year + "-" + month + "-" + dayOfMonth;
        textView.setText(persianYear+"-" + persianMonth+"-"+ persianDay);
    }

    public String getEnDate() {
        return date;
    }
}
