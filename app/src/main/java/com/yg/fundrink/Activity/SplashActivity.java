package com.yg.fundrink.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.mmkv.MMKV;
import com.umeng.commonsdk.UMConfigure;
import com.yg.fundrink.BuildConfig;
import com.yg.fundrink.DataList.Response.BaseResponse;
import com.yg.fundrink.DataList.Response.UserLoginResponse;
import com.yg.fundrink.DataList.Resquest.UserLoginResquest;
import com.yg.fundrink.Utils.ApiUtils;
import com.yg.fundrink.Utils.LogUtils;
import com.yg.fundrink.Utils.MMKVUtils;
import com.yg.fundrink.Utils.NetUtils;
import com.yg.fundrink.Interface.OnAgreementPopListener;
import com.yg.fundrink.Pop.AgreementPop;
import com.yg.fundrink.R;
import com.yg.fundrink.Utils.ToastUtils;
import com.yg.fundrink.Utils.UserDeviceInfoUtils;
import com.yg.fundrink.Utils.UserInfoUtils;

import java.util.Timer;
import java.util.TimerTask;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.yg.fundrink.Utils.MMKVUtils.MMKVinit;


public class SplashActivity extends AppCompatActivity {
    private String TAG = "splash";

    private AlertDialog noNetDialog;

    private boolean bFirstOpenApp;
    private AgreementPop mAgreementPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        MMKVinit(this);
        // 初始化umeng
        UMConfigure.init(this, BuildConfig.UMENG_APP_KEY, BuildConfig.CHANNEL_ID, UMConfigure.DEVICE_TYPE_PHONE, null);
        UMConfigure.setLogEnabled(BuildConfig.DEBUG);
        // 初始化bugly
        Beta.initDelay = 1 * 1000;
        Beta.autoCheckUpgrade = false;
        //Beta.upgradeDialogLayoutId = R.layout.dialog_upgrade;
        Beta.enableNotification = true;
        Bugly.init(this, BuildConfig.BUGLY_APPID, BuildConfig.DEBUG);

        userLogin();

        //设置全屏模式开启
        ImmersionBar
                .with(this)
                .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)
                .init();
        MMKV.initialize(this);
    }

    private void userLogin() {
//        public void post(String url,String key,String value){

        UserDeviceInfoUtils userDeviceInfoUtils = new UserDeviceInfoUtils();

        userDeviceInfoUtils.setOS("Android");
        userDeviceInfoUtils.setIMEI("Android");
        userDeviceInfoUtils.setMAC("Android");
        userDeviceInfoUtils.setAndroidID("Android");
        userDeviceInfoUtils.setBrand("Android");
        userDeviceInfoUtils.setModel("Android");
        userDeviceInfoUtils.setSDKVersion("Android");
//        userDeviceInfoUtils.setIMEI(MobileInfoUtil.getIMEI(LaunchActivity.this));
//        userDeviceInfoUtils.setMAC(MobileInfoUtil.getMac(LaunchActivity.this));
//        userDeviceInfoUtils.setAndroidID(MobileInfoUtil.getAndroidID(LaunchActivity.this));
//        userDeviceInfoUtils.setBrand(MobileInfoUtil.getBrand());
//        userDeviceInfoUtils.setModel(MobileInfoUtil.getModel());
//        userDeviceInfoUtils.setSDKVersion(MobileInfoUtil.getSDKVersion());
        LogUtils.e(TAG, "api start");
        ApiUtils.getInstance().create().getUserInfo(new UserLoginResquest("111", userDeviceInfoUtils))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<UserLoginResponse>>() {
                    @Override
                    public void onCompleted() {
                        LogUtils.e(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "onError:" + e.getMessage());
                    }

                    @Override
                    public void onNext(BaseResponse<UserLoginResponse> req) {
                        LogUtils.e(TAG, req.toString());
                        if (req.getCode() == 200) {
                            try {
                                UserLoginResponse userInfo = req.getData();
                                UserInfoUtils.getInstance().setUserInfo(userInfo);
                            } catch (Exception e) {
                                ToastUtils.show("未获取到用户数据");
                                LogUtils.e(TAG, e.getMessage());
                            }
                        } else {
                            ToastUtils.show(req.getMsg());
                        }
                    }
                });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            // 检查网络
            startCheck();

        }
    }

    private void startCheck() {
        LogUtils.e(TAG, "check network");
        if (!NetUtils.isNetworkConnected(SplashActivity.this)) {
            showNoNetWorkDialog();
        } else {
            // 判断是否第一次打开
            bFirstOpenApp = MMKVUtils.GetBoolean(MMKVUtils.KEY_FIRST_OPEN_APP);
            LogUtils.e(TAG, String.valueOf(bFirstOpenApp));
            if (bFirstOpenApp) {
                popAgree();
            } else {
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        lannMain();
                    }
                };
                timer.schedule(task, 2 * 1000);
            }
        }
    }

    /**
     * 展示隐私权限弹窗
     */
    private void popAgree() {
        if (mAgreementPopupWindow == null) {
            mAgreementPopupWindow = new AgreementPop(this);
        }

        mAgreementPopupWindow.showAtCenter(new OnAgreementPopListener() {
            @Override
            public void onPopClose() {
                mAgreementPopupWindow.dismiss();
                //showAgreementPop2();
            }

            @Override
            public void onUserAgree() {
                MMKVUtils.PutBoolean(MMKVUtils.KEY_FIRST_OPEN_APP, false);
                lannMain();
            }

            @Override
            public void onExit() {
                mAgreementPopupWindow.dismiss();
                //showAgreementPop2();
                SplashActivity.this.finish();
            }
        });
    }

    private void showNoNetWorkDialog() {
        if (noNetDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("提示")
                    .setMessage("当前网络连接不可用").setPositiveButton("重试", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            startCheck();
                        }
                    }).setNegativeButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();

                            //跳转网络设置界面
                            Intent intent = new Intent(Settings.ACTION_SETTINGS);
                            startActivity(intent);
                        }
                    }).setCancelable(false);
            noNetDialog = builder.create();
        }
        if (!noNetDialog.isShowing()) {
            noNetDialog.show();
        }
    }

    private void lannMain () {
        // 跳转到main
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
    }
}
