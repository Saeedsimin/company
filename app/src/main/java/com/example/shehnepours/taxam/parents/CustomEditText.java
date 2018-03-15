package com.example.shehnepours.taxam.parents;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.EditText;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.utils.FontsHandeller;

/**
 * Created by shehnepour.s on 12/12/2017.
 */

public class CustomEditText extends EditText {
    public CustomEditText(Context context) {
        super(context);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    public void init() {
        setTypeface(FontsHandeller.setFont(getContext(),"sans_lit"));
        setBackground(getResources().getDrawable(R.drawable.edittextbackground));
        setHintTextColor(getResources().getColor(R.color.hint_text));
    }
}
