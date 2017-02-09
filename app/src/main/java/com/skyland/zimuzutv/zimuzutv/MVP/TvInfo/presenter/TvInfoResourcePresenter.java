package com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.presenter;

import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.model.OnLoadDataListListener;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.model.TvInfoResourceModel;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.view.TvInfoResourceView;

import java.util.List;

/**
 * Created by skyland on 2016/12/18.
 */

public class TvInfoResourcePresenter {

    private TvInfoResourceModel mModel;
    private TvInfoResourceView mView;

    private List<ResourceListDto> data;

    public TvInfoResourcePresenter(TvInfoResourceView view){
        this.mModel = new TvInfoResourceModel();
        this.mView = view;
    }

    public void LoadResourceListData(boolean isLoad, String cid, String accesskey, String timestamp, String id, int file){
        mModel.loadResourceListData(isLoad, cid, accesskey, timestamp, id, file, new OnLoadDataListListener<List<ResourceListDto>>() {
            @Override
            public void onSuccess(List<ResourceListDto> data) {
                mView.loadTvInfoResourceList(data);
            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
    }

}
