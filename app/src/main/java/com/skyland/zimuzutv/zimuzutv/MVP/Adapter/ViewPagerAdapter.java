package com.skyland.zimuzutv.zimuzutv.MVP.Adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;


import java.util.ArrayList;
import java.util.List;

import static android.R.attr.fragment;

/**
 * Created by skyland on 2016/12/17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> fragmentTitles = new ArrayList<>();
    private Context context;

    public ViewPagerAdapter (FragmentManager fm, Context context){
        super(fm);
        this.context = context;
    }

    public void addFragment (Fragment fragment, String title){
        fragments.add(fragment);
        fragmentTitles.add(title);
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
