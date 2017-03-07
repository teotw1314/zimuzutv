package com.skyland.zimuzutv.zimuzutv.MVP.Adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.gms.common.api.Status;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.FilmsListDto;
import com.skyland.zimuzutv.zimuzutv.R;

import java.util.List;
import java.util.Stack;

/**
 * Created by skyland on 2017/3/6.
 */

public class FilmFrgAdapter extends BaseQuickAdapter<FilmsListDto, BaseViewHolder>{

    public FilmFrgAdapter(List<FilmsListDto> listData){
        super(R.layout.item_filmfragmnt_layout,listData);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, FilmsListDto item) {
        viewHolder.setText(R.id.film_frg_tv_cname, item.getCnname())
                .setText(R.id.film_frg_tv_tvtype, item.getChannel())
                .setText(R.id.film_frg_tv_ename, item.getEnname())
                .setText(R.id.film_frg_tv_area, item.getArea())
                .setText(R.id.film_frg_tv_state, item.getPlay_status())
                .setText(R.id.film_frg_tv_type, item.getCategory())
                .setText(R.id.film_frg_tv_scan, item.getViews())
                .setText(R.id.film_frg_tv_explain, item.getRemark());
        Glide.with(mContext).load(item.getPoster()).crossFade().into((ImageView) viewHolder.getView(R.id.film_frg_iv_poster));
    }
}
