package com.sgl.weixin;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JerryMouse on 2016/10/12.
 */
public class UploadUtil {

    //上传图片(返回的是: url)
    private static final String UPLOAD_IMG = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";

    //上传图片(返回的是: media_id)
    private static final String UPLOAD_IMG_MEDIA_ID = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

    //上传图文消息素材
    private static final String UPLOAD_NEWS = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";

    //根据标签进行群发【订阅号与服务号认证后均可用】
    private static final String SEND_BY_TAG = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";

    //新增临时素材
    private static final String UPLOAD_NOTALL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

    public static String postForm(String realUrl, String filePath) throws IOException {

        String result = "";
        String mediaId = "";


            File file = new File(filePath);
            if(!file.exists() || !file.isFile()){
                System.out.println("文件不存在");
                throw new IOException("文件不存在");
            }

            URL url = new URL(realUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false); // post方式不能使用缓存

            //设置请求头信息
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");

            // 设置边界
            String BOUNDARY = "----------" + System.currentTimeMillis();
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);

            // 请求正文信息
            // 第一部分：
            StringBuilder sb = new StringBuilder();
            sb.append("--"); // 必须多两道线
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
                    + file.getName() + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");

            byte[] head = sb.toString().getBytes("utf-8");
            // 获得输出流
            OutputStream out = new DataOutputStream(con.getOutputStream());
            // 输出表头
            out.write(head);

            // 文件正文部分
            // 把文件已流文件的方式 推入到url中
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();

            // 结尾部分
            byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

            out.write(foot);

            out.flush();
            out.close();

            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = null;
        try{
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                buffer.append(line);
            }

            if(result!=null){
                result = buffer.toString();
            }
        }catch (Exception e){
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
            throw new IOException("数据读取异常");
        }finally {
            if(reader!=null){
                reader.close();
            }
        }
        System.out.println(result);
        JSONObject jsonObj = JSONObject.fromObject(result);

        mediaId = jsonObj.getString("media_id");

        return mediaId;
    }


    public static void main (String[] args){

        //AccessToken accessToken = WeiXinUtil.getAccessToken("wx3c82aa68d1ec8686","09b9812e539d726697444a776d0626b0");

       //上传图片需要使用模拟 form 的 post 请求----------------------------
        String token = "yl6wOij9tWsppT7vDFUV5Xx34XuvxqcMqqCxUaPJT9CarHWnDGAQi-YxFv0bC9ppFHEgYkIyYbRkDZSFwLIKr1YBJ6upFGrFk2N0swL9JOWSpwZt8KzL5Pt7MtykJGj8CIShAAAGMW";
//        String url = UPLOAD_IMG_MEDIA_ID.replace("ACCESS_TOKEN",token).replace("TYPE","image");
//        String imgUrl = "E:/IdeaProjects/MSSH/target/MSSH/resources/images/wlop/green.jpg";
//        try {
//            String mediaId = postForm(url,imgUrl);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

         //上传图文消息素材--------------------------------------------------
//        String imgMediaId = "-tf-CdXlHmeBznTdhYRuls9MlJU5lGanBIaOyI1RjNXXNw-6ryHAxayn_lC0t7Id";  //上传的小绿的图片
//
//        Map<String,String> map = new HashMap<String, String>();
//        map.put("thumb_media_id",imgMediaId);
//        map.put("author","YL(author)");   //不是必须的
//        map.put("title","鬼刀(title)");
//        map.put("content_source_url","http://baike.baidu.com/link?url=Pbo-gUq8EFxfM25h-JmHGsrsH-6M-x7sZobJFq_WhiCv0VWcocxCiBK_jt5IwU1T2Dp0qxWsnPAgh-ZkgIEPeYTogivroOoDIeX0XCAqgAu");  //不是必须的
//        map.put("content","这里是鬼刀的介绍文章哦......(content)");
//        map.put("digest","鬼刀(图文消息的描述)(digest)");     //不是必须的
//        map.put("show_cover_pic","1");    //不是必须的  是否显示封面，1为显示，0为不显示
//
//        Map<String,String> map1 = new HashMap<String, String>();
//        map1.put("thumb_media_id",imgMediaId);
//        map1.put("author","YL(author)");   //不是必须的
//        map1.put("title","鬼刀1(title)");
//        map1.put("content_source_url","http://baike.baidu.com/link?url=Pbo-gUq8EFxfM25h-JmHGsrsH-6M-x7sZobJFq_WhiCv0VWcocxCiBK_jt5IwU1T2Dp0qxWsnPAgh-ZkgIEPeYTogivroOoDIeX0XCAqgAu");  //不是必须的
//        map1.put("content","这里是鬼刀的介绍文章哦哦哦哦......(content)");
//        map1.put("digest","鬼刀(图文消息的描述)(digest)");     //不是必须的
//        map1.put("show_cover_pic","1");    //不是必须的  是否显示封面，1为显示，0为不显示
//
//        List<Map<String,String>> list = new ArrayList<Map<String, String>>();
//        list.add(map);
//        list.add(map1);
//
//        Map<String,Object> articles = new HashMap<String, Object>();
//        articles.put("articles",list);
//
//        String json = String.valueOf(JSONObject.fromObject(articles));
//        System.out.println(json);
//
//        String url = UPLOAD_NEWS.replace("ACCESS_TOKEN",token);
//        String result = HttpUtil.doPost(url,json);
//        System.out.println(result);
//
//        JSONObject jsonObj = JSONObject.fromObject(result);
//        String mediaId = jsonObj.getString("media_id");
//        System.out.println(mediaId);

         // 开发者没权限群发消息-----------------------------  狗腾讯 --------
//        String newMediaId = "0wgcJ3nEGZLCZU3L8fVJN6vTR-xpqNPmhAd2SaEQioJGJyq5DKquQSyjoOIYZ8Bt";
//        String url = SEND_BY_TAG.replace("ACCESS_TOKEN",token);
//
//        Map<String,Object> map = new HashMap<String, Object>();
//        map.put("is_to_all",true);
//
//        Map<String,Object> map1 = new HashMap<String, Object>();
//        map1.put("media_id",newMediaId);
//
//        Map<String,Object> all = new HashMap<String, Object>();
//        all.put("filter",map);
//        all.put("mpnews",map1);
//        all.put("msgtype","mpnews");





        String imgMediaId = "LSNL80nhcygLDQZiqggIpmGBH5ExbVVS5Z4MDaxnEVdHrlGqOvkGExBf8QnBqIjR";  //上传的小绿的图片
        String url = SEND_BY_TAG.replace("ACCESS_TOKEN",token);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("is_to_all",true);

//        Map<String,Object> map1 = new HashMap<String, Object>();
//        map1.put("media_id",imgMediaId);

        Map<String,Object> map1 = new HashMap<String, Object>();
        map1.put("content","发个图文消息还要钱，GOU B TENG—XUN，用心创造快乐，没钱玩尼嘛B");

        Map<String,Object> all = new HashMap<String, Object>();
        all.put("filter",map);
        all.put("text",map1);
        all.put("msgtype","text");


        String json = JSONObject.fromObject(all).toString();
        System.out.println(json);

        String result = HttpUtil.doPost(url,json);
        System.out.println(result);

    }

}
