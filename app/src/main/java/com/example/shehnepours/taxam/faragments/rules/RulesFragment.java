package com.example.shehnepours.taxam.faragments.rules;

import android.app.Activity;
import android.content.Context;
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
import com.example.shehnepours.taxam.adapter.RulesViewPagerAdapter;
import com.example.shehnepours.taxam.adapter.TaxFileViewPagerAdapter;
import com.example.shehnepours.taxam.utils.FontsHandeller;

/**
 * Created by shehnepour.s on 2/26/2018.
 */

public class RulesFragment extends Fragment {

    private View view;
    private Context context;


    public RulesFragment (Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_rules_viewpager,container,false);
        ViewPager viewPager = (ViewPager)view.findViewById(R.id.rules_viewPager);
        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.rules_tablayout);
        RulesViewPagerAdapter adapter = new RulesViewPagerAdapter(getResources(),getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        changeTabFont(context,tabLayout);
        return view;
    }

    private static void changeTabFont(Context context, TabLayout tabLayout) {
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
