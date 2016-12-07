package com.myproject.Bean;

import com.google.gson.JsonObject;

/**
 * Created by D1ngZenL1ng on 2016/11/25.
 */

public class TranslateBody {
    private int ret_code;

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    private Basic basic;



}
