package com.skyland.zimuzutv.zimuzutv.MVP.Home.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.skyland.zimuzutv.zimuzutv.MVP.Home.HomeActivity;

/**
 * Created by skyland on 2016/12/1.
 */

public class FragmentController {

    private int currentItem;
    private FragmentManager fm;
    private ViewPager viewPager;
    private static FragmentController controller;

    public static FragmentController getInstance(FragmentActivity activity,int currentItem,ViewPager viewPager){
        if(controller == null){
            controller = new FragmentController(activity,currentItem,viewPager);
        }
        return controller;
    }

    public static void onDestroy(){
        controller = null;
    }

    private FragmentController(FragmentActivity activity, int currentItem,ViewPager viewPager) {
        this.currentItem = currentItem;
        fm = activity.getSupportFragmentManager();
        this.viewPager = viewPager;
        initFragment();
    }

    private void initFragment(){
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int arg0) {
                if (arg0 == 0) {
                    return new HomeNewsFragment();
                } else if (arg0 == 1) {
                    return new HomeCaptionsFragment();
                } else if (arg0 == 2){
                    return new HomeTimetableFragment();
                } else if (arg0 == 3){
                    return new HomeFilmsStoreFragment();
                } else {
                    return new HomeNewsFragment();
                }
            }
            @Override
            public int getCount() {
                return 4;
            }
        };
        viewPager.setAdapter(adapter);

    }


}
