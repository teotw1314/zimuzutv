package com.skyland.zimuzutv.zimuzutv.MVP.NewsInfo.view;

import com.skyland.zimuzutv.zimuzutv.MVP.Entity.NewsInfoDto;

/**
 * Created by skyland on 2016/12/23.
 */

public interface NewsInfoView {
    void loadNewsInfo(NewsInfoDto data);

    void showProgress();

    void hideProgress();

    void showError();
}
