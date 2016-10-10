package com.sgl.weixin.core;

import com.sgl.SystemConfig;
import com.sgl.weixin.MessageUtil;
import com.sgl.weixin.message.BaseMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by JerryMouse on 2016/10/10.
 */
public class CoreService {

    public String processRequest(HttpServletRequest request, HttpSession session){
        String resMessage = "";

        try {
            Map<String,Object> map = MessageUtil.parseXml(request);

            String ToUserName = (String) map.get("ToUserName");      //开发者微信号
            String fromUserName = (String) map.get("FromUserName");  //发送方帐号（一个OpenID）
            Long CreateTime = (Long)map.get("CreateTime");       //消息创建时间 （整型）
            String MsgType = (String)map.get("MsgType");             //消息类型

            BaseMessage bs = new BaseMessage(ToUserName,fromUserName,CreateTime,MsgType,0);
            resMessage = DealMessage.dealMessage(MsgType,bs);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMessage;
    }

}
