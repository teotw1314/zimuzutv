package com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.view;

import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TvDetialDto;

import java.util.List;

/**
 * Created by skyland on 2016/12/17.
 */

public interface TvInfoView {
    void tvInfoData(TvDetialDto tvDetialDto, List<ResourceListDto> data);
   // void resData(List<ResourceListDto> data);

    //void loadTvInfoResourceList();

    //显示加载页
    void showProgress();
    //关闭加载页
    void hideProgress();
    //显示加载失败
    void showLoadFailMsg();
}
