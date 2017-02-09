package com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.skyland.zimuzutv.zimuzutv.Constant.Constant;
import com.skyland.zimuzutv.zimuzutv.Data.Api.Api;
import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.TvInfoResourceAdapter;
import com.skyland.zimuzutv.zimuzutv.MVP.Base.BaseFragment;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.prevue;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.ResLinkActivity;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.presenter.TvInfoResourcePresenter;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.view.TvInfoResourceView;
import com.skyland.zimuzutv.zimuzutv.R;
import com.skyland.zimuzutv.zimuzutv.Widget.ProgressActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skyland on 2016/12/17.
 */

public class TvInfoResourceFragment extends BaseFragment{
    private final String TAG = "TvInfoResourceFragment";

    private ProgressActivity progressActivity;
    private RecyclerView recyclerView;
    private TvInfoResourceAdapter adapter;

    private List<ResourceListDto> resourceList = new ArrayList<ResourceListDto>();
    private List<ResourceListDto> tvList = new ArrayList<ResourceListDto>();

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_tvinfo_resource,container,false);
    }

    @Override
    protected void initListener() {


    }

    @Override
    protected void initData() {

        Bundle bundle = getArguments();
        if(bundle != null){
            Log.d(TAG, "initData: faejfjewoi");
            resourceList = bundle.getParcelableArrayList("com.skyland.zimuzutv.tvList");
        }
        int size = resourceList.size();
        if( size > 0){
            for (int i = size-1; i >= 0; i--){
                if (!resourceList.get(i).getName().contains("字幕") && Integer.parseInt(resourceList.get(i).getSeason() ) < 30){
                    ResourceListDto tv = new ResourceListDto();
                    tv.setId(resourceList.get(i).getId());
                    tv.setName(resourceList.get(i).getName());
                    tv.setSeason(resourceList.get(i).getSeason());
                    tv.setEpisode(resourceList.get(i).getEpisode());
                    tv.setFormat(resourceList.get(i).getFormat());
                    tv.setSize(resourceList.get(i).getSize());
                    tvList.add(tv);
                }

            }
        }

        if(tvList.size() == 0){
            progressActivity.showEmpty(getResources().getDrawable(R.drawable.png_data_empty),"资源哪里去了？=_=","");
        }else{
            progressActivity.showContent();
        }
// Log.d(TAG, "initData: "+String.valueOf(resourceList.size()));
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);//设置list横向布局
        adapter = new TvInfoResourceAdapter(getContext(),tvList);
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(new TvInfoResourceAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                ResourceListDto data = tvList.get(position);
                String season = "第" + data.getSeason() + "季第" + data.getEpisode() + "集";
                Intent resIntent = new Intent(getActivity(), ResLinkActivity.class);
                Bundle resBundle = new Bundle();
                resBundle.putString("com.skyladn.zimuzutv.resId",data.getId());
                resBundle.putString("com.skyladn.zimuzutv.resName",data.getName());
                resBundle.putString("com.skyladn.zimuzutv.resSeason",season);
                resBundle.putString("com.skyladn.zimuzutv.resType",data.getFormat());
                resBundle.putString("com.skyladn.zimuzutv.resSize",data.getSize());
                resIntent.putExtras(resBundle);
                startActivity(resIntent);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.tvinfo_resource_recyclerview);
        progressActivity = (ProgressActivity) rootView.findViewById(R.id.tvinfo_resource_progress);
        return rootView;
    }
}
