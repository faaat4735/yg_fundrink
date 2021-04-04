package com.yg.fundrink.DataList.Resquest;

import com.yg.fundrink.Utils.UserDeviceInfoUtils;

public class UserLoginResquest {
    private String deviceId;

    private UserDeviceInfoUtils userDeviceInfo;

    public UserLoginResquest(String deviceId, UserDeviceInfoUtils userDeviceInfo) {
        this.deviceId = deviceId;
        this.userDeviceInfo = userDeviceInfo;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public UserDeviceInfoUtils getUserDeviceInfo() {
        return userDeviceInfo;
    }

    public void setUserDeviceInfo(UserDeviceInfoUtils userDeviceInfo) {
        this.userDeviceInfo = userDeviceInfo;
    }
}
