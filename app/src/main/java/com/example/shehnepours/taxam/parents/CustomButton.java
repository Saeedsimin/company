package com.example.shehnepours.taxam.parents;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.example.shehnepours.taxam.utils.FontsHandeller;

/**
 * Created by shehnepour.s on 12/11/2017.
 */


public class CustomButton extends Button{
    public CustomButton(Context context) {
        super(context);
        init(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        setTypeface(FontsHandeller.setFont(context,"sans_lit"));
        setPadding(10,0,10,0);

    }

}
