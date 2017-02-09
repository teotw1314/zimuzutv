package com.skyland.zimuzutv.zimuzutv.MVP.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by skyland on 2016/12/18.
 */

public class ResourceListDto implements Parcelable{
    private String id;
    private String name;
    private String format;
    private String season;
    private String episode;
    private String size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ResourceListDto(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.format);
        parcel.writeString(this.season);
        parcel.writeString(this.episode);
        parcel.writeString(this.size);
    }

    protected ResourceListDto(Parcel in){
        this.id = in.readString();
        this.name = in.readString();
        this.format = in.readString();
        this.season = in.readString();
        this.episode = in.readString();
        this.size = in.readString();
    }
    public static final Parcelable.Creator<ResourceListDto> CREATOR = new Parcelable.Creator<ResourceListDto>() {
        @Override
        public ResourceListDto createFromParcel(Parcel parcel) {
            return new ResourceListDto(parcel);
        }

        @Override
        public ResourceListDto[] newArray(int size) {
            return new ResourceListDto[size];
        }
    };

}
