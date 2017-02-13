package com.skyland.zimuzutv.zimuzutv.MVP.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by skyland on 2017/2/13.
 */

public class HomeFragmentAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragments;

    public HomeFragmentAdapter(FragmentManager fm, List<Fragment> fragments){
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int fragment) {
        return fragments.get(fragment);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
