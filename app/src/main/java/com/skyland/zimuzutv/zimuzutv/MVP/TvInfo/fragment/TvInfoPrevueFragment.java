package com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.TvInfoPrevueAdapter;
import com.skyland.zimuzutv.zimuzutv.MVP.Base.BaseFragment;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.prevue;
import com.skyland.zimuzutv.zimuzutv.R;
import com.skyland.zimuzutv.zimuzutv.Widget.ProgressActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skyland on 2016/12/17.
 */

public class TvInfoPrevueFragment extends BaseFragment{
    private final String TAG =  "TvInfoPrevueFragment";

    private ProgressActivity prevueProgress;
    private RecyclerView recyclerView;

    private TvInfoPrevueAdapter adapter;

    private List<prevue> prevueList = new ArrayList<prevue>();
    private int prevueCount;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_tvinfo_prevue,container,false);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        prevueList = bundle.getParcelableArrayList("com.skyland.zimuzutv.prevueList");
        prevueCount = bundle.getInt("com.skyland.zimuzutv.prevueCount");
        for(int i = 0;i < prevueCount; i++){
            Log.d(TAG, "initData: prevueList" + prevueList.get(i).getPlay_time());
        }
        Log.d(TAG, "initData: prevueCount"+String.valueOf(prevueCount));
        if(prevueCount == 0){
            prevueProgress.showEmpty(getResources().getDrawable(R.drawable.png_data_empty),"没有找到播出安排=_=","");
        }else if(prevueCount > 0){
            LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);//设置list横向布局
            adapter = new TvInfoPrevueAdapter(getActivity(),prevueList);
            recyclerView.setAdapter(adapter);
            prevueProgress.showContent();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        prevueProgress = (ProgressActivity) rootView.findViewById(R.id.tvinfo_prevue_progress);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.tvinfo_prevue_recyclerview);

        return rootView;
    }
}
