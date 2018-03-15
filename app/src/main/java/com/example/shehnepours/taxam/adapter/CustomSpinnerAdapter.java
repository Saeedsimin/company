package com.example.shehnepours.taxam.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.parents.CustomTextView;

import java.util.ArrayList;

/**
 * Created by shehnepour.s on 2/26/2018.
 */

public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter{

    private ArrayList<String> list;
    private Context context;
    private int defaultPadding = 16;
    private int defaultTextSize = 18;


    public CustomSpinnerAdapter(Context context, ArrayList<String> list){
        this.context = context;
        this.list = list;
    }

    public CustomSpinnerAdapter(Context context, ArrayList<String> list, int defaultPadding, int defaultTextSize) {
        this.context = context;
        this.list = list;
        this.defaultTextSize = defaultTextSize;
        this.defaultPadding = defaultPadding;
    }
    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)(position);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomTextView txt = new CustomTextView(context);
        txt.setPadding(defaultPadding, defaultPadding, defaultPadding, defaultPadding);
        txt.setTextSize(defaultTextSize);
        txt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_down_green_dark,0,0,0);
        txt.setText(list.get(position));
        txt.setTextColor(context.getResources().getColor(R.color.hint_text));
        return  txt;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        CustomTextView txt = new CustomTextView(context);
        txt.setPadding(defaultPadding, defaultPadding, defaultPadding, defaultPadding);
        txt.setTextSize(defaultTextSize);
        txt.setText(list.get(position));
        txt.setTextColor(context.getResources().getColor(R.color.hint_text));
        txt.setBackgroundColor(Color.WHITE);
        return  txt;
    }

}
