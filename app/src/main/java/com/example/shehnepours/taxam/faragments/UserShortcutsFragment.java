package com.example.shehnepours.taxam.faragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.constants.Variables;
import com.example.shehnepours.taxam.faragments.rules.RulesFragment;
import com.example.shehnepours.taxam.parents.ActivityTitleTextView;
import com.example.shehnepours.taxam.parents.CustomButton;
import com.example.shehnepours.taxam.parents.CustomDialog;

/**
 * Created by shehnepour.s on 11/6/2017.
 */

public class UserShortcutsFragment extends Fragment {
    private View view;
    private CustomDialog insertInfoDialog;
    private CustomButton taxFileButton;
    private CustomButton rulesButton;
    private SeekBar infoSeekBar;
    private FragmentManager fragmentManager;
    private ActivityTitleTextView titleTextView;
    private Context context;

    public UserShortcutsFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.layout_user_shortcuts_fragment,container,false);
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
                getActivity().onBackPressed();
            }
        });
    }

    private void setupTitle() {
        titleTextView.setText("پروفایل کاربری");
    }

    private void setupListener() {
        taxFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTaxFileClick();
            }
        });
        infoSeekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        rulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragment(new RulesFragment(getContext()));
            }
        });
    }

    private void setupView() {
        insertInfoDialog = new CustomDialog(getContext(),R.style.DialogSlideAnim);
        taxFileButton = (CustomButton)view.findViewById(R.id.taxfile_btn);
        rulesButton = (CustomButton)view.findViewById(R.id.rules_btn);
        infoSeekBar = (SeekBar)view.findViewById(R.id.mf_progress_bar);
        fragmentManager = getActivity().getSupportFragmentManager();
        titleTextView = (ActivityTitleTextView)getActivity().findViewById(R.id.toolbar_title);


    }


    private void onTaxFileClick() {
        if (infoSeekBar.getProgress() == 10) {
            insertInfoDialog.show();
            insertInfoDialog.no.setText("");
            insertInfoDialog.no.setClickable(false);
            insertInfoDialog.setCancelable(true);
            insertInfoDialog.yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToFragment(new BusinessRegisterFragment(getActivity()));
                    insertInfoDialog.dismiss();
                }
            });

        }
        else {
            goToFragment(new TaxFileFragment(getContext()));
            insertInfoDialog.dismiss();
        }

    }


    private void goToFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
                .replace(R.id.fragment_container,fragment).commit();
        transaction.addToBackStack(Variables.USER_SHORTCUT);

    }


}
