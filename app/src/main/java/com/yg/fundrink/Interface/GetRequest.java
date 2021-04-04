package com.yg.fundrink.Interface;

import com.yg.fundrink.DataList.Response.BaseResponse;
import com.yg.fundrink.DataList.Response.HistoryInfoResponse;
import com.yg.fundrink.DataList.Response.HomeInfoResponse;
import com.yg.fundrink.DataList.Response.UserLoginResponse;
import com.yg.fundrink.DataList.Resquest.UserLoginResquest;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface GetRequest {

    // 注解里传入 网络请求 的部分URL地址
    // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
    // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
    @POST("user/info")
    Observable<BaseResponse<UserLoginResponse>> getUserInfo(@Body UserLoginResquest req);

    @POST("info/home")
    Observable<BaseResponse<HomeInfoResponse>> getHomeInfo();

    @POST("action/drink")
    Observable<BaseResponse<Object>> addDrink();

    @POST("info/history")
    Observable<BaseResponse<HistoryInfoResponse>> getHistoryInfo();
}
