package com.yg.fundrink.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yg.fundrink.Utils.LogUtils;

public abstract class BaseFragment extends Fragment {
    String TAG = "baseFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(getResId(), container, false);
        try {
            initView(mView);
            initData(mView);
            initEvent(mView);
        } catch (Exception e) {
            LogUtils.e(TAG, "初始化失败:" + e.getMessage());
        }
        return mView;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public int getResId() {
        LogUtils.e(TAG, "init redId in base fragment");
        return 0;
    }

    public void initView(View view) {
        LogUtils.e(TAG, "init view in base fragment");
    }

    public void initData(View view) {
        LogUtils.e(TAG, "init view in base fragment");
    }

    public void initEvent(View view) {
        LogUtils.e(TAG, "init view in base fragment");
    }
}
