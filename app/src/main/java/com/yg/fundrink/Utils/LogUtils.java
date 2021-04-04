package com.yg.fundrink.Utils;

import android.util.Log;

import com.yg.fundrink.BuildConfig;

/**
 * @author go
 * time：2020/10/16
 * describe:
 */
public class LogUtils {

    public static void e(String TAG, String message) {
        if (BuildConfig.DEBUG)
            Log.e(TAG, message);
    }

    public static void i(String TAG, String message) {
        if (BuildConfig.DEBUG)
            Log.i(TAG, message);
    }

    public static void d(String TAG, String message) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, message);
    }
}
