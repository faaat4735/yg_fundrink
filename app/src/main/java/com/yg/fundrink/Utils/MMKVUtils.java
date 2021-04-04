package com.yg.fundrink.Utils;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.tencent.mmkv.MMKV;

public class MMKVUtils {

    public static String KEY_FIRST_OPEN_APP = "key_first_open_app";

    //MMKV 腾讯开源用于提升本地存储效率的轻量级存储框架
    public static void MMKVinit(Context context){
        String rootDir = MMKV.initialize(context);
        Log.e("TPLManager", " mmkv rootDir = " + rootDir);
    }

    public static void PutString(String key, String value) {
        MMKV.defaultMMKV().encode(key, value);
    }

    public static String GetString(String key) {
        return MMKV.defaultMMKV().decodeString(key, null);
    }

    public static void PutBoolean(String key, boolean value) {
        MMKV.defaultMMKV().encode(key, value);
    }

    public static boolean GetBoolean(String key) {
        return MMKV.defaultMMKV().decodeBool(key, true);
    }

}
