package com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.presenter;

import android.util.Log;

import com.skyland.zimuzutv.zimuzutv.Data.HttpData.HttpData;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TvDetialDto;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.model.OnLoadDataListListener;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.model.TvInfoModel;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.view.TvInfoView;

import java.util.List;

import rx.Observer;

/**
 * Created by skyland on 2016/12/17.
 */

public class TvInfoPresenter {
    private static final String TAG = "TvInfoPresenter";

    private TvInfoModel mModel;
    private TvInfoView mView;

    private TvDetialDto tvDetialDto;
    private List<ResourceListDto> resourceListDtos;

    private int successCount = 0;

    public TvInfoPresenter(TvInfoView mView){
        this.mView = mView;
        this.mModel = new TvInfoModel();
        mView.showProgress();
    }

    public void LoadTvInfo(boolean isLoad, String cid, String accesskey, String timestamp, String id, int prevue, int share){
        mModel.loadTvInfoData(isLoad, cid, accesskey, timestamp, id, prevue, share, new OnLoadDataListListener<TvDetialDto>() {
            @Override
            public void onSuccess(TvDetialDto data) {
                successCount ++;
                tvDetialDto = data;
                if(successCount>=2){
                    successCount = 0;
                    mView.tvInfoData(tvDetialDto,resourceListDtos);
                    mView.hideProgress();
                }
            }

            @Override
            public void onFailure(Throwable e) {
                Log.d(TAG, "onFailure: "+e.toString());
                mView.showLoadFailMsg();
            }
        });
    }

    public void LoadResourceListData(boolean isLoad, String cid, String accesskey, String timestamp, String id, int file){
        mModel.loadResourceListData(isLoad, cid, accesskey, timestamp, id, file, new com.skyland.zimuzutv.zimuzutv.MVP.Home.model.OnLoadDataListListener<List<ResourceListDto>>() {
            @Override
            public void onSuccess(List<ResourceListDto> resourceData) {
                resourceListDtos = resourceData;
                successCount ++;
                if(successCount>=2){
                    successCount = 0;
                    mView.tvInfoData(tvDetialDto,resourceListDtos);
                    mView.hideProgress();
                }

            }

            @Override
            public void onFailure(Throwable e) {
                Log.d(TAG, "onFailure: "+e.toString());
                mView.showLoadFailMsg();
            }
        });
    }


}
