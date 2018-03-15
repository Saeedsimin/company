package com.example.shehnepours.taxam.faragments.taxFileFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shehnepours.taxam.R;

/**
 * Created by shehnepour.s on 12/5/2017.
 */

public class TaxFileArchiveFragment extends Fragment{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_taxarchive_fragment,container,false);
    }

}
