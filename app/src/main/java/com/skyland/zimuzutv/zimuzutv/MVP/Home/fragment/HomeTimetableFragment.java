package com.skyland.zimuzutv.zimuzutv.MVP.Home.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import java.util.List;
import java.util.Map;

/**
 * Created by skyland on 2016/12/1.
 */

public class HomeTimetableFragment extends BaseFragment implements HomeTimeTableFragmentView{

    private static final String TAG = "HomeTimetableFragment";
    private HomeTimeTableFragmentPresenter mPresenter;

    private String start = "2017-01-06";
    private String end = "2017-01-06";

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
       // mPresenter.loadTimeTableList(true, Constant.API_CID, key, timestamp, start, end);
    }

    @Override
    public void loadList(Map.Entry<String, List<TimeTableListDto>> mapData) {
        Log.d(TAG, "loadList: " + String.valueOf("") );
    }
}
