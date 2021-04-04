package com.yg.fundrink.Utils;

public class UserDeviceInfoUtils {
    //Android/iOS
    private String OS;

    private String IMEI;

    private String MAC;

    private String AndroidID;

    private String OAID;

    private String brand; //手机品牌

    private String model;  //手机型号

    private String SDKVersion; //sdk版本号

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    public String getAndroidID() {
        return AndroidID;
    }

    public void setAndroidID(String androidID) {
        AndroidID = androidID;
    }

    public String getOAID() {
        return OAID;
    }

    public void setOAID(String OAID) {
        this.OAID = OAID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSDKVersion() {
        return SDKVersion;
    }

    public void setSDKVersion(String SDKVersion) {
        this.SDKVersion = SDKVersion;
    }

    @Override
    public String toString() {
        return "UserDeviceInfoUtils{" +
                "OS='" + OS + '\'' +
                ", IMEI='" + IMEI + '\'' +
                ", MAC='" + MAC + '\'' +
                ", AndroidID='" + AndroidID + '\'' +
                ", OAID='" + OAID + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", SDKVersion='" + SDKVersion + '\'' +
                '}';
    }
}
