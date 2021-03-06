package com.sgl.weixin.core;

import com.sgl.weixin.MessageUtil;
import com.sgl.weixin.message.BaseMessage;
import com.sgl.weixin.message.TextMessage;

import java.util.Date;
import java.util.Map;

/**
 * Created by JerryMouse on 2016/10/10.
 *
 * 普通事件具体处理类
 */
public class DealCommonMessage {

    public static String dealMessage(String msgType, Map<String,Object> map) {
        String msg = "";
        if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
            String content = (String) map.get("Content");
            TextMessage tm = new TextMessage();
            tm.setContent("你输入的是："+content);
            tm.setCreateTime(new Date().getTime());
            tm.setFromUserName((String) map.get("ToUserName"));
            tm.setToUserName((String) map.get("FromUserName"));
            tm.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
            msg = MessageUtil.textMessageToXml(tm);

        }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)){

        }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)){

        }

        return msg;
    }

}
