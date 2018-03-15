package com.example.shehnepours.taxam.parents;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TableRow;

import com.example.shehnepours.taxam.R;

/**
 * Created by shehnepour.s on 12/11/2017.
 */

public class CustomTableHeader extends TableRow {
    public CustomTableHeader(Context context) {
        super(context);
        init();
    }

    public CustomTableHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public void init() {
        setBackground(getResources().getDrawable(R.drawable.listgrad));

    }


}
