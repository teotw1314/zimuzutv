package com.skyland.zimuzutv.zimuzutv.MVP.Home.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skyland.zimuzutv.zimuzutv.Constant.Constant;
import com.skyland.zimuzutv.zimuzutv.Data.Api.Api;
import com.skyland.zimuzutv.zimuzutv.MVP.Base.LazyFragment;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.FilmsListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.FilmsResultDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.presenter.HomeFilmsFragmentPresenter;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.view.HomeFilmsFragmentView;
import com.skyland.zimuzutv.zimuzutv.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skyland on 2016/12/1.
 */

public class HomeFilmsStoreFragment extends LazyFragment {


    private ViewPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    //懒加载
    private boolean isPrepared;
    private boolean isFirstLoad;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        mViewPager = (ViewPager) rootView.findViewById(R.id.home_films_viewpager);
        mTabLayout = (TabLayout) rootView.findViewById(R.id.home_films_tab);
        mAdapter = new ViewPagerAdapter(getFragmentManager());
        mViewPager.setOffscreenPageLimit(mAdapter.getCount());
        isPrepared = true;
        isFirstLoad = true;
        initData();
        return rootView;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home_filmstore,container,false);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        if(isPrepared && isVisible && isFirstLoad) {
            isFirstLoad = false;
            mViewPager.setAdapter(mAdapter);
            mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            mTabLayout.setupWithViewPager(mViewPager);
        }
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return new FilmsFragment(position);
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "全部";
                case 1:
                    return "美剧";
                case 2:
                    return "英剧";
                case 3:
                    return "电影";
                case 4:
                    return "电视剧";
                case 5:
                    return "公开课";
                case 6:
                    return "纪录片";

            }
            return null;
        }
    }




}
