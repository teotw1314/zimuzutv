package com.skyland.zimuzutv.zimuzutv.MVP.Home.model;

import com.skyland.zimuzutv.zimuzutv.Data.HttpData.HttpData;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.FilmsResultDto;

import rx.Observer;

/**
 * Created by skyland on 2017/2/19.
 */

public class HomeFilmsFragmentModel {

    public void loadFilmsList(boolean isLoad, String cid, String accesskey, String timestamp,
                              String channel, String area, String sort, String year, String category,
                              int limit, int page, final OnLoadDataListListener listener){
        HttpData.getInstance().getFilmsList(isLoad, cid, accesskey, timestamp, channel, area,
                sort, year, category, limit, page, new Observer<FilmsResultDto>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(e);
                    }
                    @Override
                    public void onNext(FilmsResultDto films) {
                        listener.onSuccess(films);
                    }
                });

    }

}
