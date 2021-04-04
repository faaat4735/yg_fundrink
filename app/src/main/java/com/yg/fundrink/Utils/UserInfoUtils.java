package com.yg.fundrink.Utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.yg.fundrink.DataList.Response.UserLoginResponse;

/**
 * @author go
 * time：2020/10/16
 * describe:
 */
public class UserInfoUtils
{
    private String KEY_USER_SAVE = "USERINFO";

    private static UserInfoUtils userInfoUtilsInstance = new UserInfoUtils();
    private UserLoginResponse userInfo;

    public UserLoginResponse getUserInfo() {
        if (userInfo == null){
            String savedPlayer = MMKVUtils.GetString(KEY_USER_SAVE);
            if (!TextUtils.isEmpty(savedPlayer)) {
                userInfo = new Gson().fromJson(savedPlayer, UserLoginResponse.class);
            }
            if (userInfo == null) {
                return null;
            }
        }
        return userInfo;
    }

    public void setUserInfo(UserLoginResponse userInfo) {
        String user_str = "";
        if (userInfo != null) {
            user_str = new Gson().toJson(userInfo);
        }
        MMKVUtils.PutString(KEY_USER_SAVE, user_str);
        this.userInfo = userInfo;
    }

    public static UserInfoUtils getInstance(){
        return userInfoUtilsInstance;
    }

    //更新玩家总的金币
    public void UpdatePlayerTotalCoins(int coins){
        UserLoginResponse userInfo = getUserInfo();
        if (userInfo != null){
            userInfo.setCurrentGold(coins);
            setUserInfo(userInfo);
        }
    }

}
