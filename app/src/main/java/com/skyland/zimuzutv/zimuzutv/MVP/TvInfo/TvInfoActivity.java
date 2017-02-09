package com.skyland.zimuzutv.zimuzutv.MVP.TvInfo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.skyland.zimuzutv.zimuzutv.Constant.Constant;
import com.skyland.zimuzutv.zimuzutv.Data.Api.Api;
import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.ViewPagerAdapter;
import com.skyland.zimuzutv.zimuzutv.MVP.Base.BaseActivity;
import com.skyland.zimuzutv.zimuzutv.MVP.Base.BaseSwipeBackActivity;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.prevue;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TvDetialDto;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.fragment.TvInfoCaptionsFragment;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.fragment.TvInfoContentFragment;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.fragment.TvInfoPrevueFragment;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.fragment.TvInfoResourceFragment;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.presenter.TvInfoPresenter;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.view.TvInfoView;
import com.skyland.zimuzutv.zimuzutv.MyApplication.MyApplication;
import com.skyland.zimuzutv.zimuzutv.R;
import com.skyland.zimuzutv.zimuzutv.Util.CommomUtil;
import com.skyland.zimuzutv.zimuzutv.Widget.ProgressActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skyland on 2016/12/9.
 */

public class TvInfoActivity extends BaseSwipeBackActivity implements TvInfoView {
    private static final String TAG = "TvInfoActivity";
    private TvInfoPresenter mPresenter;
    private ViewPagerAdapter viewPagerAdapter;

    private TvInfoContentFragment contentFragment;
    private TvInfoPrevueFragment prevueFragment;
    private TvInfoResourceFragment resourceFragment;
    private TvInfoCaptionsFragment captionsFragment;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private ProgressActivity progressActivity;

    private ImageView ivBack;
    private ImageView ivShare;
    private ImageView ivPoster;
    private TextView tvToolBarTitle;
    private TextView tvCname;
    private TextView tvEname;
    private TextView tvArea;
    private TextView tvState;
    private TextView tvType;
    private TextView tvScan;
    private TextView tvExplain;
    private TextView tvTMtype;

