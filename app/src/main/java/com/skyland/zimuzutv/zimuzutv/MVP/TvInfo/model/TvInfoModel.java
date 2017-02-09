package com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.model;

import com.skyland.zimuzutv.zimuzutv.Data.HttpData.HttpData;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.NewsInfoListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TvDetialDto;

import java.util.List;

import rx.Observer;

/**
 * Created by skyland on 2016/12/17.
 */

public class TvInfoModel {

    public static final String TAG = "TvInfoModel";

    public void loadTvInfoData(boolean isLoad, String cid, String accesskey, String timestamp, String id, int prevue, int share, final OnLoadDataListListener listener){
        HttpData.getInstance().getTvDetial(isLoad, cid, accesskey, timestamp, id, prevue, share, new Observer<TvDetialDto>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(TvDetialDto tvDetialDto) {
                listener.onSuccess(tvDetialDto);
            }
        });

    }

    public void loadResourceListData(boolean isLoad, String cid, String accesskey, String timestamp, String id, int file, final com.skyland.zimuzutv.zimuzutv.MVP.Home.model.OnLoadDataListListener listener){
        HttpData.getInstance().getResourceList(isLoad, cid, accesskey, timestamp, id, file, new Observer<List<ResourceListDto>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(List<ResourceListDto> data) {
                listener.onSuccess(data);
            }
        });
    }


}
