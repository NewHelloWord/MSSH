package com.sgl.sms;

/**
 * Created by Yao on 2016/8/4.
 * 短信发送工具类
 */
public class SmsUtil {

    public static boolean sendMessage(String mobile,String text){
        String[] mobiles = {mobile};
        SmsInfo smsInfo = new SmsInfo();
        smsInfo.setMobiles(mobiles);
        smsInfo.setContent(text);
        return SmsService.getInstance().sendMessageService(smsInfo);
    }

    public static boolean sendMessage(String[] mobiles, String text){
        SmsInfo smsInfo = new SmsInfo();
        smsInfo.setMobiles(mobiles);
        smsInfo.setContent(text);
        return SmsService.getInstance().sendMessageService(smsInfo);
    }

//    public static void main(String[] args){
//        boolean b = SmsUtil.sendMessage("18357876563","123456");
//        System.out.println(b);
//    }
}
