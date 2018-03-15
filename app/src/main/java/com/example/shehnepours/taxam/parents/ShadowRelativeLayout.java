package com.example.shehnepours.taxam.parents;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by shehnepour.s on 2/13/2018.
 */

public class ShadowRelativeLayout extends RelativeLayout {
    public ShadowRelativeLayout(Context context) {
        super(context);
        init();
    }

    public ShadowRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShadowRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ShadowRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setElevation(10);
        setTranslationZ(10);
    }
}
