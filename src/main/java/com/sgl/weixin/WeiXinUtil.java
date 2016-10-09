package com.sgl.weixin;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeiXinUtil {

    /**
     * 获取access_token的接口地址（GET） 限200（次/天）
     */
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 获取公众号关注者的列表（GET）
     */
    public final static String getUser_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";

    /**
     * 发送客服消息（包括文本 ， 图文 ， 多图文）
     */
    public final static String send_message_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

    /**
     * 创建自定义菜单
     */
    public final static String create_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 删除自定义菜单
     */
    public final static String delcreate_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    /**
     * 查询自定义菜单
     */
    public final static String find_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

    /**
     * 获取公众号粉丝的appid
     */
    public final static String find_openid_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    /**
     * 网页授权
     */
    public final static String oauth2 = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    /**
     * 获取用户信息
     * @param args
     */
    public final static String get_user_info = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";


    public static void main(String[] args){
        //AccessToken at = getAccessToken("wx3c82aa68d1ec8686","09b9812e539d726697444a776d0626b0");
        //String s = "{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"视频\",\"url\":\"http://v.qq.com/\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}";
        String token = "hkbJ1n5aWKf3jHZgF4dP_hjef9TdGhLZhh2CzgkKTZErOfATm0h-09MIcEQG3Mn3G69jAMhyVAL3UsM8RZh5ZURi-wtBvWJyreIvR5l3biQVd1BnrjuRFI0sy8lBk9eDSNTgACAXSY";
        String requestUrl = create_menu_url.replace("ACCESS_TOKEN", token);

        Button b = new Button();
        b.setName("电影");
        b.setType("view");
        b.setUrl("http://www.douyu.com/");

        Button b2 = new Button();
        b2.setName("数学");
        b2.setType("view");
        b2.setUrl("http://www.soso.com/");

        Button bu = new Button();
        bu.setName("在线影院");
        List<Button> list = new ArrayList<Button>();
        list.add(b);
        bu.setSub_button(list);

        Button bu1 = new Button();
        bu1.setName("初中");
        List<Button> list1 = new ArrayList<Button>();
        list1.add(b2);
        list1.add(b);
        bu1.setSub_button(list1);

        List<Button> all = new ArrayList<Button>();
        all.add(bu);
        all.add(bu1);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("button",all);
        String s = JSONObject.fromObject(map).toString();
        System.out.println(s);

        //String requestUrl = delcreate_menu_url.replace("ACCESS_TOKEN", token);
        String st = HttpUtil.doPost(requestUrl,s);
        System.out.println(st);
    }

    /**
     * 获取access_token
     *
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static AccessToken getAccessToken(String appid, String appsecret){
        AccessToken token = null;
        String url = access_token_url.replace("APPID",appid).replace("APPSECRET",appsecret);
        String result = HttpUtil.doGet(url,null);
        if(result != null){
            try{
                JSONObject json = JSONObject.fromObject(result);
                token = new AccessToken();
                token.setAccess_token(json.getString("access_token"));
                token.setExpires_in(json.getInt("expires_in"));
                System.out.println("access_token========="+token.getAccess_token()+"time===="+token.getExpires_in());
            }catch (Exception e){
                System.out.println("获取access_token失败");
                token = null;
            }
        }
        return token;
    }

}
