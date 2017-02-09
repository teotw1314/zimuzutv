package com.skyland.zimuzutv.zimuzutv.MVP.Home.view;

import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TimeTableListDto;

import java.util.List;
import java.util.Map;

/**
 * Created by skyland on 2017/1/6.
 */

public interface HomeTimeTableFragmentView {
    void loadList(Map.Entry<String, List<TimeTableListDto>> mapData);
}
