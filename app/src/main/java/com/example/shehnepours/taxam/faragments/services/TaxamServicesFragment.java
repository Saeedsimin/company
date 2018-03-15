package com.example.shehnepours.taxam.faragments.services;

import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.parents.ActivityTitleTextView;
import com.example.shehnepours.taxam.parents.CustomButton;
import com.example.shehnepours.taxam.parents.CustomDialog;
import com.example.shehnepours.taxam.parents.CustomMessageBox;
import com.example.shehnepours.taxam.parents.CustomRadioButton;

/**
 * Created by shehnepour.s on 3/5/2018.
 */

public class TaxamServicesFragment extends Fragment{

    private View view;
    private CustomRadioButton freeConsult;
    private CustomRadioButton phoneConsult;
    private CustomRadioButton face2faceConsult;
    private CustomRadioButton defenseLayehe;
    private CustomButton sendInfo;
    private CustomDialog freeDialog;
    private CustomDialog defenseDialog;

    private TextInputEditText messageBox;
    private ActivityTitleTextView titleText;

    private Boolean freeConsultFlag = false;
    private Boolean phoneConsultFlag = false;
    private Boolean face2faceConsultFlag = false;
    private Boolean defenseLayeheFlag = false;

    private String messageText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_taxam_services,container,false);
        setupView();
        setupListener();
        setupTitle();
        setupBackButton();
        return view;
    }

    private void setupTitle() {
        titleText.setText("خدمات تکسام");
    }

    private void setupListener() {
        sendInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForCorrectInput();
            }
        });
    }

    private void checkForCorrectInput() {
        if(messageBox.getText().toString().equals("")) {
            Toast.makeText(getContext(),"لطفا سوال خود را در جعبه پیام وارد کنید",Toast.LENGTH_SHORT).show();
        }
        else {
            onSendInfo();
        }
    }

    private void onSendInfo() {
        freeConsultFlag = freeConsult.isChecked();
        phoneConsultFlag = phoneConsult.isChecked();
        face2faceConsultFlag = face2faceConsult.isChecked();
        defenseLayeheFlag = defenseLayehe.isChecked();

        messageText = messageBox.getText().toString();

        SendMessageInfo();
    }

    private void SendMessageInfo() {
        // TODO: 3/5/2018

        if(freeConsultFlag) {
            freeDialog = new CustomDialog(getContext(),R.style.DialogSlideAnim);
            freeDialog.show();
            freeDialog.dialText.setText("همکاران ما در اسرع وقت پاسخگوی سوال شما خواهند بود");
            freeDialog.dialText.setTextColor(getResources().getColor(R.color.main_btn_bck));
            freeDialog.no.setVisibility(View.GONE);
            freeDialog.yes.setVisibility(View.GONE);
            freeDialog.setCancelable(true);
        } else if(defenseLayeheFlag) {
            defenseDialog = new CustomDialog(getContext(),R.style.DialogSlideAnim);
            defenseDialog.show();
            defenseDialog.dialText.setText("درخواست لایحه دفاعی مربوط به پرونده شما برای مشاور ارسال شد. بعد از بررسی با شما تماس خواهند گرفت.");
            defenseDialog.dialText.setTextColor(getResources().getColor(R.color.main_btn_bck));
            defenseDialog.no.setVisibility(View.GONE);
            defenseDialog.yes.setVisibility(View.GONE);
            defenseDialog.setCancelable(true);
        }

    }

    private void setupView() {
        freeConsult = (CustomRadioButton)view.findViewById(R.id.free_consult);
        phoneConsult = (CustomRadioButton)view.findViewById(R.id.phone_consult);
        face2faceConsult = (CustomRadioButton) view.findViewById(R.id.face2face_consult);
        defenseLayehe = (CustomRadioButton) view.findViewById(R.id.defense_layehe);

        sendInfo = (CustomButton) view.findViewById(R.id.send_message_btn);
        messageBox = (TextInputEditText) view.findViewById(R.id.message_box);
        titleText = (ActivityTitleTextView) getActivity().findViewById(R.id.toolbar_title);

    }
    private void setupBackButton() {

        TextView backIcon;
        backIcon = (TextView)getActivity().findViewById(R.id.back_btn);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    getActivity().getSupportFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        } );
    }
}
