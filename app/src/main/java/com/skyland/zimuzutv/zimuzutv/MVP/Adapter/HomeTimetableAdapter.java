package com.skyland.zimuzutv.zimuzutv.MVP.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TimeTableListDto;
import com.skyland.zimuzutv.zimuzutv.MyApplication.MyApplication;
import com.skyland.zimuzutv.zimuzutv.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skyland on 2017/2/10.
 */

public class HomeTimetableAdapter extends RecyclerView.Adapter<HomeTimetableAdapter.mViewHolder>{
    private Context context;
    private List<TimeTableListDto> listData = new ArrayList<>();

    public HomeTimetableAdapter (Context context){
        this.context = context;
    }

    public void addList(List<TimeTableListDto> listData){
        this.listData = listData;
    }

    private HomeTimetableAdapter.OnItemClickListener clickListener;
    public void setClickListener(HomeTimetableAdapter.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public static interface OnItemClickListener {
        void onClick(View view, TimeTableListDto data);
    }

    @Override
    public HomeTimetableAdapter.mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HomeTimetableAdapter.mViewHolder viewHolder = new HomeTimetableAdapter.mViewHolder(LayoutInflater.from(context).inflate(R.layout.item_timetable_layout,parent,false));
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final HomeTimetableAdapter.mViewHolder holder, int position) {
        final TimeTableListDto data = listData.get(position);
        Glide.with(MyApplication.getInstance())
                .load(data.getPoster())
                .crossFade()
                .placeholder(R.mipmap.no_tv_poster)
                .into(holder.imgvPoster );
        holder.tvCnname.setText(data.getCnname());
        holder.tvEnname.setText("原名：" + data.getEnname());
        holder.tvJishu.setText("第" + data.getSeason() + "季第" + data.getEpisode() + "集");
        if(clickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(holder.itemView, data);
                }
            });
        }
    }

    @Override
    public int getItemCount()  {
        return listData.size();
    }

    class mViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgvPoster;
        private TextView tvCnname;
        private TextView tvEnname;
        private TextView tvJishu;

        public mViewHolder(View itemview){
            super(itemview);
            imgvPoster = (ImageView) itemview.findViewById(R.id.item_timetable_imgv_poster);
            tvCnname = (TextView) itemview.findViewById(R.id.item_timetable_tv_cnname);
            tvEnname = (TextView) itemview.findViewById(R.id.item_timetable_tv_enname);
            tvJishu = (TextView) itemview.findViewById(R.id.item_timetable_tv_jishu);
        }
    }
}
