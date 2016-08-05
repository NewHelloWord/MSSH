package com.sgl.sms;

/**
 * Created by Yao on 2016/8/4.
 */
public class SmsService{

    //消息发送队列启动标志
    private static boolean flag = true;

    private static SmsService instance = new SmsService();

    public static SmsService getInstance(){
        return instance;
    }

    public boolean sendMessageService(SmsInfo smsInfo){
        try {
            addSMSQueue(smsInfo);
            if(flag = true){
                SendMessageService sm = new SendMessageService();
                Thread thread = new Thread(sm);
                thread.start();
                flag = false;
            }
        }catch (Exception e){
            System.out.println("SmsService 发送消息失败------------");
            return false;
        }
        return true;
    }

    //将消息加入发送队列
    private boolean addSMSQueue(SmsInfo smsInfo){
        if(!SmsQueue.getInstance().getSMSInfo().offer(smsInfo)){
            System.out.println("将消息加入发送队列失败--------");
            return false;
        }
        return true;
    }

}
