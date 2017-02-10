package com.skyland.zimuzutv.zimuzutv.MVP.Home.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skyland.zimuzutv.zimuzutv.Constant.Constant;
import com.skyland.zimuzutv.zimuzutv.Data.Api.Api;
import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.HomeSubtitleAdapter;
import com.skyland.zimuzutv.zimuzutv.MVP.Base.BaseFragment;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TimeTableListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.presenter.HomeSubtitleFragmentPresenter;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.presenter.HomeTimeTableFragmentPresenter;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.view.HomeTimeTableFragmentView;
import com.skyland.zimuzutv.zimuzutv.R;
import com.skyland.zimuzutv.zimuzutv.Widget.ProgressActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by skyland on 2016/12/1.
 */

public class HomeTimetableFragment extends BaseFragment implements HomeTimeTableFragmentView{

    private static final String TAG = "HomeTimetableFragment";
    private HomeTimeTableFragmentPresenter mPresenter;

    private String start = "2017-02-09";
    private String end = "2017-02-10";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        return rootView;
    }
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home_timetable,container,false);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter = new HomeTimeTableFragmentPresenter(this);
        String timestamp = Api.getTimestamp();
        String key = Api.getAccessKey(timestamp);
        mPresenter.loadTimeTableList(true, Constant.API_CID, key, timestamp, start, end);
    }

    @Override
    public void loadList(Map<String, List<Map<String, String>>> mapData) {
        Log.d(TAG, "loadList: " + "sfjeifje");
        List<TimeTableListDto> listData = new ArrayList<TimeTableListDto>();
        listData = getListData("2017-02-10",mapData);
        for (int i = 0; i < listData.size(); i++){
            Log.d(TAG, "loadList: " + listData.get(i).getCnname());
        }


    }

    /*
     *datetime 2017-02-10
     */
    private List<TimeTableListDto> getListData(String datetime, Map<String, List<Map<String, String>>> mapData){
        List<TimeTableListDto> listData = new ArrayList<TimeTableListDto>();
        List<Map<String, String>> listMap = mapData.get(datetime);
        int size = listMap.size();
        for(int i = 0; i < size; i++){
            TimeTableListDto dto = new TimeTableListDto();
            Map<String, String> map = listMap.get(i);
            dto.setId(map.get("id"));
            dto.setCnname(map.get("cnname"));
            dto.setEnname(map.get("enname"));
            dto.setPoster(map.get("poster"));
            dto.setSeason(map.get("season"));
            dto.setEpisode(map.get("episode"));
            listData.add(dto);
        }
        return listData;
    }

}
