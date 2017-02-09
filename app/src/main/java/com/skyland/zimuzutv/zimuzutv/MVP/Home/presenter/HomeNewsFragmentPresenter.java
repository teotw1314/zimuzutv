package com.skyland.zimuzutv.zimuzutv.MVP.Home.presenter;



import android.util.Log;

import com.skyland.zimuzutv.zimuzutv.MVP.Entity.BannerDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.NewsInfoListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TvInfoListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.model.HomeNewsFragmentModdel;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.model.OnLoadDataListListener;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.view.HomeNewsFragmentView;



import java.util.ArrayList;
import java.util.List;


/**
 * Created by skyland on 2016/12/1.
 */

public class HomeNewsFragmentPresenter{
    private static final String TAG = "NewsFragmentPresenter";

    private HomeNewsFragmentView mView;
    private HomeNewsFragmentModdel mModel;

    private List<BannerDto> bannerList = new ArrayList<>();
    private List<TvInfoListDto> hotList = new ArrayList<>();
    private List<NewsInfoListDto> newsList = new ArrayList<>();

    private int successFlag = 0;
    private static final int apiCount = 3;
    private int newsPage;

    public HomeNewsFragmentPresenter(HomeNewsFragmentView mView){
        this.mView = mView;
        this.mModel = new HomeNewsFragmentModdel();
        mView.showProgress();
    }


    public void LoadBannerData(boolean isLoad, String cid, String accesskey, String timestamp, String newstype, int limit, int page){
        mModel.loadBannerData(isLoad, cid, accesskey, timestamp, newstype, limit, page, LoadBannerDataListener);

    }
    OnLoadDataListListener<List<BannerDto>> LoadBannerDataListener = new OnLoadDataListListener<List<BannerDto>>() {
        @Override
        public void onSuccess(List<BannerDto> data) {
            bannerList = data;
            successFlag ++;
            Log.d(TAG, "onSuccess: LoadBannerData"+String.valueOf(successFlag)+String.valueOf(apiCount));
            if(successFlag >= apiCount){
                successFlag = 0;
                mView.homeAllDatas(bannerList,hotList,newsList);
               // mView.rvChangeNotify();
                mView.hideProgress();
            }
        }

        @Override
        public void onFailure(Throwable e) {
            mView.showLoadFailMsg();
        }
    };



    public void LoadTodayHotData(boolean isLoad, String cid, String accesskey, String timestamp, int limit, int page){
        mModel.loadTodayHotData(isLoad, cid, accesskey, timestamp, limit, page, LoadTodayHotDataListener);
    }
    OnLoadDataListListener<List<TvInfoListDto>> LoadTodayHotDataListener = new OnLoadDataListListener<List<TvInfoListDto>>() {
        @Override
        public void onSuccess(List<TvInfoListDto> data) {
            hotList = data;
            successFlag ++;
            Log.d(TAG, "onSuccess: LoadTodayHotData"+String.valueOf(successFlag)+String.valueOf(apiCount));
            if(successFlag >= apiCount){
                successFlag = 0;
                mView.homeAllDatas(bannerList,hotList,newsList);
               // mView.rvChangeNotify();
                mView.hideProgress();
            }
        }
        @Override
        public void onFailure(Throwable e) {
             mView.showLoadFailMsg();
        }
    };


    public void LoadNewsListData(boolean isLoad, String cid, String accesskey, String timestamp, int limit, int page){
        mModel.loadNewsListData(isLoad, cid, accesskey, timestamp, limit, page, LoadNewsListDataListener);
        newsPage = page;
    }
    OnLoadDataListListener<List<NewsInfoListDto>> LoadNewsListDataListener = new OnLoadDataListListener<List<NewsInfoListDto>>() {
        @Override
        public void onSuccess(List<NewsInfoListDto> dataNews) {
                newsList = dataNews;
                successFlag ++;
                Log.d(TAG, "onSuccess: LoadNewsListData"+String.valueOf(successFlag)+String.valueOf(apiCount));
                if(successFlag >= apiCount){
                    successFlag = 0;
                    mView.homeAllDatas(bannerList,hotList,newsList);
                  //  mView.rvChangeNotify();
                    mView.hideProgress();
                }
        }
        @Override
        public void onFailure(Throwable e) {
            mView.showLoadFailMsg();
        }
    };





//end
}
