package com.skyland.zimuzutv.zimuzutv.MVP.Home.fragment;

import com.skyland.zimuzutv.zimuzutv.Util.DateUtil;
import com.skyland.zimuzutv.zimuzutv.Widget.ProgressActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.skyland.zimuzutv.zimuzutv.Constant.Constant;
import com.skyland.zimuzutv.zimuzutv.Data.Api.Api;
import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.HomeTimetableAdapter;
import com.skyland.zimuzutv.zimuzutv.MVP.Base.LazyFragment;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TimeTableListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.presenter.HomeTimeTableFragmentPresenter;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.view.HomeTimeTableFragmentView;
import com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.TvInfoActivity;
import com.skyland.zimuzutv.zimuzutv.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by skyland on 2016/12/1.
 */

public class HomeTimetableFragment extends LazyFragment implements HomeTimeTableFragmentView, DatePickerDialog.OnDateSetListener, View.OnClickListener{

    private static final String TAG = "HomeTimetableFragment";
    private HomeTimeTableFragmentPresenter mPresenter;

    private RecyclerView recyclerView;
    private ProgressActivity progress;
    private ImageView imgvLeft;
    private ImageView imgvRight;
    private TextView tvDate;
    private HomeTimetableAdapter adapter;
    private List<TimeTableListDto> listData = new ArrayList<>();
    private String start = "2017-02-09";

    private boolean isPrepared;
    private boolean isFirstLoad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.home_timetable_recyclerview);
        progress = (ProgressActivity) rootView.findViewById(R.id.timetable_progress);
        imgvLeft = (ImageView) rootView.findViewById(R.id.timetable_imgv_left);
        imgvRight = (ImageView) rootView.findViewById(R.id.timetable_imgv_right);
        tvDate = (TextView) rootView.findViewById(R.id.timetable_tv_date);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);//设置list 向布局
        adapter = new HomeTimetableAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        isPrepared = true;
        isFirstLoad = true;
        initData();

        return rootView;
    }
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home_timetable,container,false);
    }

    @Override
    protected void initListener() {
        adapter.setClickListener(new HomeTimetableAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, TimeTableListDto data) {
                // Toast.makeText(getContext(),data.getId() , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), TvInfoActivity.class);
                intent.putExtra("com.skyland.zimuzu.tvid",data.getId());
                startActivity(intent);
            }
        });
        imgvLeft.setOnClickListener(this);
        imgvRight.setOnClickListener(this);
        tvDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.timetable_imgv_left:
                start = DateUtil.getSpecifiedDayBefore(start);
                loadData();
                break;
            case R.id.timetable_imgv_right:
                start = DateUtil.getSpecifiedDayAfter(start);
                loadData();
                break;
            case R.id.timetable_tv_date:
                start = DateUtil.getDate("yyyy-MM-dd");
                loadData();
                break;
        }
    }

    @Override
    protected void initData() {
        if(isPrepared && isVisible && isFirstLoad) {
            isFirstLoad = false;
            Log.d(TAG, "initData: 3");
            start = DateUtil.getDate("yyyy-MM-dd");
            mPresenter = new HomeTimeTableFragmentPresenter(this);
            loadData();
        }
    }

    @Override
    public void loadList(Map<String, List<Map<String, String>>> mapData) {
        listData = getListData(start,mapData);
        adapter.addList(listData);
        adapter.notifyDataSetChanged();

        progress.showContent();
    }

    /*
     *getListData  (List<Map> to List<TimeTableListDto> )
     *datetime 2017-02-10
     */
    private List<TimeTableListDto> getListData(String datetime, Map<String, List<Map<String, String>>> mapData){
        List<TimeTableListDto> listData = new ArrayList<TimeTableListDto>();
        List<Map<String, String>> listMap = mapData.get(datetime);
        int size = listMap.size();
        for(int i = 0; i < size; i++){
            TimeTableListDto dto = new TimeTableListDto();
            Map<String, String> map = listMap.get(i);
            dto.setId(map.get("id"));
            dto.setCnname(map.get("cnname"));
            dto.setEnname(map.get("enname"));
            dto.setPoster(map.get("poster"));
            dto.setSeason(map.get("season"));
            dto.setEpisode(map.get("episode"));
            listData.add(dto);
        }
        return listData;
    }

    ///fab点击事件
    public void fabTimetableClick(){
        Log.d(TAG, "fabTimetableClick: sss");
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                HomeTimetableFragment.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setVersion(DatePickerDialog.Version.VERSION_2);
        dpd.show(getFragmentManager(),"");

    }

    ///日期选择
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String month;
        String day;
        if (++monthOfYear < 10){
            month = "0" + monthOfYear;
        }else{
            month = String.valueOf(monthOfYear);
        }
        if (dayOfMonth < 10){
            day = "0" + dayOfMonth;
        }else{
            day = String.valueOf(dayOfMonth);
        }
        start = year + "-" + month + "-" + day;  //dayOfMonth+"/"+(++monthOfYear)+"/"+year;
        loadData();
    }

    private  void loadData(){
        tvDate.setText(start + "  " + DateUtil.getWeekByDate(start));
        progress.showLoading();
        String timestamp = Api.getTimestamp();
        String key = Api.getAccessKey(timestamp);
        mPresenter.loadTimeTableList(false, Constant.API_CID, key, timestamp, start, start);
    }

}
