package com.skyland.zimuzutv.zimuzutv.MVP.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skyland.zimuzutv.zimuzutv.Constant.Constant;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TvInfoListDto;
import com.skyland.zimuzutv.zimuzutv.MyApplication.MyApplication;
import com.skyland.zimuzutv.zimuzutv.R;
import com.skyland.zimuzutv.zimuzutv.Util.CommomUtil;

import java.util.List;

import okhttp3.internal.Internal;

/**
 * Created by skyland on 2016/12/2.
 */

public class TvInfoGridAdapter extends RecyclerView.Adapter<TvInfoGridAdapter.MyViewHolder>{
    private static final String TAG = "TvInfoGridAdapter";
    private Context context;
    private List<TvInfoListDto> datas;



    /**
     * item的点击事件的长按事件接口
     */
    private OnItemClickListener onItemClickListener;

    public TvInfoGridAdapter(Activity context, List<TvInfoListDto> datas) {
        this.context = context;
        this.datas = datas;
    }


    /**
     * 设置点击事件
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.item_home_tvs_layout, null);
        MyViewHolder viewHolder = new MyViewHolder(contentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        /*
        RecyclerView.LayoutParams layoutParams;
        int ivWidth = (int)((Constant.mWidth - CommomUtil.dip2px(MyApplication.getInstance(),10))/3 + 0.5f);
        int ivheight = (int) (ivWidth * 1.5 + 0.5f);
        Log.d(TAG, "onBindViewHolder: itemheight"+String.valueOf(ivheight));
        layoutParams = new RecyclerView.LayoutParams(ivWidth,ivheight);
        holder.itemView.setLayoutParams(layoutParams);
        */

        /**加载图片**/
        Glide.with(context)
                .load(datas.get(position).getPoster())
                .crossFade()
                //.placeholder(R.mipmap.no_tv_poster)
                .into((holder.imageView));
        holder.tvName.setText(datas.get(position).getCnname());
        Log.d(TAG, "onBindViewHolder: "+String.valueOf(position));
        /**设置item点击监听**/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClickListener(position, datas.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    /**
     * 用于缓存的ViewHolder
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvName;


        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_home_tvs_iv);
            tvName = (TextView) itemView.findViewById(R.id.item_home_tvs_tvName);

        }
    }


    /**
     * 设置item监听的接口
     */
    public interface OnItemClickListener {
        void onItemClickListener(int position, TvInfoListDto data);

    }


}

