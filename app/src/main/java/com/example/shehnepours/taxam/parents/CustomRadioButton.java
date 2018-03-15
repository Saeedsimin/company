package com.example.shehnepours.taxam.parents;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.example.shehnepours.taxam.utils.FontsHandeller;

/**
 * Created by shehnepour.s on 3/4/2018.
 */

public class CustomRadioButton extends RadioButton {
    public CustomRadioButton(Context context) {
        super(context);
        init(context);
    }


    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CustomRadioButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        setTypeface(FontsHandeller.setFont(context,"sans_lit"));
    }


}
