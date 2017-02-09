package com.skyland.zimuzutv.zimuzutv.Data.Retrofit;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/*
 *okHttp的配置
 * 缓存已经添加  目前只支持GET请求的缓存  POST的我在找合适的处理方法
 * 也可根据自己的需要进行相关的修改
 */
public class OkHttp3Utils {

    private static OkHttpClient mOkHttpClient;


    /**
     * 获取OkHttpClient对象
     */
    public static OkHttpClient getOkHttpClient() {

        //日志显示级别
        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("zcb","OkHttp====Message:"+message);
            }
        });
        loggingInterceptor.setLevel(level);

        if (null == mOkHttpClient) {

            //同样okhttp3后也使用build设计模式
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            mOkHttpClient = httpClientBuilder
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();
            //OkHttp进行添加拦截器loggingInterceptor
            httpClientBuilder.addInterceptor(loggingInterceptor);

        }



        return mOkHttpClient;
    }



}
