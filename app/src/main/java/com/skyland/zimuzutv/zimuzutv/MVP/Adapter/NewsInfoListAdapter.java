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
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.NewsInfoListDto;
import com.skyland.zimuzutv.zimuzutv.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by skyland on 2016/12/3.
 */

public class NewsInfoListAdapter extends RecyclerView.Adapter<NewsInfoListAdapter.MyViewHolder>{
    private static final String TAG = "NewsInfoListAdapter";
    private Context context;
    private List<NewsInfoListDto> datas;



    /**
     * item的点击事件的长按事件接口
     */
    private NewsInfoListAdapter.OnItemClickListener onItemClickListener;

    public NewsInfoListAdapter(Activity context, List<NewsInfoListDto> datas) {
        this.context = context;
        this.datas = datas;
        Log.d(TAG, "NewsInfoListAdapter: "+String.valueOf(datas));
    }


    /**
     * 设置点击事件
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(NewsInfoListAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public NewsInfoListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.item_home_news_layout, null);
        NewsInfoListAdapter.MyViewHolder viewHolder = new NewsInfoListAdapter.MyViewHolder(contentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NewsInfoListAdapter.MyViewHolder holder, final int position) {
        /**加载图片**/
        Log.d(TAG, "onBindViewHolder: "+datas.get(position).getTitle());
        //NewsInfoListDto data = new New
        Glide.with(context)
                .load(datas.get(position).getPoster())
                .crossFade()
                //.placeholder(R.mipmap.home_load_error)
                .into((holder.ivPoster));
        holder.tvTitle.setText(datas.get(position).getTitle());
      //  holder.tvTimeLine.setText(datas.get(position).getDateline());
      //  holder.tvReaded.setText("20");
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
        private ImageView ivPoster;
        private TextView tvTitle;
      //  private TextView tvTimeLine;
       // private TextView tvReaded;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivPoster = (ImageView) itemView.findViewById(R.id.item_home_news_poster_iv);
            tvTitle = (TextView) itemView.findViewById(R.id.item_home_news_tvTitle);
           // tvTimeLine = (TextView) itemView.findViewById(R.id.item_home_news_tv_timeline);
           // tvReaded = (TextView) itemView.findViewById(R.id.item_home_news_tv_readed);

        }
    }


    /**
     * 设置item监听的接口
     */
    public interface OnItemClickListener {
        void onItemClickListener(int position, NewsInfoListDto data);

    }
}
