package com.skyland.zimuzutv.zimuzutv.MVP.Adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.skyland.zimuzutv.zimuzutv.MVP.Entity.BannerDto;

/**
 * Created by skyland on 2016/12/5.
 */

public abstract class TypeAbstractViewHolder<T> extends RecyclerView.ViewHolder{

    public TypeAbstractViewHolder(View itemView){
        super(itemView);
    }

    public abstract void bindHolder(T model);
}
