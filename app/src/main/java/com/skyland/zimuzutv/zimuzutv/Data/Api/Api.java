package com.skyland.zimuzutv.zimuzutv.Data.Api;

import android.util.Log;

import com.skyland.zimuzutv.zimuzutv.Constant.Constant;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;



/**
 * Created by skyland on 2016/12/1.
 */

public class Api {


    private static final String TAG = "Api";

    //生成密钥
    public static String getAccessKey(String timestamp){
        String key = "";
        Log.d(TAG, "getAccessKey: "+timestamp);
        key = parseStrToMd5L32(Constant.API_CID+"$$"+Constant.API_KEY+"&&"+timestamp);
        return key;

    }

    //时间戳
    public static String getTimestamp(){
        return String.valueOf(new Date().getTime());
    }

    /**
     * @param str
     * @Description:  32位小写MD5
     */
    public static String parseStrToMd5L32(String str){
        String reStr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes){
                int bt = b&0xff;
                if (bt < 16){
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }
            reStr = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return reStr;
    }
}
