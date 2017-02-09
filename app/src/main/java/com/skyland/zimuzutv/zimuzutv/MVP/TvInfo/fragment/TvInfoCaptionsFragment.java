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

import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.TvInfoResourceAdapter;
import com.skyland.zimuzutv.zimuzutv.MVP.Base.BaseFragment;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.ResLinkActivity;
import com.skyland.zimuzutv.zimuzutv.R;
import com.skyland.zimuzutv.zimuzutv.Widget.ProgressActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skyland on 2016/12/19.
 */

public class TvInfoCaptionsFragment extends BaseFragment{
    private final String TAG = "TvInfoCaptionsFragment";
    private ProgressActivity progressActivity;
    private RecyclerView recyclerView;

    private TvInfoResourceAdapter adapter;
    private List<ResourceListDto> resourceList = new ArrayList<ResourceListDto>();
    private List<ResourceListDto> captionsList = new ArrayList<ResourceListDto>();
    private String tvid;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_tvinfo_captions,container,false);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

        Bundle bundle = getArguments();
        if(bundle != null){
            resourceList = bundle.getParcelableArrayList("com.skyland.zimuzutv.tvList");
        }
        int size = resourceList.size();
        if( size > 0){
            for (int i = size-1; i >= 0; i--){
                if (resourceList.get(i).getName().contains("字幕") && Integer.parseInt(resourceList.get(i).getSeason() ) < 30){
                    ResourceListDto captions = new ResourceListDto();
                    captions.setId(resourceList.get(i).getId());
                    captions.setName(resourceList.get(i).getName());
                    captions.setSeason(resourceList.get(i).getSeason());
                    captions.setEpisode(resourceList.get(i).getEpisode());
                    captions.setFormat(resourceList.get(i).getFormat());
                    captions.setSize(resourceList.get(i).getSize());
                    captionsList.add(captions);
                }
            }
        }
        if(captionsList.size() == 0 ){
            progressActivity.showEmpty(getResources().getDrawable(R.drawable.png_data_empty),"资源哪里去了？=_=","");
        }else {
            progressActivity.showContent();
        }
        // Log.d(TAG, "initData: "+String.valueOf(resourceList.size()));
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);//设置list横向布局
        adapter = new TvInfoResourceAdapter(getContext(),captionsList);
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(new TvInfoResourceAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                ResourceListDto data = captionsList.get(position);
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
        recyclerView = (RecyclerView) rootView.findViewById(R.id.tvinfo_captions_recyclerview);
        progressActivity = (ProgressActivity) rootView.findViewById(R.id.tvinfo_captions_progress);
        return rootView;
    }

}
