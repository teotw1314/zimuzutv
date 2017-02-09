package com.skyland.zimuzutv.zimuzutv.MVP.NewsInfo;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.skyland.zimuzutv.zimuzutv.Constant.Constant;
import com.skyland.zimuzutv.zimuzutv.Data.Api.Api;
import com.skyland.zimuzutv.zimuzutv.MVP.Base.BaseActivity;
import com.skyland.zimuzutv.zimuzutv.MVP.Base.BaseSwipeBackActivity;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.NewsInfoDto;
import com.skyland.zimuzutv.zimuzutv.MVP.NewsInfo.presenter.NewsInfoPresenter;
import com.skyland.zimuzutv.zimuzutv.MVP.NewsInfo.view.NewsInfoView;
import com.skyland.zimuzutv.zimuzutv.MyApplication.MyApplication;
import com.skyland.zimuzutv.zimuzutv.R;
import com.skyland.zimuzutv.zimuzutv.RichText.RichText;
import com.skyland.zimuzutv.zimuzutv.Util.CommomUtil;
import com.skyland.zimuzutv.zimuzutv.Util.MyImageGetter;
import com.skyland.zimuzutv.zimuzutv.Widget.AspectRatioImageView;
import com.skyland.zimuzutv.zimuzutv.Widget.ProgressActivity;

import java.util.List;

/**
 * Created by skyland on 2016/12/23.
 */

public class NewsInfoActivity extends BaseSwipeBackActivity implements NewsInfoView{

    private NewsInfoPresenter mPresenter;

    private ProgressActivity progressActivity;
    private Toolbar toolbar;
    private AspectRatioImageView ivPoster;
    private TextView tvTitle;
    private TextView tvDateline;
    private ImageView ivBack;
    private RichText tvContent;


    private String id;


    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_newsinfo2);
    }

    @Override
    protected void findViewById() {
        progressActivity = (ProgressActivity) findViewById(R.id.newsinfo2_progress);
        toolbar = (Toolbar) findViewById(R.id.newsinfo2_toolbar);
        ivBack = (ImageView) findViewById(R.id.newsinfo2_ivback);
        ivPoster = (AspectRatioImageView) findViewById(R.id.newsinfo2_iv_poster);
        tvTitle = (TextView) findViewById(R.id.newsinfo_title);
        tvDateline = (TextView) findViewById(R.id.newsinfo_dateline);
        tvContent = (RichText) findViewById(R.id.newsinfo2_content);
    }

    @Override
    protected void setListener() {
        //图片点击响应事件
        tvContent.setOnRichTextImageClickListener(new RichText.OnRichTextImageClickListener() {
            @Override
            public void imageClicked(List<String> imageUrls, int position) {
                Toast.makeText(NewsInfoActivity.this, imageUrls.get(position),Toast.LENGTH_SHORT).show();
            }
        });
        ivBack.setOnClickListener(this);

    }

    @Override
    protected void processLogic() {
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
        Intent intent = getIntent();
        id = intent.getStringExtra("com.skyland.zimuzu.newsid");

        mPresenter = new NewsInfoPresenter(this);
        String timestamp = Api.getTimestamp();
        String key = Api.getAccessKey(timestamp);
        mPresenter.LoadNewsInfo(true, Constant.API_CID, key, timestamp, id);

    }

    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.newsinfo2_ivback:
                scrollToFinishActivity();   //调用swipebacklayout的库返回
                break;
        }
    }



    @Override
    public void loadNewsInfo(NewsInfoDto data) {
        Glide.with(MyApplication.getInstance())
                .load(data.getPoster())
                .crossFade()
//                .placeholder(R.mipmap.no_tv_poster)
                .into((ivPoster));
        tvTitle.setText(data.getTitle());
        tvDateline.setText(CommomUtil.getStandardDate(data.getDateline()));
        tvContent.setRichText(data.getContent());
        //tvContent.setText(Html.fromHtml(data.getContent(), new MyImageGetter(this,tvContent),null));
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
    public void showError() {
        progressActivity.showError(getResources().getDrawable(R.drawable.ic_load_error),
                "提示",
                "没有网络",
                "重试", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
    }

}
