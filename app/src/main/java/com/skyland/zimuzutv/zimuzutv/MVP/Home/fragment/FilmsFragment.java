package com.skyland.zimuzutv.zimuzutv.MVP.Home.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.skyland.zimuzutv.zimuzutv.Constant.Constant;
import com.skyland.zimuzutv.zimuzutv.Data.Api.Api;
import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.FilmFrgAdapter;
import com.skyland.zimuzutv.zimuzutv.MVP.Base.BaseFragment;
import com.skyland.zimuzutv.zimuzutv.MVP.Base.LazyFragment;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.FilmsListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.FilmsResultDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.presenter.HomeFilmsFragmentPresenter;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.view.HomeFilmsFragmentView;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.TvInfoActivity;
import com.skyland.zimuzutv.zimuzutv.R;
import com.skyland.zimuzutv.zimuzutv.Widget.ProgressActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by skyland on 2017/2/19.
 */

public class FilmsFragment extends LazyFragment implements HomeFilmsFragmentView {
    private static final String TAG = "FilmsFragment";
    private int tabPosition = 0;

    //懒加载
    private boolean isPrepared;
    private boolean isFirstLoad;

    private static final int FILM_TAB_ALL = 0;
    private static final int FILM_TAB_AMERICAN = 1;
    private static final int FILM_TAB_ENGLAND = 2;
    private static final int FILM_TAB_MOVIE= 3;
    private static final int FILM_TAB_TV= 4;
    private static final int FILM_TAB_OPENCLASS= 5;
    private static final int FILM_TAB_JILUPIAN= 6;

    private int  page = 0;

    private HomeFilmsFragmentPresenter mPresenter;
    private List<FilmsListDto> filmsList = new ArrayList<>();

    private ProgressActivity progress;

    //列表
    private RecyclerView recycleView;
    private LinearLayoutManager layoutManager;
    private FilmFrgAdapter adapter;

    public FilmsFragment(int tabPosition){
        this.tabPosition = tabPosition;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        progress = (ProgressActivity) rootView.findViewById(R.id.fragment_film_progress);

        recycleView = (RecyclerView) rootView.findViewById(R.id.fragment_films_recyclerview);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new FilmFrgAdapter(filmsList);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(adapter);

        isPrepared = true;
        isFirstLoad = true;
        mPresenter = new HomeFilmsFragmentPresenter(this);
        initData();
        return rootView;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_films,container,false);
    }

    @Override
    protected void initListener() {
        recycleView.addOnItemTouchListener(new OnItemClickListener(){

            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                Intent intent = new Intent(getActivity(), TvInfoActivity.class);
                intent.putExtra("com.skyland.zimuzu.tvid",filmsList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        if(isPrepared && isVisible && isFirstLoad) {
            isFirstLoad = false;
            switch (tabPosition){
                case FILM_TAB_ALL:
                    loadDate("", "", "", "", "",20,1);
                    break;
                case FILM_TAB_AMERICAN:
                    page ++;
                    loadDate("tv", "美国", "", "", "",20,1);
                    break;
                case FILM_TAB_ENGLAND:
                    loadDate("tv", "英国", "", "", "",20,1);
                    break;
                case FILM_TAB_MOVIE:
                    loadDate("movie", "", "", "", "",20,1);
                    break;
                case FILM_TAB_TV:
                    loadDate("tv", "", "", "", "",20,1);
                    break;
                case FILM_TAB_OPENCLASS:
                    loadDate("openclass", "", "", "", "",20,1);
                    break;
                case FILM_TAB_JILUPIAN:
                    loadDate("", "", "", "", "",20,1);
                    break;
            }
            Log.d(TAG, "initData: " + String.valueOf(tabPosition) + "page" + String.valueOf(page));
        }
    }

    private void loadDate( String channel, String area, String sort, String year, String category,
                           int limit, int page){
        String timestamp = Api.getTimestamp();
        String key = Api.getAccessKey(timestamp);
        mPresenter.loadFilmsList(true, Constant.API_CID, key, timestamp, channel, area, sort, year,category,limit,page);
    }


    @Override
    public void showProgress() {
        progress.showLoading();
    }

    @Override
    public void hideProgress() {
        progress.showContent();
    }

    @Override
    public void loadFilmsList(FilmsResultDto data) {
        filmsList = data.getList();
        adapter.addData(filmsList);
        adapter.notifyDataSetChanged();
        for(int i=0; i<filmsList.size(); i++){
            Log.d("films", "loadFilmsList: " + String.valueOf(tabPosition) + filmsList.get(i).getCnname());
        }
    }

    @Override
    public void showLoadFailMsg() {
        progress.showError(getResources().getDrawable(R.drawable.ic_load_error),
                "提示",
                "没有网络",
                "重试", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
    }
}
