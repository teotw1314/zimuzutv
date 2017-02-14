package com.skyland.zimuzutv.zimuzutv.MVP.Home.presenter;

import android.view.View;

import com.skyland.zimuzutv.zimuzutv.MVP.Entity.SubtitleResultDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.model.HomeSubtitleFragmentModel;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.model.OnLoadDataListListener;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.view.HomeSubtitleFragmentView;

/**
 * Created by skyland on 2016/12/29.
 */

public class HomeSubtitleFragmentPresenter {

    private HomeSubtitleFragmentModel mModel;
    private HomeSubtitleFragmentView mView;

    public HomeSubtitleFragmentPresenter(HomeSubtitleFragmentView mView){
        this.mView = mView;
        mModel = new HomeSubtitleFragmentModel();
       // mView.showProgress();
    }

    public void loadSubtitleList(boolean isLoad, String cid, String accesskey, String timestamp, final int limit, int page){
        mModel.loadSubtitleList(isLoad, cid, accesskey, timestamp, limit, page, new OnLoadDataListListener<SubtitleResultDto>() {
            @Override
            public void onSuccess(SubtitleResultDto data) {
                mView.loadSubtitleList(data);
               // mView.hideProgress();
            }

            @Override
            public void onFailure(Throwable e) {
                mView.showLoadFailMsg();
            }
        });
    }


}
