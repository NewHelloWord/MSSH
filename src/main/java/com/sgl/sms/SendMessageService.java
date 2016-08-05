package com.sgl.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Yao on 2016/8/4.
 */
public class SendMessageService implements Runnable {

    private static String SUCCESS_CODE = "result=\"1\"";

    public void run() {

        String reqUrl = "http://agblbajfcbflevhnvnwergohevgbrgupweigbhwebsdopfnhiwegfrvoweyfhwopieufgvweoifghtwegihbwergbewghwefnbvweuilf";
        SmsInfo smsInfo = null;
        String[] mobiles = null;
        String smsContent = null;

        while (true){

            String onceUrl = reqUrl;
            String result = "";

            try {
                smsInfo = SmsQueue.getInstance().getSMSInfo().take();
                mobiles = smsInfo.getMobiles();
                smsContent = smsInfo.getContent();
                if(mobiles != null && mobiles.length > 0){
                    onceUrl += URLEncoder.encode(smsContent,"UTF-8")+"&m=";
                    for(String mobile : mobiles){
                        onceUrl += mobile + ",";
                    }
                    onceUrl = onceUrl.substring(0 , onceUrl.length() - 1 );
                    result = send(onceUrl);
                    if(result.indexOf(SUCCESS_CODE) < 0){
                        System.out.println("发送失败");
                        send(onceUrl);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public String send(String reqUrl){

        HttpURLConnection httpURLConn = null;
        String result = "";

        try {
            URL url = new URL(reqUrl);
            httpURLConn = (HttpURLConnection) url.openConnection();
            httpURLConn.setDoInput(true);
            httpURLConn.setDoOutput(true);
            httpURLConn.setRequestMethod("GET");
            httpURLConn.connect();
            InputStream in = httpURLConn.getInputStream();
            BufferedReader bd = new BufferedReader(new InputStreamReader(in));

            String line = null;
            while ((line = bd.readLine()) != null){
                result += line;
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = "";
        }finally {
            if(httpURLConn != null){
                httpURLConn.disconnect();
            }
        }

        return result;
    }
}
