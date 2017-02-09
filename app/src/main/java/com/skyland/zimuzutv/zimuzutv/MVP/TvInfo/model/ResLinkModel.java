package com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.model;

import com.skyland.zimuzutv.zimuzutv.Data.HttpData.HttpData;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceLinkListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.model.*;

import java.util.List;

import rx.Observer;

/**
 * Created by skyland on 2016/12/20.
 */

public class ResLinkModel {

    public void loadResLinkListData(boolean isLoad, String cid, String accesskey, String timestamp, String id, final com.skyland.zimuzutv.zimuzutv.MVP.Home.model.OnLoadDataListListener listener){
        HttpData.getInstance().getResourceLinkList(isLoad, cid, accesskey, timestamp, id, new Observer<List<ResourceLinkListDto>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(List<ResourceLinkListDto> resourceLinkListDtos) {
                listener.onSuccess(resourceLinkListDtos);
            }
        });
    }
}
