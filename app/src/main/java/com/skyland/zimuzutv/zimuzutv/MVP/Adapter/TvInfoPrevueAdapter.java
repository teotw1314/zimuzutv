package com.skyland.zimuzutv.zimuzutv.MVP.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.prevue;

import com.skyland.zimuzutv.zimuzutv.R;

import java.util.List;


/**
 * Created by skyland on 2016/12/19.
 */

public class TvInfoPrevueAdapter extends RecyclerView.Adapter<TvInfoPrevueAdapter.mViewHolder>{

    private Context context;
    private List<prevue> prevueList;

    public TvInfoPrevueAdapter(Context context, List<prevue> prevueList){
        this.context = context;
        this.prevueList = prevueList;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mViewHolder viewHolder = new mViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tvinfo_prevue_layout,parent,false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int i) {
        prevue p = prevueList.get(i);
        Log.d("TvInfoPrevueAdapter", "onBindViewHolder: "+p.getPlay_time());
        holder.tvSeason.setText("第" + p.getSeason() + "季第" + p.getEpisode() + "集");
        holder.tvTime.setText("播出时间：" + p.getPlay_time() + " " + p.getWeek());
    }

    @Override
    public int getItemCount() {
        return prevueList.size();
    }

    class mViewHolder extends RecyclerView.ViewHolder{
        private TextView tvSeason;
        private TextView tvTime;

        public mViewHolder(View itemview){
            super(itemview);
            tvSeason = (TextView) itemview.findViewById(R.id.item_prevue_tv_season);
            tvTime = (TextView) itemview.findViewById(R.id.item_prevue_tv_time);
        }
    }
}
