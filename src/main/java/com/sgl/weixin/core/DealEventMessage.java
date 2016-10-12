package com.sgl.weixin.core;

import com.sgl.weixin.MessageUtil;
import com.sgl.weixin.message.Article;
import com.sgl.weixin.message.NewsMessage;
import com.sgl.weixin.message.TextMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoliao on 2016/10/10.
 */
public class DealEventMessage {

    public static String dealMessage(String msgType, Map<String, Object> map) {
        String msg = "";
        String event = (String) map.get("Event");
        if (event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {

            TextMessage tm = new TextMessage();
            tm.setContent("Welcome");
            tm.setCreateTime(new Date().getTime());
            tm.setFromUserName((String) map.get("ToUserName"));
            tm.setToUserName((String) map.get("FromUserName"));
            tm.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
            msg = MessageUtil.textMessageToXml(tm);

        } else if (event.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {

        } else if (event.equals(MessageUtil.EVENT_TYPE_CLICK)) {

            String EventKey = (String) map.get("EventKey");
            if(EventKey.equals("green")){

                NewsMessage nm = new NewsMessage();
                List<Article> articles = new ArrayList<Article>();
                Article article = new Article();
                article.setDescription("鬼刀-wlop-小绿");
                article.setPicUrl("http://yl12345.vicp.net/resources/images/wlop/green.jpg");
                article.setTitle("鬼刀");
                article.setUrl("http://weibo.com/wlop?is_hot=1");
                articles.add(article);

                Article article1 = new Article();
                article1.setDescription("灯2");
                article1.setPicUrl("http://yl12345.vicp.net/resources/images/wlop/light.jpg");
                article1.setTitle("鬼刀2");
                article1.setUrl("https://www.hacg.li/");
                articles.add(article1);

                nm.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                nm.setCreateTime(new Date().getTime());
                nm.setFromUserName((String) map.get("ToUserName"));
                nm.setToUserName((String) map.get("FromUserName"));
                nm.setFuncFlag(0);
                nm.setArticles(articles);
                nm.setArticleCount(articles.size()+"");
                msg = MessageUtil.newsMessageToXml(nm);



            }


        }else if (event.equals(MessageUtil.EVENT_TYPE_VIEW)) {

        }

        return msg;
    }
}
