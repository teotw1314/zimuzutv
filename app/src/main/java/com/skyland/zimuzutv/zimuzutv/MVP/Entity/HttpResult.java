package com.skyland.zimuzutv.zimuzutv.MVP.Entity;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by skyland on 2016/12/1.
 * 服务器返回数据实体类
 */

public class HttpResult<T> {
    public int status;
    public String info;
    public T data;

    public HttpResult(int status,String info,T data){
        this.status = status;
        this.info = info;
        this.data = data;
    }

    //getter
    public int getStatus(){
        return status;
    }
    public String getInfo(){
        return info;
    }
    public T getData(){
        return data;
    }

    //setter
    public void setStatus(int status){
        this.status = status;
    }
    public void setInfo(String info){
        this.info = info;
    }
    public void setData(T data){
        this.data = data;
    }


}
