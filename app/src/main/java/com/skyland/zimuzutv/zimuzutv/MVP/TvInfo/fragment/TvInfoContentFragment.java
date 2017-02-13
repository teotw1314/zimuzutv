package com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skyland.zimuzutv.zimuzutv.MVP.Base.BaseFragment;
import com.skyland.zimuzutv.zimuzutv.R;

/**
 * Created by skyland on 2016/12/17.
 */

public class TvInfoContentFragment extends BaseFragment{
    private TextView tvContent;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_tvinfo_content,container,false);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Log.d("TvInfoContentFragment", "initData: content");
        Bundle bundle = getArguments();
        String content = bundle.getString("com.skyland.zimuzutv.content");
        tvContent.setText("\u3000\u3000" + content);    //前面部分为首行缩进两个字符
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        tvContent = (TextView) rootView.findViewById(R.id.tvinfo_tv_content);

        return rootView;
    }
}
