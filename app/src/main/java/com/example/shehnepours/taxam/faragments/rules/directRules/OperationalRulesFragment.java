package com.example.shehnepours.taxam.faragments.rules.directRules;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.adapter.CustomListViewAdapter;
import com.example.shehnepours.taxam.constants.Variables;
import com.example.shehnepours.taxam.faragments.UserShortcutsFragment;
import com.example.shehnepours.taxam.parents.CustomTextView;
import com.example.shehnepours.taxam.parents.VerticalTextView;
import com.example.shehnepours.taxam.utils.RulesItems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shehnepour.s on 3/1/2018.
 */

public class OperationalRulesFragment extends Fragment {

    private View view;
    private ListView rulesListView;
    private CustomListViewAdapter listViewAdapter;
    private FragmentManager fragmentManager;
    private SharedPreferences sharedPreferences;

    private ImageView taxTypeDown;
    private ImageView taxTypeUp;

    private ImageView babDown;
    private ImageView babUp;

    private ImageView faslDown;
    private ImageView faslUp;

    private ImageView madehDown;
    private ImageView madehUp;

    private VerticalTextView taxTypeText;
    private VerticalTextView faslText;
    private VerticalTextView babText;
    private VerticalTextView madehText;

    private CustomTextView rulesTitleText;

