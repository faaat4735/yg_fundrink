package com.yg.fundrink.Fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yg.fundrink.Adapter.DrinkAdapter;
import com.yg.fundrink.DataList.Response.BaseResponse;
import com.yg.fundrink.DataList.Response.HomeInfoResponse;
import com.yg.fundrink.R;
import com.yg.fundrink.Utils.ApiUtils;
import com.yg.fundrink.Utils.LogUtils;
import com.yg.fundrink.Utils.ToastUtils;
import com.yg.fundrink.View.RoundProgressBar;


import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeFragment extends BaseFragment {
    String TAG = "HomeFragment";
    private RoundProgressBar roundProgressBar;
    private TextView tx2;
    private TextView tx4;
    private RecyclerView recyclerView;
    private Button drinkButton;
    private List<HomeInfoResponse.DrinkItem> drinkList = new ArrayList<>();
    private DrinkAdapter drinkAdapter;

    public int getResId() {
        return R.layout.fragment_home;
    }

    public void initView (View view) {
        // 圆形进度条
        roundProgressBar = view.findViewById(R.id.roundProgressBar);
        tx2 = view.findViewById(R.id.bar_tx_2);
        tx4 = view.findViewById(R.id.bar_tx_4);
        // 喝水记录列表
        recyclerView = view.findViewById(R.id.drinkList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        drinkAdapter = new DrinkAdapter(drinkList, R.layout.item_drink_info);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(drinkAdapter);
        // 喝水按钮
        drinkButton = view.findViewById(R.id.drinkButton);
    }

    public void initData(View view) {
        ApiUtils.getInstance().create().getHomeInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<HomeInfoResponse>>() {
                    @Override
                    public void onCompleted() {
                        LogUtils.e(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "onError:" + e.getMessage());
                    }

                    @Override
                    public void onNext(BaseResponse<HomeInfoResponse> req) {
                        LogUtils.e(TAG, "onNext" + req.toString());
                        if (req.getCode() == 200) {
                            HomeInfoResponse homeinfo = req.getData();
                            roundProgressBar.setMax(homeinfo.getTarget());
                            tx4.setText(homeinfo.getTarget() + " ml");
                            roundProgressBar.setProgress(homeinfo.getCurrent());
                            tx2.setText(homeinfo.getCurrent() + " ml");
                            drinkList.clear();
                            drinkList.addAll(homeinfo.getList());
                            drinkAdapter.notifyDataSetChanged();
                        } else {
                            ToastUtils.show(req.getMsg());
                        }
                    }
                });

    }

    public void initEvent(View view) {
        drinkButton.setOnClickListener(v -> {
            LogUtils.e(TAG, drinkList.toString());
            ApiUtils.getInstance().create().addDrink()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BaseResponse<Object>>() {
                        @Override
                        public void onCompleted() {
                            LogUtils.e(TAG, "onCompleted");
                        }

                        @Override
                        public void onError(Throwable e) {
                            LogUtils.e(TAG, "onError" + e.getMessage());
                        }

                        @Override
                        public void onNext(BaseResponse<Object> objectBaseResponse) {
                            LogUtils.e(TAG, "onNext");
                        }
                    });
            int drinkOnce = 200;
            drinkList.add(0, new HomeInfoResponse.DrinkItem(System.currentTimeMillis(), "水", drinkOnce));
            int progress = roundProgressBar.getProgressVal() + drinkOnce;
            roundProgressBar.setProgress(progress);
            drinkAdapter.notifyDataSetChanged();
            tx2.setText(progress + " ml");
            ToastUtils.show("恭喜喝水成功");
        });
    }
}
