package com.skyland.zimuzutv.zimuzutv.MVP.TvInfo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.skyland.zimuzutv.zimuzutv.Constant.Constant;
import com.skyland.zimuzutv.zimuzutv.Data.Api.Api;
import com.skyland.zimuzutv.zimuzutv.MVP.Base.BaseActivity;
import com.skyland.zimuzutv.zimuzutv.MVP.Base.BaseSwipeBackActivity;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceLinkListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.presenter.ResLinkPresenter;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.view.ResLinkView;
import com.skyland.zimuzutv.zimuzutv.R;
import com.skyland.zimuzutv.zimuzutv.Util.CommomUtil;
import com.skyland.zimuzutv.zimuzutv.Widget.ProgressActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skyland on 2016/12/20.
 */

public class ResLinkActivity extends BaseSwipeBackActivity implements ResLinkView{
    private final String TAG = "ResLinkActivity";

    private ResLinkPresenter mPresenter;

    private ProgressActivity progressActivity;
    private Toolbar toolbar;
    private LinearLayout dl_layout;
    private LinearLayout bt_layout;
    private LinearLayout wp_layout;
    private LinearLayout ct_layout;

    private Button btnDlCopy;
    private Button btnBtCopy;
    private Button btnWpCopy;
    private Button btnCtCopy;
    private Button btnDlShare;
    private Button btnBtShare;
    private Button btnWpShare;
    private Button btnCtShare;

    private ImageView ivBack;
    private TextView tvName;
    private TextView tvSeason;
    private TextView tvType;
    private TextView tvSize;

    private String id;
    private String name;
    private String season;
    private String type;
    private String size;
    private List<ResourceLinkListDto> addrList = new ArrayList<ResourceLinkListDto>();



    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_resource_link);
    }

    @Override
    protected void findViewById() {
        progressActivity = (ProgressActivity) findViewById(R.id.reslink_progress);
        toolbar = (Toolbar) findViewById(R.id.reslink_toolbar);
        dl_layout = (LinearLayout) findViewById(R.id.reslink_dl_layout);
        bt_layout = (LinearLayout) findViewById(R.id.reslink_bt_layout);
        wp_layout = (LinearLayout) findViewById(R.id.reslink_wp_layout);
        ct_layout = (LinearLayout) findViewById(R.id.reslink_ct_layout);

        btnDlCopy = (Button) findViewById(R.id.reslink_btn_dl_copy);
        btnBtCopy = (Button) findViewById(R.id.reslink_btn_bt_copy);
        btnWpCopy = (Button) findViewById(R.id.reslink_btn_wp_copy);
        btnCtCopy = (Button) findViewById(R.id.reslink_btn_ct_copy);
        btnDlShare = (Button) findViewById(R.id.reslink_btn_dl_share);
        btnBtShare = (Button) findViewById(R.id.reslink_btn_bt_share);
        btnWpShare = (Button) findViewById(R.id.reslink_btn_wp_share);
        btnCtShare = (Button) findViewById(R.id.reslink_btn_ct_share);

        ivBack = (ImageView) findViewById(R.id.reslink_ivback);
        tvName = (TextView) findViewById(R.id.reslink_tv_name);
        tvSeason = (TextView) findViewById(R.id.reslink_tv_season);
        tvType = (TextView) findViewById(R.id.reslink_tv_type);
        tvSize = (TextView) findViewById(R.id.reslink_tv_size);
    }

    @Override
    protected void setListener() {
        ivBack.setOnClickListener(this);
        btnDlCopy.setOnClickListener(this);
        btnBtCopy.setOnClickListener(this);
        btnWpCopy.setOnClickListener(this);
        btnCtCopy.setOnClickListener(this);
        btnDlShare.setOnClickListener(this);
        btnBtShare.setOnClickListener(this);
        btnWpShare.setOnClickListener(this);
        btnCtShare.setOnClickListener(this);
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
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            id = bundle.getString("com.skyladn.zimuzutv.resId");
            name = bundle.getString("com.skyladn.zimuzutv.resName");
            season = bundle.getString("com.skyladn.zimuzutv.resSeason");
            type = bundle.getString("com.skyladn.zimuzutv.resType");
            size = bundle.getString("com.skyladn.zimuzutv.resSize");
        }

        tvName.setText(name);
        tvSeason.setText(season);
        tvType.setText(type);
        tvSize.setText(size);

        mPresenter = new ResLinkPresenter(this);
        String timestamp = Api.getTimestamp();
        String key = Api.getAccessKey(timestamp);
        mPresenter.LoadResLinkListData(false, Constant.API_CID, key, timestamp, id);

    }

    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.reslink_btn_dl_copy:
                copyLink(Constant.WAY_DL,"ED2K链接");
                break;
            case R.id.reslink_btn_bt_copy:
                copyLink(Constant.WAY_BT,"磁力链接");
                break;
            case R.id.reslink_btn_wp_copy:
                copyLink(Constant.WAY_WP,"网盘链接");
                break;
            case R.id.reslink_btn_ct_copy:
                copyLink(Constant.WAY_CT,"城通盘链接");
                break;
            case R.id.reslink_btn_dl_share:
                showToast("share");
                break;
            case R.id.reslink_btn_bt_share:
                showToast("share");
                break;
            case R.id.reslink_btn_wp_share:
                showToast("share");
                break;
            case R.id.reslink_btn_ct_share:
                showToast("share");
                break;
            case R.id.reslink_ivback:
                scrollToFinishActivity();   //调用swipebacklayout的库返回
                break;
        }
    }
    private void copyLink(String way,String tip){
        int size = addrList.size();
        for (int i = 0; i < size; i++){
            ResourceLinkListDto data = new ResourceLinkListDto();
            data = addrList.get(i);
            if(data.getWay().equals(way)){
                ClipboardManager cmb = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData mClip = ClipData.newPlainText("linktext",data.getAddress());
                cmb.setPrimaryClip(mClip);
                showToast(tip + "复制成功");
            }
        }
    }
    private void shareLink(){

    }

    @Override
    public void loadResLinkData(List<ResourceLinkListDto> dataList) {
        addrList = dataList;
        for(int i = 0; i < dataList.size(); i++){
            ResourceLinkListDto data = dataList.get(i);
            Log.d(TAG, "loadResLinkData: " + data.getWay());
            if(data.getWay().equals("1")){
                dl_layout.setVisibility(View.VISIBLE);
            }else if(data.getWay().equals("2")){
                bt_layout.setVisibility(View.VISIBLE);
            }else if(data.getWay().equals("9")){
                wp_layout.setVisibility(View.VISIBLE);
            }else if(data.getWay().equals("12")){
                ct_layout.setVisibility(View.VISIBLE);
            }
        }
    }

    private void addAddrList(String way,String addr){
        ResourceLinkListDto link = new ResourceLinkListDto();
        link.setWay(way);
        link.setAddress(addr);
        addrList.add(link);
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
}
