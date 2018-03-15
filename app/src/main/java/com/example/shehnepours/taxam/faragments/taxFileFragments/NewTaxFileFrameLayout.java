package com.example.shehnepours.taxam.faragments.taxFileFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.parents.CustomButton;

/**
 * Created by shehnepour.s on 12/5/2017.
 */

public class NewTaxFileFrameLayout extends Fragment {

    private View view;
    private CustomButton button;
    private FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.layout_frame_newtaxfile,container,false);
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.newtax_frame_layout,new NewTaxFileFragment()).commit();

        return view;

    }

}
