package com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.presenter;

import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceLinkListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.model.OnLoadDataListListener;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.model.ResLinkModel;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.view.ResLinkView;

import java.util.List;

/**
 * Created by skyland on 2016/12/20.
 */

public class ResLinkPresenter {

    private ResLinkView mView;
    private ResLinkModel mModel;

    public ResLinkPresenter (ResLinkView mView){
        this.mView = mView;
        this.mModel = new ResLinkModel();
        mView.showProgress();
    }

    public void LoadResLinkListData(boolean isLoad, String cid, String accesskey, String timestamp, String id){
        mModel.loadResLinkListData(isLoad, cid, accesskey, timestamp, id, new OnLoadDataListListener<List<ResourceLinkListDto>>() {
            @Override
            public void onSuccess(List<ResourceLinkListDto> data) {
                mView.loadResLinkData(data);
                mView.hideProgress();
            }

            @Override
            public void onFailure(Throwable e) {
                mView.showLoadFailMsg();
            }
        });
    }


}
