package com.skyland.zimuzutv.zimuzutv.MVP.Entity;

import java.util.List;

/**
 * Created by skyland on 2016/12/1.
 */

public class HomeDto {
    private List<BannerDto> banner;

    public List<BannerDto> getBanner(){
        return banner;
    }

    public void setBanner(List<BannerDto> banner){
        this.banner = banner;
    }
}
