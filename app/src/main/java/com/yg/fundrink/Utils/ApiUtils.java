package com.yg.fundrink.Utils;

import com.yg.fundrink.BuildConfig;
import com.yg.fundrink.Interface.GetRequest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {
    private String TAG = "apiutils";
    private static ApiUtils apiUtils;
    private String YGSCRET = "ygsecert1007";

    public static ApiUtils getInstance() {
        if (apiUtils == null) {
            synchronized (ApiUtils.class) {
                if (apiUtils == null) {
                    apiUtils = new ApiUtils();
                }
            }
        }
        return apiUtils;
    }

    public GetRequest create() {
        LogUtils.e(TAG,"start" );
        OkHttpClient.Builder okhttpclientbuilder = new OkHttpClient.Builder();
//                .retryOnConnectionFailure(false)
//                .readTimeout(Config.TIME_OUT, TimeUnit.SECONDS)
//                .connectTimeout(Config.TIME_OUT, TimeUnit.SECONDS);
//
        okhttpclientbuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                String time = System.currentTimeMillis() + "";
                String secret =  time + YGSCRET;
                String md5 = DeviceInfoUtils.getMD5(secret, false);

                okhttp3.Request.Builder requestBuilder = original.newBuilder()
                        .header("Accept", "application/json")
                        .removeHeader("User-Agent")
                        .addHeader("User-Agent", getPlayerAgent())
                        .addHeader("version-code", BuildConfig.VERSION_NAME)
                        .addHeader("source", BuildConfig.FLAVOR+"")
                        .addHeader("time", time)
                        .addHeader("secret", md5.substring(0,8));
                if (null != UserInfoUtils.getInstance().getUserInfo() && null != UserInfoUtils.getInstance().getUserInfo().getAccessToken()) {
                    requestBuilder.header("access-token", UserInfoUtils.getInstance().getUserInfo().getAccessToken());
                }
                requestBuilder.method(original.method(), original.body());

                okhttp3.Request request = requestBuilder.build();
                Response response = chain.proceed(request);
                return response;
            }
        });
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://test.shyonggui.com:8004/") // 设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 支持RxJava平台
                .client(okhttpclientbuilder.build())
                .build();
        // 步骤5:创建 网络请求接口 的实例
        GetRequest request = retrofit.create(GetRequest.class);
        return request;
    }

    private static String getPlayerAgent() {
        String userAgent = "";
        StringBuffer sb = new StringBuffer();
        userAgent = System.getProperty("http.agent");//Dalvik/2.1.0 (Linux; U; Android 6.0.1; vivo X9L Build/MMB29M)

        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
