package com.example.shehnepours.taxam.faragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.adapter.TaxFileViewPagerAdapter;
import com.example.shehnepours.taxam.parents.ActivityTitleTextView;
import com.example.shehnepours.taxam.utils.FontsHandeller;

/**
 * Created by shehnepour.s on 12/4/2017.
 */

public class TaxFileFragment extends Fragment {


    private View view;
    private Context context;

    public TaxFileFragment (Context context) {
        this.context = context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_taxfile_fragment,container,false);
        ViewPager viewPager = (ViewPager)view.findViewById(R.id.taxFile_viewPager);
        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.taxfile_tablayout);
        TaxFileViewPagerAdapter adapter = new TaxFileViewPagerAdapter(getResources(),getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        changeTabFont(context,tabLayout);
        return view;
    }

    private static void changeTabFont(Context context,TabLayout tabLayout) {
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(FontsHandeller.setFont(context,"sans_med"));
                }
            }
        }
    }



}
