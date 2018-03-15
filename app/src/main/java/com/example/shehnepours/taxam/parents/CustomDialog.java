package com.example.shehnepours.taxam.parents;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.Window;

import com.example.shehnepours.taxam.R;

/**
 * Created by shehnepour.s on 2/25/2018.
 */

public class CustomDialog extends Dialog{
    public Context context;
    public CustomButton yes,no;
    public CustomTextView dialText;
    public CustomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public CustomDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = (CustomButton) findViewById(R.id.btn_yes);
        no = (CustomButton) findViewById(R.id.btn_no);
        dialText = (CustomTextView)findViewById(R.id.txt_dia);
    }
}
