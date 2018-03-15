package com.example.shehnepours.taxam.faragments.services;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.adapter.CustomSpinnerAdapter;
import com.example.shehnepours.taxam.parents.ActivityTitleTextView;
import com.example.shehnepours.taxam.parents.CustomButton;
import com.example.shehnepours.taxam.parents.CustomRadioButton;
import com.example.shehnepours.taxam.parents.CustomTextView;

import java.util.ArrayList;

/**
 * Created by shehnepour.s on 3/5/2018.
 */

public class ConsultPaymentFragment extends Fragment {

    private View view;
    private CustomRadioButton face2faceConsultButton;
    private Spinner priceSpinner;
    private CustomSpinnerAdapter priceSpinnerAdapter;

    private CustomRadioButton phoneConsult;
    private Spinner timeSpinner;
    private CustomSpinnerAdapter timeSpinnerAdapter;
    private CustomTextView consultRateTextView;
    private CustomTextView totalPriceTextView;

    private CustomRadioButton earliestTimeButton;
    private CustomRadioButton morningButton;
    private CustomRadioButton afternoonButton;

    private TextInputEditText messageBox;
    private CustomButton sendRequestButton;

    private ActivityTitleTextView titleTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_consult_payment,container,false);
        setupView();
        setupListener();
        setupTitle();
        setupBackButton();
        return view;
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

    private void setupTitle() {
        titleTextView.setText("مشاوره مالیاتی");

    }


    private void setupListener() {
        face2faceConsultButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                phoneConsult.setChecked(!isChecked);
            }
        });
        phoneConsult.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                face2faceConsultButton.setChecked(!isChecked);
            }
        });
        timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        totalPriceTextView.setText("۲۰۰۰۰ تومان");
                        break;
                    case 1:
                        totalPriceTextView.setText("۴۰۰۰۰ تومان");
                        break;
                    case 2:
                        totalPriceTextView.setText("۶۰۰۰۰ تومان");
                        break;
                    case 3:
                        totalPriceTextView.setText("۸۰۰۰۰ تومان");
                        break;
                    case 4:
                        totalPriceTextView.setText("۱۰۰۰۰۰ تومان");
                        break;
                    case 5:
                        totalPriceTextView.setText("۱۲۰۰۰۰ تومان");
                        break;
                    default:
                        totalPriceTextView.setText("۲۰۰۰۰ تومان");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 3/6/2018
            }
        });

    }

    private void setupView() {
        titleTextView = (ActivityTitleTextView) getActivity().findViewById(R.id.toolbar_title);
        face2faceConsultButton = (CustomRadioButton)view.findViewById(R.id.face2face_consult);
        priceSpinner = (Spinner)view.findViewById(R.id.price_dropdown);
        priceSpinner.setDropDownVerticalOffset(60);
        setupSpinner("price");

        phoneConsult = (CustomRadioButton) view.findViewById(R.id.phone_consult);
        timeSpinner = (Spinner) view.findViewById(R.id.time_dropdown);
        timeSpinner.setDropDownVerticalOffset(60);
        setupSpinner("time");
        consultRateTextView = (CustomTextView) view.findViewById(R.id.consult_rate);
        totalPriceTextView = (CustomTextView) view.findViewById(R.id.total_rate);

        earliestTimeButton = (CustomRadioButton) view.findViewById(R.id.earliest_time_btn);
        morningButton = (CustomRadioButton) view.findViewById(R.id.morning_btn);
        afternoonButton = (CustomRadioButton) view.findViewById(R.id.afternoon_btn);

        messageBox = (TextInputEditText) view.findViewById(R.id.payment_message_box);
        sendRequestButton = (CustomButton) view.findViewById(R.id.send_message_btn);


    }

    private void setupSpinner(String type) {
        if(type.matches("price")) {
            ArrayList<String> priceList = new ArrayList<>();
            priceList.add("۲,۰۰۰ تومان");
            priceList.add("۵,۰۰۰ تومان");
            priceList.add("۱۰,۰۰۰ تومان");
            priceList.add("۲۰,۰۰۰ تومان");
            priceSpinnerAdapter = new CustomSpinnerAdapter(getContext(),priceList,0,14);
            priceSpinner.setAdapter(priceSpinnerAdapter);
        } else if (type.matches("time")) {
            ArrayList<String> timeList = new ArrayList<>();
            timeList.add("۱۰ دقیقه");
            timeList.add("۲۰ دقیقه");
            timeList.add("۳۰ دقیقه");
            timeList.add("۴۰ دقیقه");
            timeList.add("۵۰ دقیقه");
            timeList.add("یک ساعت");
            timeSpinnerAdapter = new CustomSpinnerAdapter(getContext(),timeList,0,14);
            timeSpinner.setAdapter(timeSpinnerAdapter);
        }


    }
}
