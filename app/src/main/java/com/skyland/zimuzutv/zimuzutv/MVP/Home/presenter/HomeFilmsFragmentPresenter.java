package com.skyland.zimuzutv.zimuzutv.MVP.Home.presenter;

import com.skyland.zimuzutv.zimuzutv.MVP.Entity.FilmsResultDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.model.HomeFilmsFragmentModel;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.model.OnLoadDataListListener;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.view.HomeFilmsFragmentView;

/**
 * Created by skyland on 2017/2/19.
 */

public class HomeFilmsFragmentPresenter {

    private HomeFilmsFragmentModel mModel;
    private HomeFilmsFragmentView mView;

    public HomeFilmsFragmentPresenter(HomeFilmsFragmentView mView){
        this.mView = mView;
        this.mModel = new HomeFilmsFragmentModel();
    }

    public void loadFilmsList(boolean isLoad, String cid, String accesskey, String timestamp,
                              String channel, String area, String sort, String year, String category,
                              int limit, int page){
        mModel.loadFilmsList(isLoad, cid, accesskey, timestamp, channel, area, sort, year, category,
                limit, page, new OnLoadDataListListener<FilmsResultDto>() {
                    @Override
                    public void onSuccess(FilmsResultDto data) {
                        mView.loadFilmsList(data);
                    }

                    @Override
                    public void onFailure(Throwable e) {

                    }
                });

    }


}
