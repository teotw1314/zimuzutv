package com.skyland.zimuzutv.zimuzutv.MVP.TvInfo.view;

import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceListDto;

import java.util.List;

/**
 * Created by skyland on 2016/12/18.
 */

public interface TvInfoResourceView {
    void loadTvInfoResourceList(List<ResourceListDto> data);
}
