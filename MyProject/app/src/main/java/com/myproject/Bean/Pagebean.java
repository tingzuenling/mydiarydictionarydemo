package com.myproject.Bean;

import java.util.List;

/**
 * Created by D1ngZenL1ng on 2016/11/20.
 */

public class Pagebean {
    private int allPages;
    private List<Contentlist> contentlist;
    private int currentPage;
    private int allNum;
    private int maxResult;

    @Override
    public String toString() {
        return "Pagebean{" +
                "allPages=" + allPages +
                ", contentlist=" + contentlist +
                ", currentPage=" + currentPage +
                ", allNum=" + allNum +
                ", maxResult=" + maxResult +
                '}';
    }

    public int getAllPages() {
        return allPages;
    }

    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    public List<Contentlist> getContentlist() {
        return contentlist;
    }

    public void setContentlist(List<Contentlist> contentlist) {
        this.contentlist = contentlist;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }
}
