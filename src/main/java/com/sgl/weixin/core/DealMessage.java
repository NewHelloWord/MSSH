package com.sgl.weixin.core;

import com.sgl.weixin.MessageUtil;
import com.sgl.weixin.message.BaseMessage;

/**
 * Created by JerryMouse on 2016/10/10.
 *
 */
public class DealMessage {
    public static String dealMessage(String msgType, BaseMessage bs){
        String msg = "";
        if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){   //接收事件推送
            msg = DealEventMessage.dealMessage(msgType,bs);
        }else{   //接受普通消息
            msg = DealCommonMessage.dealMessage(msgType,bs);
        }
        return msg;
    };
}
