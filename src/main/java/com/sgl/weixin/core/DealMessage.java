package com.sgl.weixin.core;

import com.sgl.weixin.MessageUtil;
import com.sgl.weixin.message.BaseMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by JerryMouse on 2016/10/10.
 *
 */
public class DealMessage {
    public static String dealMessage(String msgType, Map<String,Object> map){
        String msg = "";
        if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){   //接收事件推送
            msg = DealEventMessage.dealMessage(msgType,map);
        }else{   //接受普通消息
            msg = DealCommonMessage.dealMessage(msgType,map);
        }
        return msg;
    };
}
