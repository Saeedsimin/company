package com.example.shehnepours.taxam.adapter;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.example.shehnepours.taxam.faragments.rules.directRules.DirectRulesFirstPageFragment;
import com.example.shehnepours.taxam.faragments.rules.directRules.DirectRulesFrameLayout;
import com.example.shehnepours.taxam.faragments.rules.searchRules.SearchRulesFirstPageFragment;
import com.example.shehnepours.taxam.faragments.rules.searchRules.SearchRulesFrameLayout;

/**
 * Created by shehnepour.s on 2/26/2018.
 */

public class RulesViewPagerAdapter extends FragmentPagerAdapter {
    private final Resources resources;
    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();


    public RulesViewPagerAdapter(final Resources resources,FragmentManager fm) {
        super(fm);
        this.resources = resources;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return new DirectRulesFrameLayout();
        }
        if(position == 1) {
            return new SearchRulesFirstPageFragment();
        }
        return new DirectRulesFrameLayout();
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "قوانین و بخشنامه‌ها";
            case 1:
                return "جستجو";
            default:
                return "";
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }
    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}
