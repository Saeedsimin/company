package com.example.shehnepours.taxam.faragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.adapter.CustomSpinnerAdapter;
import com.example.shehnepours.taxam.parents.ActivityTitleTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shehnepour.s on 2/25/2018.
 */

public class BusinessRegisterFragment extends Fragment {

    private View view;
    private Activity activity;
    private ActivityTitleTextView titleTextView;
    private Spinner personType;
    private CustomSpinnerAdapter customSpinnerAdapter;

    public BusinessRegisterFragment(Activity activity) {
        this.activity= activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.layout_business_register,container,false);
        setupView();
        setupListener();
        setupTitle();
        setupBackButton();
        return view;
    }

    private void setupTitle() {
        titleTextView = (ActivityTitleTextView)activity.findViewById(R.id.toolbar_title);
        titleTextView.setText("تکمیل اطلاعات");
    }

    private void setupListener() {
        setupSpinnerListener();
    }

    private void setupSpinnerListener() {
        // Spinner Drop down elements
        final ArrayList<String> personTypes = new ArrayList<String>();
        personTypes.add("حقیقی");
        personTypes.add("حقوقی");
        customSpinnerAdapter = new CustomSpinnerAdapter(getContext(),personTypes);
        personType.setAdapter(customSpinnerAdapter);
        personType.setDropDownVerticalOffset(100);

        personType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setupView() {
        personType = (Spinner)view.findViewById(R.id.person_type_dropdown);


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
