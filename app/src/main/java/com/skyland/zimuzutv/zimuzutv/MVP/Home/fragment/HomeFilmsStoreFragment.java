package com.skyland.zimuzutv.zimuzutv.MVP.Home.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skyland.zimuzutv.zimuzutv.Constant.Constant;
import com.skyland.zimuzutv.zimuzutv.Data.Api.Api;
import com.skyland.zimuzutv.zimuzutv.MVP.Base.LazyFragment;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.FilmsListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.FilmsResultDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.presenter.HomeFilmsFragmentPresenter;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.view.HomeFilmsFragmentView;
import com.skyland.zimuzutv.zimuzutv.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skyland on 2016/12/1.
 */

public class HomeFilmsStoreFragment extends LazyFragment implements HomeFilmsFragmentView {

    private HomeFilmsFragmentPresenter mPresenter;


    private List<FilmsListDto> filmsList = new ArrayList<>();

    private boolean isPrepared;
    private boolean isFirstLoad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        mPresenter = new HomeFilmsFragmentPresenter(this);
        isPrepared = true;
        isFirstLoad = true;
        initData();

        return rootView;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home_filmstore,container,false);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        if(isPrepared && isVisible && isFirstLoad) {
            isFirstLoad = false;
            loadDate("", "", "", "", "",20,1);
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadFilmsList(FilmsResultDto data) {
        filmsList = data.getList();
        for(int i=0; i<filmsList.size(); i++){
            Log.d("films", "loadFilmsList: " + filmsList.get(i).getCnname());
        }
    }

    @Override
    public void showLoadFailMsg() {

    }


    ///
    private void loadDate( String channel, String area, String sort, String year, String category,
                           int limit, int page){
        String timestamp = Api.getTimestamp();
        String key = Api.getAccessKey(timestamp);
        mPresenter.loadFilmsList(true, Constant.API_CID, key, timestamp, channel, area, sort, year,category,limit,page);
    }

    public void fabFilmsClick(){

    }




}
