package com.skyland.zimuzutv.zimuzutv.MVP.Adapter.holder;

import android.view.View;
import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.BGABannerAdapter;
import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.HomeAllAdapter;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.BannerDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Home.fragment.HomeNewsFragment;
import com.skyland.zimuzutv.zimuzutv.MyApplication.MyApplication;
import com.skyland.zimuzutv.zimuzutv.R;
import java.util.List;
import cn.bingoogolapple.bgabanner.BGABanner;
import static com.skyland.zimuzutv.zimuzutv.MVP.Home.fragment.HomeNewsFragment.bannerImage;
import static com.skyland.zimuzutv.zimuzutv.MVP.Home.fragment.HomeNewsFragment.bannerTitle;

/**
 * Created by skyland on 2016/12/5.
 */

public class TypeBannerViewHolder extends TypeAbstractViewHolder<Integer>{
    private BGABanner bgaBanner;
    private HomeAllAdapter.BannerCallBack callBack;

    public TypeBannerViewHolder(View itemView, HomeAllAdapter.BannerCallBack callBack){
        super(itemView);
        bgaBanner = (BGABanner) itemView.findViewById(R.id.multi_headview_banner);
        this.callBack = callBack;
    }


    @Override
    public void bindHolder(Integer data) {
        bgaBanner.setAdapter(new BGABannerAdapter(MyApplication.getInstance()));
        bgaBanner.setData(bannerImage, bannerTitle);
        if (callBack == null){
            return;
        }
        final List<BannerDto> datas = HomeNewsFragment.homeBannerList;
        bgaBanner.setOnItemClickListener(new BGABanner.OnItemClickListener() {
            @Override
            public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {

                callBack.onItemClick(datas.get(position));

              //  Toast.makeText(MyApplication.getInstance(), String.valueOf(datas.get(position).getId()), Toast.LENGTH_SHORT).show();
              //  Log.d("bgaBannerClickListener", "onBannerItemClick: "+String.valueOf(datas.get(position).getId()));
            }
        });
    }
}