    private Bundle bundle;
    private ArrayList<String> titleArrayList;
    private ArrayList<RulesItems> subtitlesList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.layout_rules_operational,container,false);
        setupView();
        setListener();
        setupBackButton();
        return view;
    }

    private void setListener() {
        rulesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomTextView title = (CustomTextView) view.findViewById(R.id.rule_title_text);
                ImageView arrowImage = (ImageView) view.findViewById(R.id.arrow_image);
                titleArrayList.add(title.getText().toString());
                if (arrowImage.getVisibility() == View.GONE) {
                    // TODO: 3/3/2018
                    goToFragment(new UserShortcutsFragment(getContext()));
                } else {
                    goToFragment(new OperationalRulesFragment());
                }
            }

            
        });
    }

    private void goToFragment(Fragment fragment) {
//        Bundle bundle = new Bundle();
//        bundle.putStringArrayList(Variables.STEPS_ARRAYLIST,titleArrayList);
//        fragment.setArguments(bundle);

        saveStepsToSharedPreferecnce(titleArrayList);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
                .replace(R.id.newtax_frame_layout,fragment).commit();
        transaction.addToBackStack(Variables.USER_SHORTCUT);

    }

    private void setupBackButton() {

        TextView backIcon;
        backIcon = (TextView)getActivity().findViewById(R.id.back_btn);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleArrayList.remove(titleArrayList.size()-1);
                saveStepsToSharedPreferecnce(titleArrayList);
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
                    titleArrayList.remove(titleArrayList.size()-1);
                    saveStepsToSharedPreferecnce(titleArrayList);
                    getActivity().getSupportFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        } );
    }

    private void setupView() {
//        bundle = OperationalRulesFragment.this.getArguments();
//        if (bundle != null) {
//            titleArrayList = bundle.getStringArrayList(Variables.STEPS_ARRAYLIST);
//        }
        sharedPreferences = getContext().getSharedPreferences(Variables.RULES_STEPS_SHARED_PREF,Context.MODE_PRIVATE);
        retreiveSteps();
        rulesListView = (ListView)view.findViewById(R.id.rules_list);
        rulesTitleText = (CustomTextView) view.findViewById(R.id.rules_title_list);

        taxTypeText = (VerticalTextView)view.findViewById(R.id.tax_type_subtitle);
        taxTypeDown = (ImageView) view.findViewById(R.id.tax_type_down);
        taxTypeUp = (ImageView) view.findViewById(R.id.tax_type_up);

        babText = (VerticalTextView) view.findViewById(R.id.bab_subtitle);
        babDown = (ImageView) view.findViewById(R.id.bab_down);
        babUp = (ImageView) view.findViewById(R.id.bab_up);

        faslText = (VerticalTextView) view.findViewById(R.id.fasl_subtitle);
        faslUp = (ImageView) view.findViewById(R.id.fasl_up);
        faslDown = (ImageView) view.findViewById(R.id.fasl_down);

        madehText = (VerticalTextView) view.findViewById(R.id.madeh_subtitle);
        madehDown = (ImageView) view.findViewById(R.id.madeh_down);
        madehUp = (ImageView) view.findViewById(R.id.madeh_up);

        fragmentManager = getActivity().getSupportFragmentManager();
        configView();


    }

    private void retreiveSteps() {
        int size = sharedPreferences.getInt("SIZE", 0);
        titleArrayList = new ArrayList<>();
        for(int i=0;i<size;i++)
        {
            titleArrayList.add(sharedPreferences.getString("STATUS" + i, null));
        }
    }

    private void configView() {
        if(titleArrayList.size() == 1) {
            taxTypeText.setText(titleArrayList.get(0));
            babText.setText("");
            faslText.setText("");
            madehText.setText("");

            taxTypeDown.setVisibility(View.VISIBLE);
            taxTypeUp.setVisibility(View.VISIBLE);

            babDown.setVisibility(View.GONE);
            babUp.setVisibility(View.GONE);

            faslDown.setVisibility(View.GONE);
            faslUp.setVisibility(View.GONE);

            madehUp.setVisibility(View.GONE);
            madehDown.setVisibility(View.GONE);

            rulesTitleText.setText(titleArrayList.get(0));
        }

        if(titleArrayList.size() == 2) {
            taxTypeText.setText(titleArrayList.get(0));
            babText.setText(titleArrayList.get(1));
            faslText.setText("");
            madehText.setText("");

            taxTypeDown.setVisibility(View.GONE);
            taxTypeUp.setVisibility(View.GONE);

            babDown.setVisibility(View.VISIBLE);
            babUp.setVisibility(View.VISIBLE);

            faslDown.setVisibility(View.GONE);
            faslUp.setVisibility(View.GONE);

            madehUp.setVisibility(View.GONE);
            madehDown.setVisibility(View.GONE);

            rulesTitleText.setText(titleArrayList.get(1));
        }
        if(titleArrayList.size() == 3) {
            taxTypeText.setText(titleArrayList.get(0));
            babText.setText(titleArrayList.get(1));
            faslText.setText(titleArrayList.get(2));
            madehText.setText("");

            taxTypeDown.setVisibility(View.GONE);
            taxTypeUp.setVisibility(View.GONE);

            babDown.setVisibility(View.GONE);
            babUp.setVisibility(View.GONE);

            faslDown.setVisibility(View.VISIBLE);
            faslUp.setVisibility(View.VISIBLE);

            madehUp.setVisibility(View.GONE);
            madehDown.setVisibility(View.GONE);

            rulesTitleText.setText(titleArrayList.get(2));
        }

        if(titleArrayList.size() == 4) {
            taxTypeText.setText(titleArrayList.get(0));
            babText.setText(titleArrayList.get(1));
            faslText.setText(titleArrayList.get(2));
            madehText.setText(titleArrayList.get(3));

            taxTypeDown.setVisibility(View.GONE);
            taxTypeUp.setVisibility(View.GONE);

            babDown.setVisibility(View.GONE);
            babUp.setVisibility(View.GONE);

            faslDown.setVisibility(View.GONE);
            faslUp.setVisibility(View.GONE);

            madehUp.setVisibility(View.VISIBLE);
            madehDown.setVisibility(View.VISIBLE);

            rulesTitleText.setText(titleArrayList.get(3));
        }

        generateData();
        listViewAdapter = new CustomListViewAdapter(getContext(),subtitlesList);
        rulesListView.setAdapter(listViewAdapter);

    }

    private void generateData() {
        subtitlesList = new ArrayList<>();
        subtitlesList.add(new RulesItems("مورد اول",0));
        subtitlesList.add(new RulesItems("مورد دوم",1));
        subtitlesList.add(new RulesItems("مورد سوم",1));
        subtitlesList.add(new RulesItems("مورد چهارم",1));
        subtitlesList.add(new RulesItems("مورد پنجم",0));
        subtitlesList.add(new RulesItems("مورد ششم",0));
    }
    private void saveStepsToSharedPreferecnce(ArrayList<String> stepArrayList) {
        sharedPreferences = getContext().getSharedPreferences(Variables.RULES_STEPS_SHARED_PREF, Context.MODE_PRIVATE);
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
