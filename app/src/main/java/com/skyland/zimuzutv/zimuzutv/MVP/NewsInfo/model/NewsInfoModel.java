package com.skyland.zimuzutv.zimuzutv.MVP.NewsInfo.model;

import com.skyland.zimuzutv.zimuzutv.Data.HttpData.HttpData;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.NewsInfoDto;

import rx.Observer;

/**
 * Created by skyland on 2016/12/23.
 */

public class NewsInfoModel {

    public void loadNewsInfo(boolean isLoad, String cid, String accesskey, String timestamp, String id, final OnLoadDataListListener listener){
        HttpData.getInstance().getNewsInfo(isLoad, cid, accesskey, timestamp, id, new Observer<NewsInfoDto>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(NewsInfoDto newsInfoDto) {
                listener.onSuccess(newsInfoDto);
            }
        });

    }
}
