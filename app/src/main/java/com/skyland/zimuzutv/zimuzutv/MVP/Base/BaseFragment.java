package com.skyland.zimuzutv.zimuzutv.MVP.Base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by skyland on 2016/12/1.
 */

public abstract class BaseFragment extends Fragment {

    protected boolean isVisible;//是否可见
    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        mRootView = initView(inflater,container);
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListener();
        initData();
    }
    protected abstract View initView(LayoutInflater inflater, ViewGroup container);
    protected abstract void initListener();
    protected abstract void initData();
}
