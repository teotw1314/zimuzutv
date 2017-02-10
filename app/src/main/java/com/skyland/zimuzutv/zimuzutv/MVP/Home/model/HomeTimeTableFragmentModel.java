package com.skyland.zimuzutv.zimuzutv.MVP.Home.model;

import com.skyland.zimuzutv.zimuzutv.Data.HttpData.HttpData;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TimeTableListDto;

import java.util.List;
import java.util.Map;

import rx.Observer;

/**
 * Created by skyland on 2017/1/6.
 */

public class HomeTimeTableFragmentModel {

    public void getTimeTableList(boolean isLoad, String cid, String accesskey, String timestamp, String start, String end,
                                 final OnLoadDataListListener listener){
        HttpData.getInstance().getTimeTableList(isLoad, cid, accesskey, timestamp, start, end, new Observer< Map<String, List<Map<String, String>>> >() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(Map<String, List<Map<String, String>>>  stringListMap) {
                listener.onSuccess(stringListMap);
            }
        });
    }

}
