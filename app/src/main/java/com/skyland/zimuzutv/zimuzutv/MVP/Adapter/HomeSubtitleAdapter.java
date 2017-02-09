package com.skyland.zimuzutv.zimuzutv.MVP.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.SubtitleListDto;
import com.skyland.zimuzutv.zimuzutv.MyApplication.MyApplication;
import com.skyland.zimuzutv.zimuzutv.R;
import com.skyland.zimuzutv.zimuzutv.Util.CommomUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skyland on 2016/12/30.
 */

public class HomeSubtitleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = "HomeSubtitleAdapter";

    public final static int TYPE_NORMAL = 20;
    public final static int TYPE_FOOTER = 21;


    private Context context;
    private List<SubtitleListDto> list = new ArrayList<>();

    public HomeSubtitleAdapter(Context context) {
        this.context = context;
    }

    public void addList(List<SubtitleListDto> list) {
        this.list = list;
    }

    public void addMoreItem(List<SubtitleListDto> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    private HomeSubtitleAdapter.OnItemClickListener clickListener;

    public void setClickListener(HomeSubtitleAdapter.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public static interface OnItemClickListener {
        void onClick(View view, SubtitleListDto data);
    }


    @Override
    public int getItemViewType(int position) {
        if(position + 1 == getItemCount()){
            return TYPE_FOOTER;
        }else{
            return TYPE_NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_NORMAL){
            mViewholder viewHolder = new mViewholder(LayoutInflater.from(context).inflate(R.layout.item_subtitle_layout, parent, false));
            return viewHolder;
        }else if(viewType == TYPE_FOOTER){
            FootViewHolder footerHolder = new FootViewHolder(LayoutInflater.from(context).inflate(R.layout.item_loadmore_footlview,parent,false));
            return footerHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  mViewholder){
            final SubtitleListDto data = list.get(position);
            mViewholder mHolder = (mViewholder)holder;
            if(data.getResource_info() != null){
                Glide.with(MyApplication.getInstance())
                        .load(data.getResource_info().getPoster())
                        .crossFade()
                        .placeholder(R.mipmap.no_tv_poster)
                        .into( mHolder.ivPoster );
            }
            mHolder.tvCname.setText(data.getCnname());
            mHolder.tvEname.setText(data.getEnname());
            mHolder.tvSegment.setText("片源：" + data.getSegment());
            mHolder.tvLang.setText(data.getLang());
            mHolder.tvViews.setText(data.getViews());
            mHolder.tvSource.setText(data.getSource());
            mHolder.tvDateline.setText(CommomUtil.getStandardDate(data.getDateline()));
            if (clickListener != null) {
                mHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clickListener.onClick(holder.itemView, data);
                    }
                });
            }
        }else if(holder instanceof FootViewHolder){
            FootViewHolder footViewHolder=(FootViewHolder)holder;
        }



    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    class mViewholder extends RecyclerView.ViewHolder {
        private ImageView ivPoster;
        private TextView tvCname;
        private TextView tvEname;
        private TextView tvSegment;
        private TextView tvLang;
        private TextView tvViews;
        private TextView tvSource;
        private TextView tvDateline;

        public mViewholder(View itemView) {
            super(itemView);
            ivPoster = (ImageView) itemView.findViewById(R.id.item_subtitle_iv_poster);
            tvCname = (TextView) itemView.findViewById(R.id.item_subtitle_tv_cname);
            tvEname = (TextView) itemView.findViewById(R.id.item_subtitle_tv_ename);
            tvSegment = (TextView) itemView.findViewById(R.id.item_subtitle_tv_segment);
            tvLang = (TextView) itemView.findViewById(R.id.item_subtitle_tv_lang);
            tvViews = (TextView) itemView.findViewById(R.id.item_subtitle_tv_views);
            tvSource = (TextView) itemView.findViewById(R.id.item_subtitle_tv_source);
            tvDateline = (TextView) itemView.findViewById(R.id.item_subtitle_tv_dateline);
        }
    }

    /**
     * 底部FootView布局
     */
    public static class FootViewHolder extends  RecyclerView.ViewHolder{
        private TextView foot_view_item_tv;
        public FootViewHolder(View view) {
            super(view);
            foot_view_item_tv=(TextView)view.findViewById(R.id.item_loadmore_footer_tv);
        }
    }

}
