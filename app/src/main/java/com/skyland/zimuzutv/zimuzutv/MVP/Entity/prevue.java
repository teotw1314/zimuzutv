package com.skyland.zimuzutv.zimuzutv.MVP.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by skyland on 2016/12/17.
 */

public class prevue implements Parcelable{
    private String season;
    private String episode;
    private String play_time;
    private String week;

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

    public String getPlay_time() {
        return play_time;
    }

    public void setPlay_time(String play_time) {
        this.play_time = play_time;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.season);
        parcel.writeString(this.episode);
        parcel.writeString(this.play_time);
        parcel.writeString(this.week);
    }

    public prevue(){

    }

    protected prevue(Parcel in){
        this.season = in.readString();
        this.episode = in.readString();
        this.play_time = in.readString();
        this.week = in.readString();
    }

    public static final Parcelable.Creator<prevue> CREATOR = new Parcelable.Creator<prevue>() {
        @Override
        public prevue createFromParcel(Parcel parcel) {
            return new prevue(parcel);
        }

        @Override
        public prevue[] newArray(int size) {
            return new prevue[size];
        }
    };


}
