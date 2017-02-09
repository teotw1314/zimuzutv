package com.skyland.zimuzutv.zimuzutv.MVP.Adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.HomeAllAdapter;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TvInfoListDto;


import com.skyland.zimuzutv.zimuzutv.MyApplication.MyApplication;
import com.skyland.zimuzutv.zimuzutv.R;


/**
 * Created by skyland on 2016/12/5.
 */

public class TypeHotViewHolder extends TypeAbstractViewHolder<TvInfoListDto>{

    private HomeAllAdapter.HotTvCallBack callBack;
    public ImageView imageView;
    public TextView tvName;
    public TextView tvChannel;

    public TypeHotViewHolder(View itemView, HomeAllAdapter.HotTvCallBack callBack){
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.item_home_tvs_iv);
        tvName = (TextView) itemView.findViewById(R.id.item_home_tvs_tvName);
        tvChannel = (TextView) itemView.findViewById(R.id.item_home_tvs_tvchannal);
        this.callBack = callBack;

    }

    @Override
    public void bindHolder(final TvInfoListDto data) {
        /**加载图片**/
        Glide.with(MyApplication.getInstance())
                .load(data.getPoster())
                .crossFade()
                .placeholder(R.mipmap.no_tv_poster)
                .into((imageView));

        tvName.setText(data.getCnname());
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
        tvChannel.setText(channel);
        if(callBack == null){
            return;
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onItemClick(data);
            }
        });
    }
}
