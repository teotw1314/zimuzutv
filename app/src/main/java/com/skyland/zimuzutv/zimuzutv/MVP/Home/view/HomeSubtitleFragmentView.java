package com.skyland.zimuzutv.zimuzutv.MVP.Home.view;

import com.skyland.zimuzutv.zimuzutv.MVP.Entity.SubtitleResultDto;

/**
 * Created by skyland on 2016/12/29.
 */

public interface HomeSubtitleFragmentView {

    //显示加载页
    void showProgress();
    //关闭加载页
    void hideProgress();

    void loadSubtitleList(SubtitleResultDto data);


    //显示加载失败
    void showLoadFailMsg();

}
