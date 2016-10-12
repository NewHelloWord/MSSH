package com.sgl.weixin;

import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by JerryMouse on 2016/10/12.
 */
public class UploadUtil {

    public static String postForm(String realUrl, String filePath) {

        String result = "";
        String mediaId = "";

        try{
            File file = new File(filePath);
            if(!file.exists() || !file.isFile()){
                System.out.println("文件不存在");
                return mediaId;
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
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line = null;
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                buffer.append(line);
            }

            if(result==null){
                result = buffer.toString();
            }
            if(reader!=null){
                reader.close();
            }

            JSONObject jsonObj = JSONObject.fromObject(result);
            mediaId = jsonObj.getString("media_id");
        }catch (Exception e){

        }

        return mediaId;
    }

}
