package com.example.shehnepours.taxam.parents;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.utils.FontsHandeller;

/**
 * Created by shehnepour.s on 3/4/2018.
 */

public class CustomMessageBox extends EditText {

    public CustomMessageBox(Context context) {
        super(context);
        init();
    }

    public CustomMessageBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomMessageBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CustomMessageBox(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    public void init() {
        setTypeface(FontsHandeller.setFont(getContext(),"sans_lit"));
        setBackground(getResources().getDrawable(R.drawable.messagebox_background));
        setHintTextColor(getResources().getColor(R.color.hint_text));
        setHeight(250);
    }
}
