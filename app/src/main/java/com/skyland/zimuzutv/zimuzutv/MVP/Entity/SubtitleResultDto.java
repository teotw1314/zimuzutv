package com.skyland.zimuzutv.zimuzutv.MVP.Entity;

import java.util.List;

/**
 * Created by skyland on 2016/12/29.
 */

public class SubtitleResultDto {
    private String count;
    private List<SubtitleListDto> list;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<SubtitleListDto> getList() {
        return list;
    }

    public void setList(List<SubtitleListDto> list) {
        this.list = list;
    }
}
