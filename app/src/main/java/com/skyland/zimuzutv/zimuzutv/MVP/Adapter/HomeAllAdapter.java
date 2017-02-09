package com.skyland.zimuzutv.zimuzutv.MVP.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.holder.TypeBannerViewHolder;
import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.holder.TypeHotViewHolder;
import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.holder.TypeNewsViewHolder;
import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.holder.TypeSectionViewHolder;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.BannerDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.NewsInfoListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.SectionDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TvInfoListDto;
import com.skyland.zimuzutv.zimuzutv.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by skyland on 2016/12/3.
 */

public class HomeAllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int TYPE_SECTION1 = 1;
    public static final int TYPE_SECTION2 = 2;
    public static final int TYPE_HOT = 3;
    public static final int TYPE_NEWS = 4;

    public static final int TYPE_HEAD = 10;

    private int isLoaded = 0;



    private LayoutInflater layoutInflater;
    private View headview;

    public HomeAllAdapter(Context context,View headview){
        layoutInflater = LayoutInflater.from(context);
        this.headview = headview;
    }

    private List<SectionDto> section1List;
    private List<SectionDto> section2List;
    private List<TvInfoListDto> hotList;
    private List<NewsInfoListDto> newsList;
    private List<Integer> headTemp= new ArrayList<>();

    private List<Integer> types = new ArrayList<>();
    private Map<Integer,Integer> mPositions = new HashMap<>();



    public void addList(List<SectionDto> section1List, List<TvInfoListDto> hotList, List<SectionDto> section2List, List<NewsInfoListDto> newsList){
        isLoaded++;
        if(isLoaded>1){
           // this.section1List.clear();
            //this.section2List.clear();
            //this.hotList.clear();
           // this.newsList.clear();
            this.headTemp.clear();
            this.types.clear();
            this.mPositions.clear();
        }
        headTemp.add(1);
        addListByType(TYPE_HEAD,headTemp);
        for (int i = 0 ; i < headTemp.size();i++){
            Log.d("HomeAllAdapter", "headTemp: "+String.valueOf(headTemp.get(i)));
        }

        addListByType(TYPE_SECTION1,section1List);
        addListByType(TYPE_HOT,hotList);
        addListByType(TYPE_SECTION2,section2List);
        addListByType(TYPE_NEWS,newsList);


        this.section1List = section1List;
        this.section2List = section2List;
        this.hotList = hotList;
        this.newsList = newsList;

    }

    private void addListByType(int type, List list) {
        mPositions.put(type,types.size());
        for(int i = 0; i < list.size(); i++ ){
            types.add(type);
        }
    }

    //banner点击回调
    private BannerCallBack bannerCallBack;
    public interface BannerCallBack{
        void onItemClick(BannerDto bannerDto);
    }
    public void setBannerCallBack(BannerCallBack bannerCallBack){
        this.bannerCallBack = bannerCallBack;
    }

    //HotTv点击回调
    private HotTvCallBack hotTvCallBack;
    public interface HotTvCallBack{
        void onItemClick(TvInfoListDto tvInfoListDto);
    }
    public void setHotTvCallBack(HotTvCallBack hotTvCallBack){
        this.hotTvCallBack = hotTvCallBack;
    }

    //News点击回调
    private NewsCallBack newsCallBack;
    public interface NewsCallBack{
        void onItemClick(NewsInfoListDto newsInfoListDto);
    }
    public void setNewsCallBack(NewsCallBack newsCallBack){
        this.newsCallBack = newsCallBack;
    }

    //section点击回调
    private SectionCallBack sectionCallBack;
    public interface SectionCallBack{
        void onItemClick(SectionDto sectionDto);
    }
    public void setSectionCallBack(SectionCallBack sectionCallBack){
        this.sectionCallBack = sectionCallBack;
    }

    //section2点击回调
    private Section2CallBack section2CallBack;
    public interface Section2CallBack{
        void onItemClick(SectionDto sectionDto);
    }
    public void setSection2CallBack(Section2CallBack section2CallBack){
        this.section2CallBack = section2CallBack;
    }

    //public interface


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_HEAD:
                return new TypeBannerViewHolder(layoutInflater.inflate(R.layout.multi_typeview_headview,parent,false),bannerCallBack);

            case TYPE_SECTION1:
                return new TypeSectionViewHolder(layoutInflater.inflate(R.layout.multi_typeview_section,parent,false),sectionCallBack);

            case TYPE_HOT:
                return new TypeHotViewHolder(layoutInflater.inflate(R.layout.item_home_tvs_layout,parent,false),hotTvCallBack);

            case TYPE_SECTION2:
                return new TypeSectionViewHolder(layoutInflater.inflate(R.layout.multi_typeview_section,parent,false),sectionCallBack);

            case TYPE_NEWS:
                return new TypeNewsViewHolder(layoutInflater.inflate(R.layout.item_home_news_layout,parent,false),newsCallBack);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        int realPosition = position - mPositions.get(viewType);
        switch (viewType){
            case TYPE_HEAD:
                ((TypeBannerViewHolder)holder).bindHolder(headTemp.get(position));
                break;

            case TYPE_SECTION1:
                ((TypeSectionViewHolder)holder).bindHolder(section1List.get(realPosition));
                break;

            case TYPE_HOT:
                ((TypeHotViewHolder)holder).bindHolder(hotList.get(realPosition));

                break;

            case TYPE_SECTION2:
                ((TypeSectionViewHolder)holder).bindHolder(section2List.get(realPosition));

                break;

            case TYPE_NEWS:
                ((TypeNewsViewHolder)holder).bindHolder(newsList.get(realPosition));

                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return types.get(position);
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    private OnItemClickListener mListener;

    public static interface OnItemClickListener{
        //点击事件
        void onItemClick(View v, int position);
    }

    public  void setOnItemClickListener(OnItemClickListener mListener){
        this.mListener = mListener;
    }

}
