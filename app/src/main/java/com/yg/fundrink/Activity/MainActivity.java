package com.yg.fundrink.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yg.fundrink.Fragment.HistoryFragment;
import com.yg.fundrink.Fragment.HomeFragment;
import com.yg.fundrink.Fragment.MyFragment;
import com.yg.fundrink.R;

public class MainActivity extends AppCompatActivity {
    private Fragment currentFragment = new Fragment();
    private Fragment HomeFragment = new HomeFragment();
    private Fragment HistoryFragment = new HistoryFragment();
    private Fragment MyFragmet = new MyFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchFragment(HomeFragment);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
              switch (menuItem.getItemId()) {
                  case R.id.nav_home:
                      switchFragment(HomeFragment);
                      break;
                  case R.id.nav_history:
                      switchFragment(HistoryFragment);
                      break;
                  case R.id.nav_my:
                      switchFragment(MyFragmet);
                      break;
              }

              return true;
            }
        });
    }

    private void switchFragment (Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment.isAdded()) {
            transaction.hide(currentFragment)
                    .show(fragment)
                    .commit();

        } else {
            transaction.hide(currentFragment)
                    .add(R.id.main_content, fragment)
                    .commit();
        }
        currentFragment = fragment;
    }

    public void onViewChange (String type, String url) {
        this.onViewChange(type, url, "");
    }

    public void onViewChange(String type, String url, String title) {
        Log.e("onViewChange", "type = " + type + " url = " + url);
        switch (type) {
//            case "interior":
//                switch (url) {
//                    case "walkStage":       //??????????????????
//                        StepAwardActivity.launch(MainActivity.this);
//                        break;
//                    case "clockIn":         //????????????
//                        DayDrinkActivity.launch(MainActivity.this);
//                        break;
//                    case "invited":         //????????????
//                        InviteFriendActivity.launch(MainActivity.this);
//                        break;
//                    case "sport":         //??????
//                        SportEarnActivity.launch(MainActivity.this);
//                        break;
//                    case "lottery":        //??????
//                        LotteryActivity.launch(MainActivity.this);
//                        break;
//                    case "task":
//                        switchView(2);
//                        break;
//                    case "liveness":
//                        switchView(1);
//                        break;
//                    case "index":
//                        switchView(0);
//                        break;
//                    default:
//                        ToastUtils.show("unknown internal");
//                        break;
//                }
//                break;

//            case "task":
//                switch (url){
//                    case "wechat":          //????????????
////                        if (model.getInfo().getIsBuild() == 1) {
////                            return;
////                        }
////                        if (PlayerManger.getInstance().getPlayerInfo().getHeadimgurl().length() != 0){
////                            ToastUtils.show("??????????????????");
////                            return;
////                        }
//                        UMShareAPI
//                                .get(MainActivity.this)
//                                .getPlatformInfo(MainActivity.this, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
//                                    @Override
//                                    public void onStart(SHARE_MEDIA share_media) {
//                                        Log.e("Main", "onStart: ");
//                                    }
//
//                                    @Override
//                                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
//                                        Log.e("Main", "onComplete: ");
//                                        for (String s : map.keySet()) {
//                                            Log.e("Main", "onComplete: " + s + " = " + map.get(s));
//                                        }
//                                        PlayerWxInfo playerWxInfo = new PlayerWxInfo();
//                                        playerWxInfo.setCity(map.get("city"));
//                                        playerWxInfo.setCountry(map.get("country"));
//                                        playerWxInfo.setUnionid(map.get("unionid"));
//
//                                        int sex = -1;
//                                        if (map.get("gender").endsWith("???")) {
//                                            sex = 1;
//                                        } else if (map.get("gender").endsWith("???")) {
//                                            sex = 0;
//                                        }
//
//                                        playerWxInfo.setSex(sex);
//                                        playerWxInfo.setOpenid(map.get("openid"));
//                                        playerWxInfo.setLanguage(map.get("language"));
//                                        playerWxInfo.setHeadimgurl(map.get("profile_image_url"));
//                                        playerWxInfo.setProvince(map.get("province"));
//                                        playerWxInfo.setNickname(map.get("screen_name"));
//                                        LogUtils.e("test====== ", playerWxInfo.toString());
//
//                                        m_HeadUrl = map.get("profile_image_url");
//
//                                        onBindWeChat(playerWxInfo);
//                                    }
//
//                                    @Override
//                                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
//                                        Log.e("Main", "onError: ");
//                                    }
//
//                                    @Override
//                                    public void onCancel(SHARE_MEDIA share_media, int i) {
//                                        Log.e("Main", "onCancel: ");
//                                    }
//                                });
//                        break;
//                    case "invitedCode":     //???????????????
//                        InviteCodeCommitPop writeInviteCodePop = new InviteCodeCommitPop(MainActivity.this);
//                        writeInviteCodePop.setOnSubmitListener(new InviteCodeCommitPop.OnSubmitListener() {
//                            @Override
//                            public void onSubmit(String content) {
//                                onSubmitInviteCode(content);
//                            }
//                        });
//                        writeInviteCodePop.show(null);
//                        break;
//                    case "video_1":
//                        break;
//                    case "video_2":
//                        break;
//                    case "video_3":
//                        break;
//                    default:
//                        ToastUtils.show("unknown task");
//                        break;
//                }
//                break;
            case "myfragment":
                switch (url){
                    case "advice":              //????????????
                        AdviceActivity.launch(MainActivity.this);
                        break;
//                    case "withdraw":            //????????????
//                        //DrawCashActivity.launch(MainActivity.this);
//                        DrawCashAlipayActivity.launch(MainActivity.this);
//                        break;
//                    case "withdrawdetail":      //????????????
//                        DrawCashDetailActivity.launch(MainActivity.this);
//                        break;
//                    case "golddetail":          //????????????
//                        GoldDetailActivity.launch(MainActivity.this);
//                        break;
//                    default:
//                        ToastUtils.show("unknown task");
//                        break;
                }
                break;
            case "web":
                WebActivity.launch(MainActivity.this, title, url);
                break;
//            case "sdk":
//                switch(url){
//                    case "dl_game":              //??????
//                        DyAdApi.getDyAdApi().jumpAdList(this, DeviceInfoHelper.getDeviceId(this), 0);
//                        break;
//                    case "op_novel":              //??????
//                        if(AdSdk.getSingleton().toNovel(this, "8RFmsALtMIGYfJ7PXoihyC3aOxKpb1jZ")){
//                            Log.e("AdSdk", "????????????");
//                        }else {
//                            Log.e("AdSdk", "????????????");
//                        }
//                        break;
//                }
//                break;
            default:
                Log.e("onViewChange", "unknown type task");
                break;
        }
    }
}