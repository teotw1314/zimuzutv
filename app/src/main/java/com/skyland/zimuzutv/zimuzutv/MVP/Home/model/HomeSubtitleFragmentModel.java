package com.skyland.zimuzutv.zimuzutv.MVP.Home.model;

import com.skyland.zimuzutv.zimuzutv.Data.HttpData.HttpData;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.SubtitleResultDto;

import rx.Observer;

/**
 * Created by skyland on 2016/12/29.
 */

public class HomeSubtitleFragmentModel {
    private final String TAG = "HomeSubtitleFragmentModel";

    public void loadSubtitleList(boolean isLoad, String cid, String accesskey, String timestamp, final int limit, int page,
                                 final OnLoadDataListListener listener){
        HttpData.getInstance().getSubtitleList(isLoad, cid, accesskey, timestamp, limit, page,
                new Observer<SubtitleResultDto>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(SubtitleResultDto data) {
                listener.onSuccess(data);
            }
        });

    }

}
