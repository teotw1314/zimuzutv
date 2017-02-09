package com.skyland.zimuzutv.zimuzutv.MVP.Home.model;

import android.util.Log;

import com.skyland.zimuzutv.zimuzutv.Data.HttpData.HttpData;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.BannerDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.NewsInfoListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TvInfoListDto;

import java.util.List;

import rx.Observer;

/**
 * Created by skyland on 2016/12/1.
 */

public class HomeNewsFragmentModdel {
    private static final String TAG = "HomeNewsFragmentModdel";

    public void loadBannerData(boolean isLoad, String cid, String accesskey, String timestamp, String newstype, int limit, int page,
                         final OnLoadDataListListener listener){
        HttpData.getInstance().getBannerList(isLoad, cid, accesskey, timestamp, newstype, limit, page,
                new Observer<List<BannerDto>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: getBannerList");
                        listener.onFailure(e);
                    }

                    @Override
                    public void onNext(List<BannerDto> bannerDtos) {
                        Log.d(TAG, "onNext: getBannerList");
                        listener.onSuccess(bannerDtos);
                    }
                });
    }

    public void loadTodayHotData(boolean isLoad,String cid, String accesskey, String timestamp, int limit, int page,
                                 final OnLoadDataListListener listener){
        HttpData.getInstance().getTvList(isLoad,cid, accesskey, timestamp, limit, page, new Observer<List<TvInfoListDto>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: getTvList");
                listener.onFailure(e);
            }

            @Override
            public void onNext(List<TvInfoListDto> tvInfoListDtos) {
                Log.d(TAG, "onNext: getTvList");
                listener.onSuccess(tvInfoListDtos);
            }
        });
    }

    public void loadNewsListData(boolean isLoad,String cid, String accesskey, String timestamp, int limit, int page,
                               final OnLoadDataListListener listener){
        HttpData.getInstance().getNewsList(isLoad,cid, accesskey, timestamp, limit, page,
                new Observer<List<NewsInfoListDto>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError:loadNewsListData ");
                        Log.d(TAG, "onError: "+e);
                        listener.onFailure(e);
                    }

                    @Override
                    public void onNext(List<NewsInfoListDto> newsInfoListDtos) {
                        Log.d(TAG, "onNext: loadNewsListData");
                        listener.onSuccess(newsInfoListDtos);
                    }
                });
    }

}
