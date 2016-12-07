package com.myproject.Bean;

/**
 * Created by D1ngZenL1ng on 2016/11/20.
 */

public class Body {
    private int ret_code;

    public Pagebean getPagebean() {
        return pagebean;
    }

    public void setPagebean(Pagebean pagebean) {
        this.pagebean = pagebean;
    }

    @Override
    public String toString() {
        return "Body{" +
                "ret_code=" + ret_code +
                ", pagebean=" + pagebean +
                '}';
    }

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    private Pagebean pagebean;
}
