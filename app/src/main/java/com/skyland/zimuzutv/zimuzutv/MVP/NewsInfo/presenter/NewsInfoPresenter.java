package com.skyland.zimuzutv.zimuzutv.MVP.NewsInfo.presenter;

import com.skyland.zimuzutv.zimuzutv.MVP.Entity.NewsInfoDto;
import com.skyland.zimuzutv.zimuzutv.MVP.NewsInfo.model.NewsInfoModel;
import com.skyland.zimuzutv.zimuzutv.MVP.NewsInfo.model.OnLoadDataListListener;
import com.skyland.zimuzutv.zimuzutv.MVP.NewsInfo.view.NewsInfoView;

/**
 * Created by skyland on 2016/12/23.
 */

public class NewsInfoPresenter {
    private NewsInfoView mView;
    private NewsInfoModel mModel;

    public NewsInfoPresenter(NewsInfoView mView){
        this.mView = mView;
        this.mModel = new NewsInfoModel();
        mView.showProgress();
    }

    public void LoadNewsInfo(boolean isLoad, String cid, String accesskey, String timestamp, String id){
        mModel.loadNewsInfo(isLoad, cid, accesskey, timestamp, id, new OnLoadDataListListener<NewsInfoDto>() {
            @Override
            public void onSuccess(NewsInfoDto data) {
                mView.loadNewsInfo(data);
                mView.hideProgress();
            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
    }

}
