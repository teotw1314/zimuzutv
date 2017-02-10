package com.skyland.zimuzutv.zimuzutv.MVP.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TvInfoListDto;
import com.skyland.zimuzutv.zimuzutv.R;

import java.util.List;

/**
 * Created by skyland on 2016/12/19.
 */

public class TvInfoResourceAdapter extends RecyclerView.Adapter<TvInfoResourceAdapter.mViewHolder>{

    private Context context;
    private List<ResourceListDto> resourceList;

    public TvInfoResourceAdapter (Context context, List<ResourceListDto> resourceList){
        this.context = context;
        this.resourceList = resourceList;
    }


    private OnItemClickListener clickListener;
    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public static interface OnItemClickListener {
        void onClick(View view, int position);
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mViewHolder viewHolder = new mViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tvinfo_resource_layout,parent,false));
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final mViewHolder holder, int position) {
        final ResourceListDto data = resourceList.get(position);
        holder.tvName.setText(data.getName());
        holder.tvSeason.setText("第" + data.getSeason() + "季第" + data.getEpisode() + "集");
        holder.tvType.setText(data.getFormat());
        holder.tvSize.setText(data.getSize());
        if(clickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(holder.itemView, holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount()  {
        return resourceList.size();
    }

    class mViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvSeason;
        private TextView tvType;
        private TextView tvSize;

        public mViewHolder(View itemview){
            super(itemview);
            tvName = (TextView) itemview.findViewById(R.id.item_resource_tv_name);
            tvSeason = (TextView) itemview.findViewById(R.id.item_resource_tv_season);
            tvType = (TextView) itemview.findViewById(R.id.item_resource_tv_type);
            tvSize = (TextView) itemview.findViewById(R.id.item_resource_tv_size);
        }
    }
}


