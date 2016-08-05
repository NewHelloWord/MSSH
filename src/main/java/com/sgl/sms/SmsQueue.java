package com.sgl.sms;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Yao on 2016/8/4.
 * 短信发送队列
 */
public class SmsQueue {

    private static SmsQueue instance = new SmsQueue();

    private BlockingQueue<SmsInfo> mailQueue = new ArrayBlockingQueue<SmsInfo>(1000);

    public static SmsQueue getInstance(){
        return instance;
    }

    public BlockingQueue<SmsInfo> getSMSInfo(){
        return mailQueue;
    }

}
