package com.myproject.Httputils;

/**
 * Created by D1ngZenL1ng on 2016/11/20.
 */

public class Httputils {
    public static final String URL_REQUEST="http://route.showapi.com/109-35";
    public static final String APPID="27528";
    public static final String APPSECRET="c5b3f5c9abcf4bae9164dcca51c4587b";
    public static final String PAGE="1";
    public static final String NEEDCONTENT="0";
    public static final String NEEDHTML="0";
    public static final String NEEDALLLIST="1";
    public static final String MAXRESULT="20";
    public static final String URL_TRANSLATE="http://route.showapi.com/32-9";

    public static String channelName;
    public static String title;

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        Httputils.title = title;
    }

    public static String getChannelName() {
        return channelName;
    }

    public static void setChannelName(String channelName) {
        Httputils.channelName = channelName;
    }
}
