package com.sgl.controller;

import com.sgl.weixin.SignUtil;
import com.sgl.weixin.core.CoreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
@RequestMapping(value = "/token")
public class TokenController {

    @RequestMapping(value = "/checkToken.htm", method = RequestMethod.GET)
    public void checkToken(HttpServletRequest request, PrintWriter out){

        String signature = request.getParameter("signature");    //微信加密签名
        String timestamp = request.getParameter("timestamp");   //时间戳
        String nonce = request.getParameter("nonce");    //随机数
        String echostr = request.getParameter("echostr");   //随机字符串
        //System.out.println("signature=================="+signature);
        //boolean isCheck = SignUtil.checkSign(signature,timestamp,nonce);
        if(SignUtil.checkSign(signature,timestamp,nonce)){
            out.print(echostr);
        }else {
            System.out.println("假的消息来源");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/checkToken.htm", method = RequestMethod.POST, produces = "application/xml;charset=UTF-8")
    public void getMessage(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        BufferedReader in = null;
        String result = "";
        PrintWriter out = null;

        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            in = new BufferedReader(new InputStreamReader(request.getInputStream()));
            out = response.getWriter();

            // 调用核心业务类接收消息、处理消息
            CoreService coreService = new CoreService();
            result = coreService.processRequest(request,session);
            out.print(result);
            out.flush();

            String line = "";
            while ((line = in.readLine()) != null){
                result += line;
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(in != null){
                    in.close();
                }
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
