package com.example.shehnepours.taxam.parents;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.utils.FontsHandeller;

/**
 * Created by shehnepour.s on 2/12/2018.
 */

public class ShadowEditText extends EditText {
    public ShadowEditText(Context context) {
        super(context);
        init();
    }

    public ShadowEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShadowEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ShadowEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setTypeface(FontsHandeller.setFont(getContext(),"sans_lit"));
        setElevation(8);


    }
}
