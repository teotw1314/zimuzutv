package com.skyland.zimuzutv.zimuzutv.MVP.Home.fragment;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.skyland.zimuzutv.zimuzutv.Data.Api.Api;
import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.HomeAllAdapter;
import com.skyland.zimuzutv.zimuzutv.MVP.Base.BaseFragment;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.BannerDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.NewsInfoListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.SectionDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TvInfoListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.presenter.HomeNewsFragmentPresenter;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.view.HomeNewsFragmentView;
import com.skyland.zimuzutv.zimuzutv.MVP.NewsInfo.NewsInfoActivity;
import com.skyland.zimuzutv.zimuzutv.MVP.Test.TestActivity;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.TvInfoActivity;
import com.skyland.zimuzutv.zimuzutv.R;
import com.skyland.zimuzutv.zimuzutv.Widget.ProgressActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by skyland on 2016/12/1.
 */

public class HomeNewsFragment extends BaseFragment implements HomeNewsFragmentView, SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG = "HomeNewsFragment";
    private int newsPage;
    private int isLoaded = 0;
    private boolean isRefresh = false;



    ProgressActivity homeNewsProgress;
    SwipeRefreshLayout swipeRefresh;
    BGABanner bgaBanner;
    RecyclerView homeAllRv;
    HomeAllAdapter homeAllAdapter;
    View bannerView;



    private HomeNewsFragmentPresenter mPresenter;


    private List<SectionDto> section1List = new ArrayList<>();
    private List<SectionDto> section2List = new ArrayList<>();
    public static List<BannerDto> homeBannerList;
    public static List<String> bannerTitle = new ArrayList<String>();
    public static List<String> bannerImage = new ArrayList<String>();

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        Log.d(TAG, "initView: ");
        return inflater.inflate(R.layout.fragment_home_news,container,false);

    }

    @Override
    protected void initListener() {
        //下拉刷新、上拉加载
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeResources(R.color.colorBlue, R.color.colorPrimary, R.color.colorBlack);

        //Banner点击事件回调
        homeAllAdapter.setBannerCallBack(new HomeAllAdapter.BannerCallBack() {
            @Override
            public void onItemClick(BannerDto bannerDto) {
                Intent intent = new Intent(getActivity(), NewsInfoActivity.class);
                intent.putExtra("com.skyland.zimuzu.newsid",bannerDto.getId());
                startActivity(intent);
            }
        });

        //section点击回调事件
        homeAllAdapter.setSectionCallBack(new HomeAllAdapter.SectionCallBack() {
            @Override
            public void onItemClick(SectionDto sectionDto) {
                Toast.makeText(getActivity(), sectionDto.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), TestActivity.class);
                startActivity(intent);
            }
        });

        //hot tv点击回调事件
        homeAllAdapter.setHotTvCallBack(new HomeAllAdapter.HotTvCallBack() {
            @Override
            public void onItemClick(TvInfoListDto tvInfoListDto) {
                //Toast.makeText(getActivity(), tvInfoListDto.getCnname(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), TvInfoActivity.class);
                intent.putExtra("com.skyland.zimuzu.tvid",tvInfoListDto.getId());
                startActivity(intent);
            }
        });

        //news点击回调事件
        homeAllAdapter.setNewsCallBack(new HomeAllAdapter.NewsCallBack() {
            @Override
            public void onItemClick(NewsInfoListDto newsInfoListDto) {
                Intent intent = new Intent(getActivity(), NewsInfoActivity.class);
                intent.putExtra("com.skyland.zimuzu.newsid",newsInfoListDto.getId());
                startActivity(intent);
            }
        });

    }


    @Override
    protected void initData() {
        isLoaded++;

        if(isLoaded > 1){   //加载过一次之后每次加载先清除原本的数据，防止数据重复
            section1List.clear();
            section2List.clear();
        }
        SectionDto section1 = new SectionDto();
        section1.setName("今日热门");
        section1List.add(section1);

        SectionDto section2 = new SectionDto();
        section2.setName("影视资讯");
        section2List.add(section2);

        mPresenter = new HomeNewsFragmentPresenter(this);
        newsPage = 1;
        loadHomeDatas(false);

    }

    @Override
    public void showProgress() {
        homeNewsProgress.showLoading();
    }
    @Override
    public void hideProgress() {
        homeNewsProgress.showContent();
    }
    @Override
    public void showLoadFailMsg() {
        if(isRefresh){
            swipeRefresh.setRefreshing(false);
            isRefresh = false;
        }
        homeNewsProgress.showError(getResources().getDrawable(R.drawable.ic_load_error),
                "提示",
                "没有网络",
                "重试", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        isRefresh = true;
                        swipeRefresh.setRefreshing(true);
                        loadHomeDatas(true);
                    }
                });

    }

    @Override
    public void homeAllDatas(List<BannerDto> bannerList,List<TvInfoListDto> hotList, List<NewsInfoListDto> newsList) {

        if(isLoaded > 1){
            homeBannerList.clear();
        }
        bannerTitle.clear();
        bannerImage.clear();
        int size = bannerList.size();
        for (int i = 0; i < size; i++) {
            bannerTitle.add(bannerList.get(i).getTitle());
            bannerImage.add(bannerList.get(i).getPoster());
            Log.d(TAG, "homeAllDatas: bannerImage" + String.valueOf(bannerList.get(i).getPoster()));
        }
        homeBannerList = bannerList;
        homeAllAdapter.addList(section1List,hotList,section2List,newsList);
        homeAllAdapter.notifyDataSetChanged();
        if(isRefresh){
            Toast.makeText(getContext(), "影视资讯刷新成功", Toast.LENGTH_SHORT).show();
            isRefresh = false;
            swipeRefresh.setRefreshing(false);
        }

    }

    @Override
    public void startTvDetialActivity(String id) {
        Intent intent = new Intent(getActivity(), TvInfoActivity.class);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        homeNewsProgress = (ProgressActivity) rootView.findViewById(R.id.home_news_progress);
        swipeRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.home_news_swipelayout);
        //typeBannerViewHolder = new TypeBannerViewHolder(rootView);

        bannerView = inflater.inflate(R.layout.multi_typeview_headview,container,false);

        bgaBanner = (BGABanner) bannerView.findViewById(R.id.multi_headview_banner);

        homeAllRv = (RecyclerView) rootView.findViewById(R.id.home_news_rv);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                int type = homeAllRv.getAdapter().getItemViewType(position);
                if (type == HomeAllAdapter.TYPE_HEAD){
                    return gridLayoutManager.getSpanCount();
                } else if(type == HomeAllAdapter.TYPE_SECTION1){
                    return gridLayoutManager.getSpanCount();
                }else if(type == HomeAllAdapter.TYPE_NEWS){
                    return 2;
                }else if(type == HomeAllAdapter.TYPE_SECTION2){
                    return gridLayoutManager.getSpanCount();
                }else{
                    return 1;
                }
            }
        });
        homeAllRv.setLayoutManager(gridLayoutManager);
        homeAllAdapter = new HomeAllAdapter(getContext(),bannerView);
        homeAllRv.setAdapter(homeAllAdapter);

        return rootView;
    }

    @Override
    public void rvChangeNotify() {
        homeAllAdapter.notifyDataSetChanged();
    }

    /*
    //上啦加载
    @Override
    public void onLoadmore() {
        String timestamp = Api.getTimestamp();
        String key = Api.getAccessKey(timestamp);
        Log.d(TAG, "onLoadmore: "+timestamp+key);
        newsPage ++;
        mPresenter.LoadNewsListData(true,"11",key,timestamp,"news",6,newsPage);
    }
    */
    /*
    @Override
    public void nextNewsListDatas(List<NewsInfoListDto> list){
        //newsList = mPresenter.newsList;
        for (int i = 0; i < list.size(); i++){
            Log.d(TAG, "newsListDatas: "+list.get(i).getId());
        }
    }
    */

    //加载首页数据
    private void loadHomeDatas(boolean isLoaded){
        String timestamp = Api.getTimestamp();
        String key = Api.getAccessKey(timestamp);
        Log.d(TAG, "initData: "+timestamp+key);
        mPresenter.LoadBannerData(isLoaded,"11",key,timestamp,"news",4,1);
        mPresenter.LoadTodayHotData(isLoaded,"11",key,timestamp,8,1);
        mPresenter.LoadNewsListData(isLoaded,"11",key,timestamp,12,1);
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        if(!isRefresh){
            isRefresh = true;
            loadHomeDatas(true);
        }

    }
}
