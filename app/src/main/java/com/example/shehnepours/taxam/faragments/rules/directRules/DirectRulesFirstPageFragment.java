package com.example.shehnepours.taxam.faragments.rules.directRules;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.constants.Variables;
import com.example.shehnepours.taxam.faragments.services.ConsultPaymentFragment;
import com.example.shehnepours.taxam.parents.ActivityTitleTextView;
import com.example.shehnepours.taxam.parents.CustomButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shehnepour.s on 2/26/2018.
 */

public class DirectRulesFirstPageFragment extends Fragment{


    private View view;
    private SharedPreferences sharedPreferences;
    private ActivityTitleTextView titleTextView;
    private FragmentManager fragmentManager;
    private CustomButton operationalRulesButton;
    private CustomButton valueAddedRulesbButton;
    private CustomButton consultButton;

    private ArrayList<String> operationalArrayList;
    private ArrayList<String> valueAddedArrayList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_rules_firstpage,container,false);
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
        titleTextView.setText("قوانین و بخشنامه‌ها");
    }

    private void setupListener() {
        operationalRulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationalArrayList = new ArrayList<String>();
                operationalArrayList.add("مستقیم");
                goToFragment(new OperationalRulesFragment(),operationalArrayList);
            }
        });
        valueAddedRulesbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueAddedArrayList = new ArrayList<String>();
                valueAddedArrayList.add("ارزش افزوده");
                goToFragment(new ValueAddedRulesFragment(),valueAddedArrayList);
            }
        });
        consultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToConsultFragment(new ConsultPaymentFragment());
            }
        });
    }

    private void goToConsultFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
                .replace(R.id.newtax_frame_layout,fragment).commit();
        transaction.addToBackStack(Variables.USER_SHORTCUT);
    }

    private void setupView() {
        operationalRulesButton = (CustomButton)view.findViewById(R.id.operational_tax_btn);
        valueAddedRulesbButton = (CustomButton)view.findViewById(R.id.value_add_btn);
        consultButton = (CustomButton) view.findViewById(R.id.consult_btn);
        titleTextView = (ActivityTitleTextView)getActivity().findViewById(R.id.toolbar_title);
        fragmentManager = getActivity().getSupportFragmentManager();

    }
    private void goToFragment(Fragment fragment, ArrayList<String> stepArrayList) {
//        Bundle bundle = new Bundle();
//        bundle.putStringArrayList(Variables.STEPS_ARRAYLIST,stepArrayList);
//        fragment.setArguments(bundle);
        saveStepsToSharedPreferecnce(stepArrayList);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
                .replace(R.id.newtax_frame_layout,fragment).commit();
        transaction.addToBackStack(Variables.USER_SHORTCUT);

    }


    private void saveStepsToSharedPreferecnce(ArrayList<String> stepArrayList) {
        sharedPreferences = getContext().getSharedPreferences(Variables.RULES_STEPS_SHARED_PREF,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("SIZE",stepArrayList.size());
        for (int i = 0; i<stepArrayList.size();i++) {
            editor.remove("STATUS"+i);
            editor.putString("STATUS"+i,stepArrayList.get(i));
        }
//        editor.putString(Variables.RULES_TYPE_SHARED_PREF,)
//
//        Set<String> set = new HashSet<>();
//        set.addAll(stepArrayList);
//        editor.putStringSet(Variables.RULES_TYPE_SHARED_PREF,set);
        editor.apply();
    }

}
