package com.sgl.controller;

import com.sgl.SystemConfig;
import com.sgl.model.RegisterCode;
import com.sgl.model.User;
import com.sgl.service.RegisterCodeService;
import com.sgl.service.UserService;
import com.sgl.sms.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Yao on 2016/7/29.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private RegisterCodeService registerCodeService;



    @RequestMapping("/reg.htm")
    public String register(){
        return "pages/index";
    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String regist(ModelMap modelMap, User user, HttpServletRequest request,String code){
        String codeImg = (String) request.getSession().getAttribute("codeImg");
        RegisterCode rcode = registerCodeService.findRegisterCode(user.getUsername());
        User iuser = userService.findByUser(user);
        User u = new User();
        if(iuser != null){
            modelMap.addAttribute("user", null);
            modelMap.addAttribute("msg", "该手机号已被注册！");
            return "register";
        }
        if(!StringUtil.isEmpty(code) && code.equals(rcode.getCode())){
            user.setUid(UUID.randomUUID().toString());
            try {
                String salt = CryptoUtils.getSalt();
                String password = user.getPassword();
                u.setUsername(user.getUsername());
                u.setUid(salt);
                u.setPassword(CryptoUtils.getHash(password, salt));
                userService.addUser(u);
                u.setPassword("");
                modelMap.addAttribute("user", u);
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

    @RequestMapping(value = "/toLogin", method = RequestMethod.POST)
    public String login(ModelMap modelMap, User user, HttpServletRequest request,String code){
        String codeImg = (String) request.getSession().getAttribute("codeImg");
        User u = userService.findByUser(user);
        if(!StringUtil.isEmpty(code) && code.equals(codeImg) && u !=null){
            if(CryptoUtils.verify(u.getPassword(), user.getPassword(), u.getUid())){
                request.getSession().setAttribute("user",u);
                modelMap.addAttribute("user", u);
                modelMap.addAttribute("msg", "登录成功！");
                return "success";
            }
        }
        modelMap.addAttribute("msg", "用户名或密码错误！");
        return "login";
    }

    @RequestMapping(value = "/verification", method = RequestMethod.POST)
    @ResponseBody
    public String verificationCode(String code, HttpServletRequest request){
        String codeImg = (String) request.getSession().getAttribute("codeImg");
        AjaxMessage am = new AjaxMessage(false,"验证码错误!");
        if(!StringUtil.isEmpty(code) && code.equals(codeImg)){
            am = new AjaxMessage(true,"验证码正确!");
        }
        return am.toString();
    }

    @RequestMapping(value = "/mobile", method = RequestMethod.POST)
    @ResponseBody
    public String mobileCode(String mobile){
        AjaxMessage am = null;
        User u = new User();
        if(StringUtils.isEmpty(mobile)){
            am = new AjaxMessage(false, "手机号码不能为空！");
        }
        u.setUsername(mobile);
        User user = userService.findByUser(u);
        if(user != null){
            am = new AjaxMessage(false, "手机号码已被注册！");
        }else{
            //生成验证
            String code = VerificationUtil.getInstance().getCode();
            //------不正式发送
            //if(SmsUtil.sendMessage(mobile,"前方高能"+code+"，注意这不是演习!!")){
            if(true){
                System.out.println(mobile+"的验证码==="+code);
                RegisterCode c = new RegisterCode();
                c.setCode(code);
                c.setMobile(mobile);
                c.setCreatTime(new Date());
                c.setInvalidTime(DateUtils.changeDateMinute(2));
                c.setIsVaild("0");
                registerCodeService.saveRegisterCode(c);
                am = new AjaxMessage(true, "发送验证码成功！");
            }else{
                am = new AjaxMessage(false, "发送验证码失败！");
            }
        }
        return am.toString();
    }


}
