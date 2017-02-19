package com.skyland.zimuzutv.zimuzutv.MVP.Entity;

import java.util.List;

/**
 * Created by skyland on 2017/2/19.
 */

public class FilmsResultDto {
    private String count;
    private List<FilmsListDto> list;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<FilmsListDto> getList() {
        return list;
    }

    public void setList(List<FilmsListDto> list) {
        this.list = list;
    }
}
