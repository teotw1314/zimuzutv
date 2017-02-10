package com.skyland.zimuzutv.zimuzutv.Data.Api;

import com.skyland.zimuzutv.zimuzutv.MVP.Entity.BannerDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.HttpResult;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.NewsInfoDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.NewsInfoListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceLinkListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.SubtitleResultDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TimeTableListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TvDetialDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TvInfoListDto;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Api接口
 * Created by skyland on 2016/12/1.
 */

public interface ZimuzuService {

    //获取banner信息（获取资讯的前四条记录）
    @GET("article/fetchlist")
    Observable<HttpResult<List<BannerDto>>> getBannerList(
            @Query("cid")String cid, @Query("accesskey")String accesskey, @Query("timestamp")String timestamp,
            @Query("client")int client, @Query("newstype")String newstype, @Query("limit")int limit,
            @Query("page")int page);

    //获取热门影视Grid（6）
    @GET("resource/top")
    Observable<HttpResult<List<TvInfoListDto>>> getTvList(
            @Query("cid")String cid, @Query("accesskey")String accesskey, @Query("timestamp")String timestamp,
            @Query("client")int client, @Query("limit")int limit, @Query("page")int page);

    //获取新闻咨询
    @GET("article/fetchlist")
    Observable<HttpResult<List<NewsInfoListDto>>> getNewsList(
            @Query("cid")String cid, @Query("accesskey")String accesskey, @Query("timestamp")String timestamp,
            @Query("client")int client, @Query("limit")int limit,
            @Query("page")int page);

    //根据影视id获取影视详情
    @GET("resource/getinfo")
    Observable<HttpResult<TvDetialDto>> getTvDetial(
            @Query("cid")String cid, @Query("accesskey")String accesskey, @Query("timestamp")String timestamp,
            @Query("client")int client, @Query("id")String id, @Query("prevue")int prevue, @Query("share")int share
    );

    //根据影视id获取影视下载资源列表
    @GET("resource/itemlist_web")
    Observable<HttpResult<List<ResourceListDto>>> getResourceList(
        @Query("cid")String cid, @Query("accesskey")String accesskey, @Query("timestamp")String timestamp,
        @Query("client")int client,@Query("id")String id, @Query("file")int file
    );

    //根据资源id获取下载地址
    @GET("resource/itemlink")
    Observable<HttpResult<List<ResourceLinkListDto>>> getResourceLinkList(
            @Query("cid")String cid, @Query("accesskey")String accesskey, @Query("timestamp")String timestamp,
            @Query("client")int client,@Query("id")String id
    );

    //根据id获取资源详情
    @GET("article/getinfo")
    Observable<HttpResult<NewsInfoDto>> getNewsInfo(
            @Query("cid")String cid, @Query("accesskey")String accesskey, @Query("timestamp")String timestamp,
            @Query("client")int client,@Query("id")String id
    );

    //获取字幕列表
    @GET("subtitle/fetchlist")
    Observable<HttpResult<SubtitleResultDto>> getSubtitleList(
            @Query("cid")String cid, @Query("accesskey")String accesskey, @Query("timestamp")String timestamp,
            @Query("client")int client,@Query("limit")int limit, @Query("page")int page
    );

    //获取时间表
    @GET("tv/schedule")
    Observable<HttpResult< Map<String, List<Map<String, String>>> >> getTimeTableList(
            @Query("cid")String cid, @Query("accesskey")String accesskey, @Query("timestamp")String timestamp,
            @Query("client")int client,@Query("start")String strat, @Query("end")String end
    );


}
