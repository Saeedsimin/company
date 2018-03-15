package com.example.shehnepours.taxam.parents;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by shehnepour.s on 12/10/2017.
 */

public class CustomTextView extends TextView {
    public CustomTextView(Context context) {
        super(context);
        init();
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    public void init() {
        Typeface sans = Typeface.createFromAsset(getContext().getAssets(),"fonts/IRANSans_Light.ttf");
        setTypeface(sans);
    }
    public void setFont(String fontsName){
        Typeface byekan = Typeface.createFromAsset(getContext().getAssets(),"fonts/IRANSans_Light.ttf");
        setTypeface(byekan);
    }
}
