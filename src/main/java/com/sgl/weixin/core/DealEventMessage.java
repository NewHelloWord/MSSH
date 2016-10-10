package com.sgl.weixin.core;

import com.sgl.weixin.MessageUtil;
import com.sgl.weixin.message.BaseMessage;
import com.sgl.weixin.message.TextMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by yaoliao on 2016/10/10.
 */
public class DealEventMessage {

    public static String dealMessage(String msgType, Map<String,Object> map) {
        String msg = "";
        String event = (String) map.get("Event");
        if(event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
            TextMessage tm = new TextMessage();
            tm.setContent("Welcome");
            tm.setCreateTime(new Date().getTime());
            tm.setFromUserName((String) map.get("ToUserName"));
            tm.setToUserName((String) map.get("FromUserName"));
            tm.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
            msg = MessageUtil.textMessageToXml(tm);

        }

        return msg;
    }
}
