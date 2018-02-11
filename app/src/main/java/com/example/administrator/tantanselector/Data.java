package com.example.administrator.tantanselector;

/**
 * Created by Administrator on 2018/2/8.
 */

public class Data {
    String url ;
    String name ;

    public Data(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
