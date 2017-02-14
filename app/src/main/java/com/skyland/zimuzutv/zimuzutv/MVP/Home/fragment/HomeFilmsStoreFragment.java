package com.skyland.zimuzutv.zimuzutv.MVP.Home.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skyland.zimuzutv.zimuzutv.MVP.Base.LazyFragment;
import com.skyland.zimuzutv.zimuzutv.R;

/**
 * Created by skyland on 2016/12/1.
 */

public class HomeFilmsStoreFragment extends LazyFragment {

    private boolean isPrepared;
    private boolean isFirstLoad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

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
            Log.d("initdate", "initData: 4");
        }
    }

    ///
    public void fabFilmsClick(){

    }
}
