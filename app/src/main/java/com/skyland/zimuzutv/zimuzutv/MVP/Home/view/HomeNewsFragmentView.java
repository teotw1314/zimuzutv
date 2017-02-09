package com.skyland.zimuzutv.zimuzutv.MVP.Home.view;

import com.skyland.zimuzutv.zimuzutv.MVP.Entity.BannerDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.HomeDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.NewsInfoListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TvInfoListDto;

import java.util.List;

/**
 * Created by skyland on 2016/12/1.
 */

public interface HomeNewsFragmentView {
    //显示加载页
    void showProgress();
    //关闭加载页
    void hideProgress();

    //加载新数据
   // void bannerDatas(List<BannerDto> list);
    void homeAllDatas(List<BannerDto> bannerList,List<TvInfoListDto> hotList,List<NewsInfoListDto> newsList);
    void rvChangeNotify();
    void startTvDetialActivity(String id);
   // void nextNewsListDatas(List<NewsInfoListDto> list);



    //显示加载失败
    void showLoadFailMsg();
}
