package com.yg.fundrink.DataList.Response;

/**
 * @author go
 * time：2020/11/2
 * describe:
 */
public class MyListItem {
    private String title;
    private int left_iconid;
    private int right_imgid;
    private String version;

    public MyListItem(String title, int left_iconid, int right_imgid){
        this.title = title;
        this.left_iconid = left_iconid;
        this.right_imgid = right_imgid;
        this.version = "";
    }

    public MyListItem(String title, int left_iconid, int right_imgid, String version){
        this.title = title;
        this.left_iconid = left_iconid;
        this.right_imgid = right_imgid;
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLeft_iconid() {
        return left_iconid;
    }

    public void setLeft_iconid(int left_iconid) {
        this.left_iconid = left_iconid;
    }

    public int getRight_imgid() {
        return right_imgid;
    }

    public void setRight_imgid(int right_imgid) {
        this.right_imgid = right_imgid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
