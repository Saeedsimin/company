package com.example.shehnepours.taxam.faragments.rules.searchRules;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.adapter.CustomSpinnerAdapter;
import com.example.shehnepours.taxam.parents.CustomButton;
import com.example.shehnepours.taxam.parents.CustomEditText;

import java.util.ArrayList;

/**
 * Created by shehnepour.s on 2/26/2018.
 */

public class SearchRulesFirstPageFragment extends Fragment{

    private View view;
    private Spinner spinner;
    private CustomSpinnerAdapter spinnerAdapter;
    private CustomButton searchButton;
    private CustomButton consultButton;
    private CustomEditText ruleNumberEditText;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_rules_search,container,false);
        setupView();
        setupListener();
        setupTitle();
        setupBackButton();
        return view;
    }

    private void setupBackButton() {

    }

    private void setupTitle() {

    }

    private void setupListener() {
    }

    private void setupView() {
        spinner = (Spinner) view.findViewById(R.id.rules_dropdown);
        ruleNumberEditText = (CustomEditText)view.findViewById(R.id.rules_number);
        consultButton = (CustomButton) view.findViewById(R.id.consult_btn);
        searchButton = (CustomButton) view.findViewById(R.id.search_btn);
        setupSpinner();

    }

    private void setupSpinner() {
        ArrayList<String> rulesList = new ArrayList<>();
        rulesList.add("همه قوانین");
        rulesList.add("قانون مالیات مستقیم");
        rulesList.add("قانون مالیات ارزش افزوده");

        spinnerAdapter = new CustomSpinnerAdapter(getContext(),rulesList);
        spinner.setAdapter(spinnerAdapter);
        spinner.setDropDownVerticalOffset(100);
        spinner.setDropDownHorizontalOffset(10);


    }


}
