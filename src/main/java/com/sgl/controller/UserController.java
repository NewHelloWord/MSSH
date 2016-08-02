package com.sgl.controller;

import com.sgl.model.User;
import com.sgl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.AjaxMessage;
import utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by Yao on 2016/7/29.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;



    @RequestMapping("/reg.htm")
    public String register(){
        return "pages/index";
    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String codeImg(ModelMap modelMap, User user, HttpServletRequest request,String code){
        String codeImg = (String) request.getSession().getAttribute("codeImg");
        if(!StringUtil.isEmpty(code) && code.equals(codeImg)){
            user.setUid(UUID.randomUUID().toString());
            try {
                userService.addUser(user);
                modelMap.addAttribute("user", user);
                modelMap.addAttribute("msg", "注册成功了，可以去登陆了");
                return "success";
            }catch (Exception e){
                modelMap.addAttribute("user", null);
                modelMap.addAttribute("msg", "注册失败");
                return "error";
            }
        }
        return "error";
    }

    @RequestMapping(value = "/verification", method = RequestMethod.POST)
    @ResponseBody
    public String verificationCode(String code, HttpServletRequest request){
        String codeImg = (String) request.getSession().getAttribute("codeImg");
        AjaxMessage am = new AjaxMessage(false,"验证码错误!");
        if(!StringUtil.isEmpty(code) && code.equals(codeImg)){
            am = new AjaxMessage(true,"验证码正确!");
        }
        System.out.println(am.toString());
        return am.toString();
    }

}
