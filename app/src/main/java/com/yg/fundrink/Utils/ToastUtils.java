package com.yg.fundrink.Utils;


import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ToastUtils {

    private static Context mContext;
    private static List<Toast> toastList;

    public static void init(Context context){
        mContext = context;
        toastList = new ArrayList<>();
    }

    public static void show(String str){
        if (mContext == null){
            return;
        }
        Toast toast = Toast.makeText(mContext, str, Toast.LENGTH_SHORT);
        toastList.add(toast);
        if (toastList.size() > 1){
            Toast toast1 = toastList.get(0);
            toastList.remove(0);
            toast1.cancel();
            toast1 = null;
        }
        toast.show();
    }

}
