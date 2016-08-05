package com.sgl.sms;

import java.util.Arrays;

/**
 * Created by Yao on 2016/8/4.
 * 短信消息实体
 */
public class SmsInfo {

    //手机号码
    private String[] mobiles;
    //短信内容
    private String Content;

    public String[] getMobiles() {
        return mobiles;
    }

    public void setMobiles(String[] mobiles) {
        this.mobiles = mobiles;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public String toString() {
        return "SmsInfo{" +
                "mobiles=" + Arrays.toString(mobiles) +
                ", Content='" + Content + '\'' +
                '}';
    }
}
