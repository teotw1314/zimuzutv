package com.skyland.zimuzutv.zimuzutv.MVP.Home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.skyland.zimuzutv.zimuzutv.Constant.Constant;
import com.skyland.zimuzutv.zimuzutv.Data.Api.Api;
import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.HomeSubtitleAdapter;
import com.skyland.zimuzutv.zimuzutv.MVP.Base.BaseFragment;
import com.skyland.zimuzutv.zimuzutv.MVP.Base.LazyFragment;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.SubtitleListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.SubtitleResInfoDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.SubtitleResultDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.HomeActivity;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.eventbus.HomeFabAniEvent;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.eventbus.HomeFabClickEvent;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.presenter.HomeSubtitleFragmentPresenter;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.view.HomeSubtitleFragmentView;
import com.skyland.zimuzutv.zimuzutv.R;
import com.skyland.zimuzutv.zimuzutv.Widget.ProgressActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skyland on 2016/12/1.
 */

public class HomeCaptionsFragment extends LazyFragment implements HomeSubtitleFragmentView, SwipeRefreshLayout.OnRefreshListener {

    private final String TAG = "HomeCaptionsFragment";
    private HomeSubtitleFragmentPresenter mPresenter;

    private HomeSubtitleAdapter adapter;

    private SwipeRefreshLayout refreshLayout;
    private ProgressActivity progress;
    private RecyclerView recyclerview;
    private LinearLayoutManager layoutManager;

    private boolean isRefresh = false;
    private boolean isLoadMore = false;
    private int limit = 12;
    private int page = 1;
    private int lastVisibleItem;
    private List<SubtitleListDto> subtitleList = new ArrayList<>();

    private boolean isPrepared;
    private boolean isFirstLoad;
    private boolean isLoading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        EventBus.getDefault().register(this);
        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.home_subtitle_refreshlayout);
        progress = (ProgressActivity) rootView.findViewById(R.id.home_subtitle_progress);
        recyclerview = (RecyclerView) rootView.findViewById(R.id.home_subtitle_recyclerview);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new HomeSubtitleAdapter(getActivity());
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adapter);

        progress.showLoading();
        isLoading = true;

        isPrepared = true;
        isFirstLoad = true;
        initData();

        return rootView;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home_captions, container, false);
    }

    @Override
    protected void initListener() {
        adapter.setClickListener(new HomeSubtitleAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, SubtitleListDto data) {
                Toast.makeText(getActivity(), data.getCnname(), Toast.LENGTH_SHORT).show();
            }
        });
        refreshLayout.setOnRefreshListener(this);

        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    if(!isLoadMore){
                        isLoadMore = true;
                        page ++ ;
                        Toast.makeText(getActivity(), "正在加载。。。" + String.valueOf(page), Toast.LENGTH_SHORT).show();
                        loadData(true, page);
                    }
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });

    }

    @Override
    protected void initData() {
        if(isPrepared && isVisible && isFirstLoad) {
            isFirstLoad = false;
            Log.d(TAG, "initData: 2");
            mPresenter = new HomeSubtitleFragmentPresenter(this);
            loadData(false, 1);
        }
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
    public void loadSubtitleList(SubtitleResultDto data) {
        if(isLoading){
            progress.showContent();
        }
        if(page == 1){
            subtitleList = data.getList();
            adapter.addList(subtitleList);
            adapter.notifyDataSetChanged();
        }else{
            if(isLoadMore){
                isLoadMore = false;
                adapter.addMoreItem(data.getList());
            }
        }

        if (isRefresh) {
            Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
            isRefresh = false;
            refreshLayout.setRefreshing(false);
            EventBus.getDefault().post(new HomeFabAniEvent(1));
        }
    }

    @Override
    public void showLoadFailMsg() {
        if (isRefresh) {
            isRefresh = false;
            refreshLayout.setRefreshing(false);
        }
        progress.showError(getResources().getDrawable(R.drawable.ic_load_error),
                "提示",
                "没有网络",
                "重试", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isRefresh) {
                            isRefresh = true;
                            refreshLayout.setRefreshing(true);
                            loadData(true, 1);
                        }
                    }
                });

    }



    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh: start");
        if (!isRefresh) {
            page = 1;
            Log.d(TAG, "onRefresh: false,start re");
            isRefresh = true;
            loadData(true, 1);
        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void fabCaptionsClickEvent(HomeFabClickEvent event){
        Log.d("event__", "fabCaptionsClickEvent: 1");
        if(event.tabPositions == 1){
            if (!isRefresh) {
                refreshLayout.setRefreshing(true);
                page = 1;
                Log.d(TAG, "onRefresh: false,start re");
                isRefresh = true;
                loadData(true, 1);
            }
        }
    }

    private void loadData(boolean isLoad, int pageNum) {
        String timestamp = Api.getTimestamp();
        String key = Api.getAccessKey(timestamp);
        mPresenter.loadSubtitleList(isLoad, Constant.API_CID, key, timestamp, limit, pageNum);
    }



    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
