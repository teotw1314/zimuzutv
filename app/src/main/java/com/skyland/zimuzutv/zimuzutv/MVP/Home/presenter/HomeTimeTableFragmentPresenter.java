package com.skyland.zimuzutv.zimuzutv.MVP.Home.presenter;

import android.util.Log;

import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TimeTableListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.model.HomeTimeTableFragmentModel;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.model.OnLoadDataListListener;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.view.HomeSubtitleFragmentView;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.view.HomeTimeTableFragmentView;

import java.util.List;
import java.util.Map;

/**
 * Created by skyland on 2017/1/6.
 */

public class HomeTimeTableFragmentPresenter {

    private static final String TAG = "TimeTablePresenter";

    private HomeTimeTableFragmentModel mModel;
    private HomeTimeTableFragmentView mView;

    public HomeTimeTableFragmentPresenter(HomeTimeTableFragmentView mView){
        this.mModel = new HomeTimeTableFragmentModel();
        this.mView = mView;
    }

    public void loadTimeTableList(boolean isLoad, String cid, String accesskey, String timestamp, String start, String end){
        mModel.getTimeTableList(isLoad, cid, accesskey, timestamp, start, end, new OnLoadDataListListener< Map<String, List<Map<String, String>>> >() {
            @Override
            public void onSuccess( Map<String, List<Map<String, String>>> data) {
                mView.loadList(data);
            }

            @Override
            public void onFailure(Throwable e) {
                Log.d(TAG, "onFailure: " + e.toString());
            }
        });

    }


}
