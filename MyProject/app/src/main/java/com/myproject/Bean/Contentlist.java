package com.myproject.Bean;

import java.io.Serializable;
import java.util.*;

/**
 * Created by D1ngZenL1ng on 2016/11/20.
 */

public class Contentlist implements Serializable {
private String pubDate;
    private boolean havePic;
    private String title;
    private String channelName;
    private List<Imageurls> imageurls;
    private String desc;
    private String source;
    private String channelId;
    private Object[] allList;

    public Object[] getAllList() {
        return allList;
    }

    public void setAllList(Object[] allList) {
        this.allList = allList;
    }

    @Override
    public String toString() {
        return "Contentlist{" +
                "pubDate='" + pubDate + '\'' +
                ", havePic=" + havePic +
                ", title='" + title + '\'' +
                ", channelName='" + channelName + '\'' +
                ", imageurls=" + imageurls +
                ", desc='" + desc + '\'' +
                ", source='" + source + '\'' +
                ", channelId='" + channelId + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public boolean isHavePic() {
        return havePic;
    }

    public void setHavePic(boolean havePic) {
        this.havePic = havePic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Imageurls> getImageurls() {
        return imageurls;
    }

    public void setImageurls(List<Imageurls> imageurls) {
        this.imageurls = imageurls;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    private String link;
}
