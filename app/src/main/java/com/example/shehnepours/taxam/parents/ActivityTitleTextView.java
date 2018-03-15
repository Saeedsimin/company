package com.example.shehnepours.taxam.parents;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by shehnepour.s on 2/15/2018.
 */

public class ActivityTitleTextView extends TextView {
    public ActivityTitleTextView(Context context) {
        super(context);
    }

    public ActivityTitleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ActivityTitleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ActivityTitleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        Typeface sans = Typeface.createFromAsset(getContext().getAssets(),"fonts/IRANSans_Medium.ttf");
        setTypeface(sans);
    }
}
