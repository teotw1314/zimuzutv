package com.skyland.zimuzutv.zimuzutv.MVP.Adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.skyland.zimuzutv.zimuzutv.Constant.Constant;
import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.HomeAllAdapter;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.NewsInfoListDto;
import com.skyland.zimuzutv.zimuzutv.MyApplication.MyApplication;
import com.skyland.zimuzutv.zimuzutv.R;
import com.skyland.zimuzutv.zimuzutv.Util.CommomUtil;


/**
 * Created by skyland on 2016/12/5.
 */

public class TypeNewsViewHolder extends TypeAbstractViewHolder<NewsInfoListDto>{

    private HomeAllAdapter.NewsCallBack callBack;
    public ImageView ivPoster;
    public TextView tvTitle;
    public TextView tvDateLine;
    public TextView tvRead;

    public TypeNewsViewHolder(View itemView, HomeAllAdapter.NewsCallBack callBack){
        super(itemView);
        ivPoster = (ImageView) itemView.findViewById(R.id.item_home_news_poster_iv);
        tvTitle = (TextView) itemView.findViewById(R.id.item_home_news_tvTitle);
        tvDateLine = (TextView) itemView.findViewById(R.id.item_home_news_tv_timeline);
        tvRead = (TextView) itemView.findViewById(R.id.item_home_news_tv_readed);
        this.callBack = callBack;
    }

    @Override
    public void bindHolder(final NewsInfoListDto data) {
    //NewsInfoListDto data = new New
        Glide.with(MyApplication.getInstance())
                .load(data.getPoster())
                .crossFade()
                .placeholder(R.mipmap.home_load_error)
                .into((ivPoster));
        tvTitle.setText(data.getTitle());
        tvRead.setText(data.getViews());
        String time = CommomUtil.getStandardDate(data.getDateline());
        String type = "";
        switch (data.getType()){
            case "news":
                type = "新闻";
                break;
            case "guide":
                type = "导视";
                break;
            case "m_review":
                type = "影评";
                break;
            case "tv_review":
                type = "剧评";
                break;
            default:
                type = data.getType();
                break;
        }
        tvDateLine.setText(type + "/" + time);

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
