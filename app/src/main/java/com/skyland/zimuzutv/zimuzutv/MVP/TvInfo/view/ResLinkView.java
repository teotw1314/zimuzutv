package com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.view;

import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceLinkListDto;

import java.util.List;

/**
 * Created by skyland on 2016/12/20.
 */

public interface ResLinkView {
    void loadResLinkData(List<ResourceLinkListDto> data);

    //显示加载页
    void showProgress();
    //关闭加载页
    void hideProgress();
    //显示加载失败
    void showLoadFailMsg();
}
