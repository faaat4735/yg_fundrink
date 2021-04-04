package com.yg.fundrink.DataList.Response;


public class UserLoginResponse {
    private String nickname;        //用户昵称
    private String headimgurl;      //用户头像
    private int currentGold;        //用户当前金币数
    private String accessToken;     //用户token
    private String invitedCode;     //用户邀请码
    private int todayGold;          //今日获得的总金币
    private int newerAward;         //新手奖励 奖励大于0 则弹出新手弹窗 领取
    private int adClick;            //点击几次菜单栏出现插屏广告

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public int getCurrentGold() {
        return currentGold;
    }

    public void setCurrentGold(int currentGold) {
        this.currentGold = currentGold;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getInvitedCode() {
        return invitedCode;
    }

    public void setInvitedCode(String invitedCode) {
        this.invitedCode = invitedCode;
    }

    public int getTodayGold() {
        return todayGold;
    }

    public void setTodayGold(int todayGold) {
        this.todayGold = todayGold;
    }

    public int getNewerAward() {
        return newerAward;
    }

    public void setNewerAward(int newerAward) {
        this.newerAward = newerAward;
    }

    public int getAdClick() {
        return adClick;
    }

    public void setAdClick(int adClick) {
        this.adClick = adClick;
    }

    @Override
    public String toString() {
        return "UserLoginResponse{" +
                "nickname='" + nickname + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", currentGold=" + currentGold +
                ", accessToken='" + accessToken + '\'' +
                ", invitedCode='" + invitedCode + '\'' +
                ", todayGold=" + todayGold +
                ", newerAward=" + newerAward +
                ", adClick=" + adClick +
                '}';
    }
}