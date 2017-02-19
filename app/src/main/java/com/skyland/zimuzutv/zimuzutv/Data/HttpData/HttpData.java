package com.skyland.zimuzutv.zimuzutv.Data.HttpData;

import com.skyland.zimuzutv.zimuzutv.Data.Api.CacheProviders;
import com.skyland.zimuzutv.zimuzutv.Data.Api.ZimuzuService;
import com.skyland.zimuzutv.zimuzutv.Data.Retrofit.ApiException;
import com.skyland.zimuzutv.zimuzutv.Data.Retrofit.RetrofitUtils;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.BannerDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.FilmsResultDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.HttpResult;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.NewsInfoDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.NewsInfoListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceLinkListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.ResourceListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.SubtitleResultDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TimeTableListDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TvDetialDto;
import com.skyland.zimuzutv.zimuzutv.MVP.Entity.TvInfoListDto;
import com.skyland.zimuzutv.zimuzutv.Util.FileUtil;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.Reply;
import io.rx_cache.internal.RxCache;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by skyland on 2016/12/1.
 */

public class HttpData extends RetrofitUtils{

    private static final String TAG = "HttpData";

    private static File cacheDirectory = FileUtil.getcacheDirectory();
    private static final CacheProviders providers = new RxCache.Builder()
            .persistence(cacheDirectory)
            .using(CacheProviders.class);
    protected static final ZimuzuService service = getRetrofit().create(ZimuzuService.class);


    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpData INSTANCE = new HttpData();
    }

    //获取单例
    public static HttpData getInstance() {
        return SingletonHolder.INSTANCE;
    }


    //获取banner集合
    public void getBannerList(boolean isLoad, String cid, String accesskey, String timestamp, String newstype, int limit, int page, Observer<List<BannerDto>> observer){
        Observable observable = service.getBannerList(cid,accesskey,timestamp,2,newstype,limit,page).map(new HttpResultFunc<List<BannerDto>>());
        Observable observableCache = providers.getBannerList(observable,new DynamicKey("getBannerList"+newstype+page), new EvictDynamicKey( isLoad )).map(new HttpResultFuncCcche<List<BannerDto>>());
        setSubscribe(observableCache, observer);
    }

    //获取热门影视
    public void getTvList(boolean isLoad, String cid, String accesskey, String timestamp, int limit, int page, Observer<List<TvInfoListDto>> observer){
        Observable observable = service.getTvList(cid, accesskey, timestamp, 2, limit, page).map(new HttpResultFunc<List<TvInfoListDto>>());
        Observable observableCache = providers.getTvList(observable,new DynamicKey("getTvList"+limit+page), new EvictDynamicKey( isLoad )).map(new HttpResultFuncCcche<List<TvInfoListDto>>());
        setSubscribe(observableCache, observer);
    }

    //获取新闻资讯
    public void getNewsList(boolean isLoad, String cid, String accesskey, String timestamp, int limit, int page, Observer<List<NewsInfoListDto>> observer){
        Observable observable = service.getNewsList(cid,accesskey,timestamp,2,limit,page).map(new HttpResultFunc<List<NewsInfoListDto>>());
        Observable observableCache = providers.getNewsList(observable,new DynamicKey("getNewsList"+limit+page), new EvictDynamicKey( isLoad )).map(new HttpResultFuncCcche<List<NewsInfoListDto>>());
        setSubscribe(observableCache, observer);
    }

    //获取影视详情
    public void getTvDetial(boolean isLoad, String cid, String accesskey, String timestamp, String id, int prevue, int share, Observer<TvDetialDto> observer){
        Observable observable = service.getTvDetial(cid, accesskey, timestamp, 2, id, prevue, share).map(new HttpResultFunc<TvDetialDto>());
        Observable observableCache = providers.getTvDetial(observable,new DynamicKey("getTvDetial"+id), new EvictDynamicKey( isLoad )).map(new HttpResultFuncCcche<TvDetialDto>());
        setSubscribe(observableCache, observer);
    }

    //根据影视id获取影视下载资源列表
    public void getResourceList(boolean isLoad, String cid, String accesskey, String timestamp, String id, int file, Observer<List<ResourceListDto>> observer){
        Observable observable = service.getResourceList(cid, accesskey, timestamp, 2, id, file).map(new HttpResultFunc<List<ResourceListDto>>());
        Observable observableCache = providers.getResourceList(observable,new DynamicKey("getResourceList"+id + timestamp), new EvictDynamicKey( isLoad )).map(new HttpResultFuncCcche<List<ResourceListDto>>());
        setSubscribe(observableCache, observer);
    }

    //根据资源id获取资源下载地址
    public void getResourceLinkList(boolean isLoad, String cid, String accesskey, String timestamp, String id, Observer<List<ResourceLinkListDto>> observer){
        Observable observable = service.getResourceLinkList(cid, accesskey, timestamp, 2, id).map(new HttpResultFunc<List<ResourceLinkListDto>>());
        Observable observableCache = providers.getResourceList(observable,new DynamicKey("getResourceLinkList"+ id), new EvictDynamicKey( isLoad )).map(new HttpResultFuncCcche<List<ResourceLinkListDto>>());
        setSubscribe(observableCache, observer);
    }

    //根据id获取资源详情
    public void getNewsInfo(boolean isLoad, String cid, String accesskey, String timestamp, String id, Observer<NewsInfoDto> observer){
        Observable observable = service.getNewsInfo(cid, accesskey, timestamp, 2, id).map(new HttpResultFunc<NewsInfoDto>());
        Observable observableCache = providers.getNewsInfo(observable,new DynamicKey("getNewsInfo"+ id), new EvictDynamicKey( isLoad )).map(new HttpResultFuncCcche<NewsInfoDto>());
        setSubscribe(observableCache, observer);
    }

    //获取字幕列表
    public void getSubtitleList(boolean isLoad, String cid, String accesskey, String timestamp, int limit, int page, Observer<SubtitleResultDto> observer){
        Observable observable = service.getSubtitleList(cid, accesskey, timestamp, 2, limit, page).map(new HttpResultFunc<SubtitleResultDto>());
        Observable observableCache = providers.getSubtitleList(observable,new DynamicKey("getSubtitleList"+ page + limit), new EvictDynamicKey( isLoad )).map(new HttpResultFuncCcche<SubtitleResultDto>());
        setSubscribe(observableCache, observer);
    }

    //获取时间表
    public void getTimeTableList(boolean isLoad, String cid, String accesskey, String timestamp, String start, String end, Observer< Map<String, List<Map<String, String>>> > observer){
        Observable observable = service.getTimeTableList(cid, accesskey, timestamp, 2, start, end).map(new HttpResultFunc< Map<String, List<Map<String, String>>> >());
        Observable observableCache = providers.getTimeTableList(observable,new DynamicKey("getTimeTableList"+ start + end), new EvictDynamicKey( isLoad )).map(new HttpResultFuncCcche< Map<String, List<Map<String, String>>> >());
        setSubscribe(observableCache, observer);
    }

    //获取影视库
    public void getFilmsList(boolean isLoad, String cid, String accesskey, String timestamp,
                             String channel, String area, String sort, String year, String category,
                             int limit, int page, Observer<FilmsResultDto> observer){
        Observable observable = service.getFilmsList(cid, accesskey, timestamp, 2, channel, area,
                sort, year, category, limit, page).
                map(new HttpResultFunc<FilmsResultDto>());
        Observable observableCache = providers.getFilmsList(observable,new DynamicKey("getFilmsList"+ channel +
                area + sort + year + category + page + limit),
                new EvictDynamicKey( isLoad )).map(new HttpResultFuncCcche<FilmsResultDto>());
        setSubscribe(observableCache, observer);
    }



    /**
     * 插入观察者
     *
     * @param observable
     * @param observer
     * @param <T>
     */
    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }
    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private  class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getStatus() != 1 ) {
                throw new ApiException(httpResult);
            }
            return httpResult.getData();
        }
    }
    /**
     * 用来统一处理RxCacha的结果
     */
    private  class HttpResultFuncCcche<T> implements Func1<Reply<T>, T> {

        @Override
        public T call(Reply<T> httpResult) {
            return httpResult.getData();
        }
    }


}