    private String tvid;
    private String[] tabTitleArray = {"简介", "播出时间", "资源下载"};
    private List<prevue> prevueList = new ArrayList<prevue>();
    private List<ResourceListDto> resourceList = new ArrayList<ResourceListDto>();
    private int prevueCount = 0;
    //share
    private String shareTitle;
    private String shareContent;
    private String shareImage;
    private String shareUrl ;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_tvdetial);
    }

    @Override
    protected void findViewById() {
        //获取Intent传的值
        Intent intent = getIntent();
        tvid = intent.getStringExtra("com.skyland.zimuzu.tvid");
        //findviewbyid
        tabLayout = (TabLayout) findViewById(R.id.tvinfo_tab);
        viewPager = (ViewPager) findViewById(R.id.tvinfo_viewpager);
        toolbar = (Toolbar) findViewById(R.id.tvinfo_toolbar);
        progressActivity = (ProgressActivity) findViewById(R.id.tvinfo_progress);

        ivBack = (ImageView) findViewById(R.id.tvinfo_btnback);
        ivShare = (ImageView) findViewById(R.id.tvinfo_ivshare);
        ivPoster = (ImageView) findViewById(R.id.tvinfo_iv_poster);
        tvToolBarTitle = (TextView) findViewById(R.id.tvinfo_toolbar_tvtitle);
        tvCname = (TextView) findViewById(R.id.tvinfo_tv_cname);
        tvEname = (TextView) findViewById(R.id.tvinfo_tv_ename);
        tvArea = (TextView) findViewById(R.id.tvinfo_tv_area);
        tvState = (TextView) findViewById(R.id.tvinfo_tv_state);
        tvType = (TextView) findViewById(R.id.tvinfo_tv_type);
        tvScan = (TextView) findViewById(R.id.tvinfo_tv_scan);
        tvExplain = (TextView) findViewById(R.id.tvinfo_tv_explain);
        tvTMtype = (TextView) findViewById(R.id.tvinfo_tv_tvtype);
    }

    @Override
    protected void setListener() {
        ivBack.setOnClickListener(this);
        ivShare.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {
        //
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);     //设置layout布局可以在状态栏里面显示
        if (SdkApi()) {
            ViewGroup.LayoutParams lp = toolbar.getLayoutParams();
            lp.width = ViewPager.LayoutParams.MATCH_PARENT;
            lp.height = CommomUtil.dip2px(this, 46) + getStatusBarHeight();
            toolbar.setLayoutParams(lp);

            toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {   //6.0以上设置图标反色
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        //获取数据
        mPresenter = new TvInfoPresenter(this);
        String timestamp = Api.getTimestamp();
        String key = Api.getAccessKey(timestamp);
        mPresenter.LoadTvInfo(true, Constant.API_CID, key, timestamp, tvid, 1, 1);
        mPresenter.LoadResourceListData(true, Constant.API_CID, key, timestamp, tvid, 0);
        //给tablayout添加数据
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvinfo_btnback:
                scrollToFinishActivity();   //调用swipebacklayout的库返回
                break;
            case R.id.tvinfo_ivshare:
                showShare();
                break;
        }
    }
//点击事件
    private void showShare(){
        Log.d(TAG, "showShare: title" + shareTitle);
        Log.d(TAG, "showShare: content" + shareContent);
        Log.d(TAG, "showShare: image" + shareImage);
        Log.d(TAG, "showShare: url" + shareUrl);
        /*
        ShareSDK.initSDK(getActivityContext());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(getActivityContext());
        */
    }

    @Override
    public void tvInfoData(TvDetialDto tvDetialDto, List<ResourceListDto> resourceListDtos) {
        initView(tvDetialDto, resourceListDtos);
    }

    @Override
    public void showProgress() {
        progressActivity.showLoading();
    }

    @Override
    public void hideProgress() {
        progressActivity.showContent();
    }

    @Override
    public void showLoadFailMsg() {
        progressActivity.showError(getResources().getDrawable(R.drawable.ic_load_error),
                "提示",
                "没有网络",
                "错误页面", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //重试按钮点击事件

                    }
                });
    }

    public void initView(TvDetialDto data, List<ResourceListDto> resourceListDtos) {
        Log.d(TAG, "initView: data" + data.getCnname());
        if(data.getPrevue()!=null){
            prevueCount = data.getPrevue().size();
            prevueList = data.getPrevue();
            Log.d(TAG, "initView: data" + String.valueOf(data.getPrevue().size()));
        }
        Log.d(TAG, "initView: resourceListDtos" + String.valueOf(resourceListDtos.size()));
        Glide.with(MyApplication.getInstance())
                .load(data.getPoster())
                .crossFade()
//                .placeholder(R.mipmap.no_tv_poster)
                .into((ivPoster));
        tvToolBarTitle.setText("影视详情");
        tvCname.setText(data.getCnname());
        tvEname.setText(data.getEnname());
        tvArea.setText(data.getArea());
        tvState.setText(data.getPlay_status());
        tvType.setText(data.getCategory());
        tvScan.setText(data.getViews());
        tvExplain.setText(data.getRemark());
        String channel = "";
        switch (data.getChannel()){
            case "movie":
                channel = "电影";
                break;
            case "tv":
                channel = "电视剧";
                break;
            case "openclass":
                channel = "公开课";
                break;
        }
        tvTMtype.setText(channel);
//share
        shareTitle = data.getShareTitle();
        shareContent = data.getShareContent();
        shareImage = data.getShareImage();
        shareUrl = data.getShareUrl();
//init viewpager
        initViewPager(viewPager,data.getContent(), resourceListDtos);
    }

    private void initViewPager(ViewPager viewPager,String content, List<ResourceListDto> resourceListDtos) {

        resourceList = resourceListDtos;
        Log.d(TAG, "initView: initViewPager" + String.valueOf(resourceListDtos.size()));

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        contentFragment =  new TvInfoContentFragment();
        prevueFragment = new TvInfoPrevueFragment();
        resourceFragment = new TvInfoResourceFragment();
        captionsFragment = new TvInfoCaptionsFragment();
        viewPager.setOffscreenPageLimit(4);

        Bundle contentBundle = new Bundle();
        contentBundle.putString("com.skyland.zimuzutv.content",content);
        contentFragment.setArguments(contentBundle);
        viewPagerAdapter.addFragment(contentFragment, "简介");

        Bundle captionsBundle = new Bundle();
        captionsBundle.putParcelableArrayList("com.skyland.zimuzutv.tvList",(ArrayList<? extends Parcelable>) resourceList);
        captionsFragment.setArguments(captionsBundle);
        viewPagerAdapter.addFragment(captionsFragment, "HR-HDTV");

        Bundle resourceBundle = new Bundle();
        resourceBundle.putParcelableArrayList("com.skyland.zimuzutv.tvList",(ArrayList<? extends Parcelable>) resourceList);
        resourceFragment.setArguments(resourceBundle);
        viewPagerAdapter.addFragment(resourceFragment, "720P/HDTV");

        Bundle prevueBundle = new Bundle();
        prevueBundle.putInt("com.skyland.zimuzutv.prevueCount",prevueCount);
        prevueBundle.putParcelableArrayList("com.skyland.zimuzutv.prevueList",(ArrayList<? extends Parcelable>) prevueList);
        prevueFragment.setArguments(prevueBundle);
        viewPagerAdapter.addFragment(prevueFragment, "时间(" + prevueCount + ")");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }



}
