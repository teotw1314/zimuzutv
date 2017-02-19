package com.skyland.zimuzutv.zimuzutv.MVP.Home.view;

import com.skyland.zimuzutv.zimuzutv.MVP.Entity.FilmsResultDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.SubtitleResultDto;

/**
 * Created by skyland on 2017/2/19.
 */

public interface HomeFilmsFragmentView {

    //显示加载页
    void showProgress();
    //关闭加载页
    void hideProgress();

    void loadFilmsList(FilmsResultDto data);

    //显示加载失败
    void showLoadFailMsg();
}
