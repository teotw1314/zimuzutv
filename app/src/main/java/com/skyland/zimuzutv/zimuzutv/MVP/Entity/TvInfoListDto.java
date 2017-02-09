package com.skyland.zimuzutv.zimuzutv.MVP.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by skyland on 2016/12/2.
 */

public class TvInfoListDto {


    private String id;
    private String cnname;
    private String channel;
    private String area;
    private String publish_year;
    private String play_status;
    private String poster;

    //getter

    public String getId (){
        return id;
    }
    public String getCnname (){
        return cnname;
    }
    public String getChannel (){
        return channel;
    }
    public String getArea (){
        return area;
    }
    public String getPublish_year (){
        return  publish_year;
    }
    public String getPlay_status (){
        return play_status;
    }
    public String getPoster (){
        return poster;
    }

    //setter
    public void setId (String id){
        this.id = id;
    }
    public void setCnname (String cnname){
        this.cnname = cnname;
    }
    public void setChannel (String channel){
        this.channel = channel;
    }
    public void setArea (String area){
        this.area = area;
    }
    public  void setPublish_year (String publish_year){
        this.publish_year = publish_year;
    }
    public void setPlay_status (String play_status){
        this.play_status = play_status;
    }
    public void setPoster (String poster){
        this.poster = poster;
    }

}
