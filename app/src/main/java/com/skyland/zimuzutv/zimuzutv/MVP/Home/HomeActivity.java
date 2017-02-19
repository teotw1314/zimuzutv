package com.skyland.zimuzutv.zimuzutv.MVP.Home;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.skyland.zimuzutv.zimuzutv.Constant.Constant;
import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.HomeFragmentAdapter;
import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.ViewPagerAdapter;
import com.skyland.zimuzutv.zimuzutv.MVP.Base.BaseActivity;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.fragment.HomeCaptionsFragment;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.fragment.HomeFilmsStoreFragment;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.fragment.HomeNewsFragment;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.fragment.HomeTimetableFragment;
import com.skyland.zimuzutv.zimuzutv.R;
import com.skyland.zimuzutv.zimuzutv.Util.CommomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by skyland on 2016/11/30.
 */

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener
        , ViewPager.OnPageChangeListener {

    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private TextView tab_index;
    private TextView tab_captions;
    private TextView tab_timetable;
    private TextView tab_filmstore;
    private FloatingActionButton fab;

    private ViewPagerAdapter viewPagerAdapter;
    private HomeNewsFragment homeNewsFragment;
    private HomeCaptionsFragment homeCaptionsFragment;
    private HomeTimetableFragment homeTimetableFragment;
    private HomeFilmsStoreFragment homeFilmsStoreFragment;

    private AnimatorSet fabRefreshAni;

    private int currentFragment = 0;

    ///viewpager
    private float mPreviousPositionOffset;
    private boolean mViewPagerScrollingLeft;
    private int mPreviousPosition;
    //fab
    private float mFabCurrentX;


    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_navigation_drawer);
    }

    @Override
    protected void findViewById() {
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tab_index = (TextView) findViewById(R.id.tab_index);
        tab_captions = (TextView) findViewById(R.id.tab_captions);
        tab_timetable = (TextView) findViewById(R.id.tab_timetable);
        tab_filmstore = (TextView) findViewById(R.id.tab_filmstore);
        viewPager = (ViewPager) findViewById(R.id.home_viewpager);
        fab = (FloatingActionButton) findViewById(R.id.home_fab);
    }

    @Override
    protected void setListener() {
        mNavigationView.setNavigationItemSelectedListener(this);
        tab_index.setOnClickListener(this);
        tab_captions.setOnClickListener(this);
        tab_timetable.setOnClickListener(this);
        tab_filmstore.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
        fab.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);     //设置layout布局可以在状态栏里面显示
        if(SdkApi()){
            ViewGroup.LayoutParams lp = toolbar.getLayoutParams();
            lp.width = ViewPager.LayoutParams.MATCH_PARENT;
            lp.height = CommomUtil.dip2px(this, 46) + getStatusBarHeight();
            toolbar.setLayoutParams(lp);
            toolbar.setPadding(0,getStatusBarHeight(),0,0);     //设置toolbar的paddingtop
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {   //6.0以上设置图标反色
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        //mNavigationView.setItemIconTintList(null);//设置菜单图标恢复本来的颜色
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar_navigation);  三明治图标
        //toolbar.inflateMenu(R.menu.toolbar_menu);   //设置右上角填充菜单
        //初始化fragment adapter 绑定
        viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), this);
        viewPager.setOffscreenPageLimit(4);
        homeNewsFragment = new HomeNewsFragment();
        homeCaptionsFragment = new HomeCaptionsFragment();
        homeTimetableFragment = new HomeTimetableFragment();
        homeFilmsStoreFragment = new HomeFilmsStoreFragment();
        viewPagerAdapter.addFragment(homeNewsFragment, "");
        viewPagerAdapter.addFragment(homeCaptionsFragment, "");
        viewPagerAdapter.addFragment(homeTimetableFragment, "");
        viewPagerAdapter.addFragment(homeFilmsStoreFragment, "");
        viewPager.setAdapter(viewPagerAdapter);

        fabRefreshAni = (AnimatorSet) AnimatorInflater.loadAnimator(getActivityContext(),
                R.animator.refresh_animator);
        fabRefreshAni.setTarget(fab);
        mFabCurrentX = fab.getX();
    }

    @Override
    protected Context getActivityContext() {
        return this;
    }
    ///控件点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_index:
                viewPager.setCurrentItem(0);
                //Toast.makeText(HomeActivity.this, "tab_index", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_captions:
                viewPager.setCurrentItem(1);
                //Toast.makeText(HomeActivity.this, "tab_captions", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_timetable:
                viewPager.setCurrentItem(2);
               // Toast.makeText(HomeActivity.this, "tab_timetable", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_filmstore:
                viewPager.setCurrentItem(3);
                //Toast.makeText(HomeActivity.this, "tab_filmstore", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home_fab://fab点击事件
                fabClick(currentFragment);
                break;

        }
    }
    ///侧边栏item点击事件
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int menuItemId = item.getItemId();

        if (menuItemId == R.id.item1) {
            Toast.makeText(HomeActivity.this, "ITEM1", Toast.LENGTH_SHORT).show();
        } else if (menuItemId == R.id.item_2) {
            Toast.makeText(HomeActivity.this, "item2", Toast.LENGTH_SHORT).show();
        } else if (menuItemId == R.id.item_setting) {
            Toast.makeText(HomeActivity.this, "setting", Toast.LENGTH_SHORT).show();
        } else if (menuItemId == R.id.item_about) {
            Toast.makeText(HomeActivity.this, "about", Toast.LENGTH_SHORT).show();
        }

        mDrawer.closeDrawers();
        return true;

    }


    /*
     *viewpager滑动事件
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //Log.d("onPageScrolled", "onPageScrolled: position" + String.valueOf(position));
        //Log.d("onPageScrolled", "onPageScrolled: " + String.valueOf(mFabCurrentX));
        //Log.d("onPageScrolled", "onPageScrolled: positionOffset" + String.valueOf(positionOffset));
       // Log.d("onPageScrolled", "onPageScrolled: positionOffsetPixels" + String.valueOf(positionOffsetPixels));
        // Scrollling left or right
        if ((positionOffset > mPreviousPositionOffset && position == mPreviousPosition) || (positionOffset < mPreviousPositionOffset && position > mPreviousPosition)) {
            mViewPagerScrollingLeft = true;
        } else if (positionOffset < mPreviousPositionOffset) {

            mViewPagerScrollingLeft = false;
        }
        mPreviousPositionOffset = positionOffset;
        mPreviousPosition = position;

        ///fab animation
        if(positionOffset < 0.5){
            fab.setTranslationY(positionOffset * 500);
        }else{
            fab.setTranslationY( (1-positionOffset) * 500);
        }


        // FADE the indicator layout
        /*
        if (mViewPagerScrollingLeft) {
            if(positionOffset < 0.5){
                fab.setTranslationY(positionOffset*1000);
            }else{
                fab.setTranslationY( (1-positionOffset) * 1000);
            }
        } else if (!mViewPagerScrollingLeft ) {
            if( positionOffset < 0.5){
                fab.setTranslationY(positionOffset*1000);
            }else{
                fab.setTranslationY( (1-positionOffset) * 1000);
            }
        }
        */

    }
    @Override
    public void onPageSelected(int position) {
        currentFragment = position;
        resetColor();
        switch (position)
        {
            case 0:
                tab_index.setTextColor(this.getResources().getColor(R.color.colorTabSelected));
                break;
            case 1:
                tab_captions.setTextColor(this.getResources().getColor(R.color.colorTabSelected));
                break;
            case 2:
                tab_timetable.setTextColor(this.getResources().getColor(R.color.colorTabSelected));
                break;
            case 3:
                tab_filmstore.setTextColor(this.getResources().getColor(R.color.colorTabSelected));
                break;
        }
    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    ///resetColor  -> set tab's color as default
    private void resetColor()
    {
        tab_index.setTextColor(this.getResources().getColor(R.color.colorTabDefault));
        tab_captions.setTextColor(this.getResources().getColor(R.color.colorTabDefault));
        tab_timetable.setTextColor(this.getResources().getColor(R.color.colorTabDefault));
        tab_filmstore.setTextColor(this.getResources().getColor(R.color.colorTabDefault));
    }

    ///fab点击事件处理  调用不同fragment里面暴露的方法
    private void fabClick(int current){
        switch (current){
            case 0:
                startFabAni();
                homeNewsFragment.fabNewsClick();
                break;
            case 1:
                startFabAni();
                homeCaptionsFragment.fabCaptionsClick();
                break;
            case 2:
                homeTimetableFragment.fabTimetableClick();
                break;
            case 3:
                homeFilmsStoreFragment.fabFilmsClick();
                break;
        }
    }
    public void fabCallBack(int current){

        stopFabAni();
    }
    private void startFabAni(){
        if (!fabRefreshAni.isRunning())
            fabRefreshAni.start();
    }
    private void stopFabAni(){
        if (fabRefreshAni.isRunning())
            fabRefreshAni.end();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();
        }
        return false;
    }


    private boolean isExit = false;

    private void exitBy2Click() {
        Timer timer;
        if (!isExit) {
            isExit = true;
            Toast.makeText(HomeActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            finish();
        }
    }





}
