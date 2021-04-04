package com.yg.fundrink.Fragment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.veken.chartview.bean.ChartBean;
import com.veken.chartview.drawtype.DrawBgType;
import com.veken.chartview.view.LineChartView;
import com.yg.fundrink.DataList.Response.BaseResponse;
import com.yg.fundrink.DataList.Response.HistoryInfoResponse;
import com.yg.fundrink.R;
import com.yg.fundrink.Utils.ApiUtils;
import com.yg.fundrink.Utils.ChartBeanUtils;
import com.yg.fundrink.Utils.LogUtils;
import com.yg.fundrink.Utils.ToastUtils;

import java.util.ArrayList;

import retrofit2.http.Body;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HistoryFragment extends BaseFragment {
    LineChartView lineChartView;
    TextView tx2;
    TextView tx4;
    TextView tx6;
    TextView tx8;

    @Override
    public int getResId() {
        return R.layout.fragment_history;
    }

    @Override
    public void initView(View view) {
        lineChartView = view.findViewById(R.id.chart);
        lineChartView.setDrawBgType(DrawBgType.DrawBitmap);
        //是否需要连接线
//        lineChartView.setNeedDrawConnectYDataLine(true);
////连接线的样式为虚线（也可以为直线）
//        lineChartView.setDrawConnectLineType(DrawConnectLineType.DrawDottedLine);
        tx2 = view.findViewById(R.id.tv_tx2);
        tx4 = view.findViewById(R.id.tv_tx4);
        tx6 = view.findViewById(R.id.tv_tx6);
        tx8 = view.findViewById(R.id.tv_tx8);
    }

    @Override
    public void initData(View view) {
        //Y轴文字
        lineChartView.setyLableText("七日饮水报告");
        ArrayList<ChartBean> barChartList = new ArrayList<>();
        for (int i = 0;i < 7;i++) {
            ChartBean bean = new ChartBean();
            bean.setDate("01-21");
            bean.setValue("200");
            barChartList.add(bean);
        }
        lineChartView.setData(barChartList);
        ApiUtils.getInstance().create()
                .getHistoryInfo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<BaseResponse<HistoryInfoResponse>>() {
                    @Override
                    public void onCompleted() {
                        LogUtils.e(TAG, "onCompleted");

                    }

                    @Override
                    public void onError(Throwable e) {

                        LogUtils.e(TAG, "onError" + e.getMessage());
                    }

                    @Override
                    public void onNext(BaseResponse<HistoryInfoResponse> rep) {
                        LogUtils.e(TAG, "onNext");
                        if (rep.getCode() == 200) {
                            HistoryInfoResponse historyInfoResponse = rep.getData();
                            LogUtils.e(TAG, historyInfoResponse.toString());
                            LogUtils.e(TAG, historyInfoResponse.getList().toString());
                            tx2.setText(historyInfoResponse.getTotal() + "");
                            tx4.setText(historyInfoResponse.getPerDay() + "");
                            tx6.setText(historyInfoResponse.getReachCount() + "");
                            tx8.setText(historyInfoResponse.getDrinkCount() + "");
                            ArrayList<ChartBean> barChartList = new ArrayList<>();
                            Boolean isAllZero = true;
                            for (int i = 0;i < 7;i++) {
                                LogUtils.e(TAG, historyInfoResponse.getList().get(i).getQuantity() + "");
                                ChartBean bean = new ChartBean();
                                bean.setDate(historyInfoResponse.getList().get(i).getDate());
                                bean.setValue(historyInfoResponse.getList().get(i).getQuantity() + "");
                                if (historyInfoResponse.getList().get(i).getQuantity() != 0) {
                                    isAllZero = false;
                                }
                                barChartList.add(bean);
                            }
                            if (isAllZero) {
                                lineChartView.setNeedBg(false);
                            }
                            lineChartView.setData(barChartList);
                            lineChartView.postInvalidate();
                        } else {
                            ToastUtils.show(rep.getMsg());
                        }
                    }
                });

    }

    @Override
    public void initEvent(View view) {
        super.initEvent(view);
    }

}
