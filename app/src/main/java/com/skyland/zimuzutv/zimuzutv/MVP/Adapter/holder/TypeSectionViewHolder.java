package com.skyland.zimuzutv.zimuzutv.MVP.Adapter.holder;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.skyland.zimuzutv.zimuzutv.MVP.Adapter.HomeAllAdapter;
import com.skyland.zimuzutv.zimuzutv.MyApplication.MyApplication;
import com.skyland.zimuzutv.zimuzutv.R;


import com.skyland.zimuzutv.zimuzutv.MVP.Entity.SectionDto;

import org.w3c.dom.Text;

/**
 * Created by skyland on 2016/12/5.
 */

public class TypeSectionViewHolder extends TypeAbstractViewHolder<SectionDto>{

    private HomeAllAdapter.SectionCallBack callBack;
    public TextView tvSection;

    public TypeSectionViewHolder(View itemView, HomeAllAdapter.SectionCallBack callBack){
        super(itemView);
        tvSection = (TextView) itemView.findViewById(R.id.multi_section_title);
        this.callBack = callBack;
    }

    @Override
    public void bindHolder(final SectionDto data) {
        tvSection.setText(data.getName());

        if(callBack == null ){
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
