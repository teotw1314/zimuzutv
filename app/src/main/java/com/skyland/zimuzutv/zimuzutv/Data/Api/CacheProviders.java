package com.skyland.zimuzutv.zimuzutv.Data.Api;

/**
 * Created by skyland on 2016/12/1.
 */

import com.skyland.zimuzutv.zimuzutv.MVP.Entity.BannerDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.NewsInfoDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.NewsInfoListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceLinkListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.SubtitleResultDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TimeTableListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TvDetialDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TvInfoListDto;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.LifeCache;
import io.rx_cache.Reply;
import rx.Observable;

/**
 * 缓存API接口
 * @LifeCache设置缓存过期时间. 如果没有设置@LifeCache , 数据将被永久缓存理除非你使用了 EvictProvider, EvictDynamicKey or EvictDynamicKeyGroup .
 * EvictProvider可以明确地清理清理所有缓存数据.
 * EvictDynamicKey可以明确地清理指定的数据 DynamicKey.
 * EvictDynamicKeyGroup 允许明确地清理一组特定的数据. DynamicKeyGroup.
 * DynamicKey驱逐与一个特定的键使用EvictDynamicKey相关的数据。比如分页，排序或筛选要求
 * DynamicKeyGroup。驱逐一组与key关联的数据，使用EvictDynamicKeyGroup。比如分页，排序或筛选要求
 */

public interface CacheProviders {
    //获取banner信息，缓存7天
    @LifeCache(duration = 1, timeUnit = TimeUnit.DAYS)
    Observable<Reply<List<BannerDto>>> getBannerList(Observable<List<BannerDto>> oRepos, DynamicKey username, EvictDynamicKey evictDynamicKey);

    //获取热门影视信息，缓存7天
    @LifeCache(duration = 1, timeUnit = TimeUnit.DAYS)
    Observable<Reply<List<TvInfoListDto>>> getTvList(Observable<List<TvInfoListDto>> oRepos, DynamicKey username, EvictDynamicKey evictDynamicKey);
    //获取新闻资讯
    @LifeCache(duration = 1, timeUnit = TimeUnit.DAYS)
    Observable<Reply<List<NewsInfoListDto>>> getNewsList(Observable<List<NewsInfoListDto>> oRepos, DynamicKey username, EvictDynamicKey evictDynamicKey);

    //获取影视详情
    @LifeCache(duration = 7,timeUnit = TimeUnit.DAYS)
    Observable<Reply<TvDetialDto>> getTvDetial(Observable<TvDetialDto> oRepos, DynamicKey username, EvictDynamicKey evictDynamicKey);

    //根据影视id获取影视下载资源列表
    @LifeCache(duration = 1, timeUnit = TimeUnit.DAYS)
    Observable<Reply<List<ResourceListDto>>> getResourceList(Observable<List<ResourceListDto>> oRepos, DynamicKey username, EvictDynamicKey evictDynamicKey);

    //根据资源id获取资源下载地址
    @LifeCache(duration = 7, timeUnit = TimeUnit.DAYS)
    Observable<Reply<List<ResourceLinkListDto>>> getResourceLinkList(Observable<List<ResourceLinkListDto>> oRepos, DynamicKey username, EvictDynamicKey evictDynamicKey);

    //根据id获取资源详情
    @LifeCache(duration = 7, timeUnit = TimeUnit.DAYS)
    Observable<Reply<NewsInfoDto>> getNewsInfo(Observable<NewsInfoDto> oRepos, DynamicKey username, EvictDynamicKey evictDynamicKey);

    //获取字幕列表
    @LifeCache(duration = 7, timeUnit = TimeUnit.DAYS)
    Observable<Reply<SubtitleResultDto>> getSubtitleList(Observable<SubtitleResultDto> oRepos, DynamicKey username, EvictDynamicKey evictDynamicKey);

    //获取时间表
    @LifeCache(duration = 7, timeUnit = TimeUnit.DAYS)
    Observable<Reply<Map.Entry<String, List<TimeTableListDto>>>> getTimeTableList(Observable<Map<String, List<TimeTableListDto>>> oRepos, DynamicKey username, EvictDynamicKey evictDynamicKey);

}
