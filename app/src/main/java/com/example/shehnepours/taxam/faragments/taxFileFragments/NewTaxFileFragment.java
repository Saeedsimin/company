package com.example.shehnepours.taxam.faragments.taxFileFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.TextInfo;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.shehnepours.taxam.Activity.UserProfileActivity;
import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.constants.Variables;
import com.example.shehnepours.taxam.faragments.taxFileFragments.addedValueFragments.AddedValueAgreementFragment;
import com.example.shehnepours.taxam.faragments.taxFileFragments.operationalFragments.OperationalAgreementFragment;
import com.example.shehnepours.taxam.parents.ActivityTitleTextView;
import com.example.shehnepours.taxam.parents.CustomButton;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by shehnepour.s on 12/20/2017.
 */

public class NewTaxFileFragment extends Fragment {

    private TextView backIcon;
    private View view;
    private View tableView;
    private CustomButton button;
    private FragmentManager fragmentManager;
    private ActivityTitleTextView titleTextView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_newtaxfile_fragment,container,false);
        setupView();
        setupListener();
        setupTitle();
        setupBackButton();



        return view;
    }

    private void setupTitle() {
        titleTextView.setText("پرونده مالیاتی");
    }

    private void setupListener() {
        TableRow firstRow = (TableRow)tableView.findViewById(R.id.newtaxfile_firstrow);
        TableRow secondRow = (TableRow)tableView.findViewById(R.id.newtaxfile_secondrow);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragment(new NewTaxFormFragment());
            }
        });
        firstRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((UserProfileActivity)getActivity()).gotoFragment(new AddedValueAgreementFragment());
                Fragment eterazFragment = new EtereazFileFragment();
                setBundleForFragment(eterazFragment,Variables.STEPS_NUMBER,1);
                goToFragment(eterazFragment);
            }
        });
        secondRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment operationalFragment = new OperationalAgreementFragment();
                setBundleForFragment(operationalFragment,Variables.STEPS_NUMBER,2);
                goToFragment(operationalFragment);
            }
        });

    }

    private void setupView() {
        button = (CustomButton)view.findViewById(R.id.newtaxfile_button);
        fragmentManager = getActivity().getSupportFragmentManager();
        tableView = view.findViewById(R.id.taxfiles_table);
        titleTextView = (ActivityTitleTextView)getActivity().findViewById(R.id.toolbar_title);
    }


    private void setupBackButton() {
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


    public void goToFragment(Fragment fragment) {
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
                .replace(R.id.newtax_frame_layout,fragment).addToBackStack(Variables.USER_SHORTCUT).commit();

    }

    private void setBundleForFragment(Fragment fragment,String key, int step) {
        Bundle bundle = new Bundle();
        bundle.putInt(Variables.STEPS_NUMBER,step);
        fragment.setArguments(bundle);
    }

}
