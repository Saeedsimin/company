package com.example.shehnepours.taxam.adapter;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.example.shehnepours.taxam.faragments.taxFileFragments.NewTaxFileFrameLayout;
import com.example.shehnepours.taxam.faragments.taxFileFragments.TaxFileArchiveFragment;

/**
 * Created by shehnepour.s on 12/5/2017.
 */

public class TaxFileViewPagerAdapter extends FragmentPagerAdapter {
    private final Resources resources;
    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();


    public TaxFileViewPagerAdapter(final Resources resources,FragmentManager fm) {
        super(fm);
        this.resources = resources;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return new NewTaxFileFrameLayout();
        }
        else {
            return new TaxFileArchiveFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "پرونده جاری";
            case 1:
                return "آرشیو پرونده‌ها";
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
