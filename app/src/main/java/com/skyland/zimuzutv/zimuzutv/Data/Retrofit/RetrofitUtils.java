package com.skyland.zimuzutv.zimuzutv.Data.Retrofit;

import com.skyland.zimuzutv.zimuzutv.Constant.Constant;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by skyland on 2016/12/1.
 * 封装一个retrofit集成0kHttp3的抽象基类
 */

public abstract class RetrofitUtils {

    private static Retrofit mRetrofit;
    private static OkHttpClient mOkhttpClient;
    /*
     *获取retrofit对象
     */
    protected static Retrofit getRetrofit(){
        if(null == mRetrofit){
            if(null == mOkhttpClient){
                mOkhttpClient =  OkHttp3Utils.getOkHttpClient();
            }
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constant.API_SERVER+"/")
                    //添加转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置使用okhttp网络请求
                    .client(mOkhttpClient)
                    .build();
        }
        return mRetrofit;
    }


}
