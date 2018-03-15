package com.example.shehnepours.taxam.parents;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.example.shehnepours.taxam.utils.FontsHandeller;

/**
 * Created by shehnepour.s on 2/28/2018.
 */

public class VerticalTextView extends TextView {
    final boolean topDown;

    public VerticalTextView(Context context, AttributeSet attrs){
        super(context, attrs);
        setTypeface(FontsHandeller.setFont(context,"sans_lit"));
        final int gravity = getGravity();
        if(Gravity.isVertical(gravity) && (gravity&Gravity.VERTICAL_GRAVITY_MASK) == Gravity.BOTTOM) {
            setGravity((gravity&Gravity.HORIZONTAL_GRAVITY_MASK) | Gravity.TOP);
            topDown = true;
        }else
            topDown = false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    @Override
    protected void onDraw(Canvas canvas){
        TextPaint textPaint = getPaint();
        textPaint.setColor(getCurrentTextColor());
        textPaint.drawableState = getDrawableState();

        canvas.save();

        if(topDown){
            canvas.translate(getWidth(), 0);
            canvas.rotate(90);
        }else {
            canvas.translate(0, getHeight());
            canvas.rotate(-90);
        }


        canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());

        getLayout().draw(canvas);
        canvas.restore();
    }
}
