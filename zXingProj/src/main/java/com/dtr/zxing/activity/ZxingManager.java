package com.dtr.zxing.activity;

import android.app.Activity;
import android.content.Context;

import com.dtr.zxing.ZxingCallback;

/**
 * Created by zhuxiaolong on 16/6/23.
 */
public class ZxingManager extends Activity{
    private static ZxingManager ourInstance;

    public static ZxingManager getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new ZxingManager(context);
        }
        return ourInstance;
    }

    private Context mContext;

    private ZxingManager(Context context) {
        this.mContext = context;
    }



    public void startZxing(){

    }











    private ZxingCallback mZxingCallback;

    public void setZxingCallback(ZxingCallback zxingCallback) {
        mZxingCallback = zxingCallback;
    }
}
