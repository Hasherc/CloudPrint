package com.hasherc.controller;

import Util.JsonUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hasherc.consts.StatusCode;
import com.hasherc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * 处理用户登录注册
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String LoginRegister(HttpSession session) {
        if (session.getAttribute("uuid") == null) return "main";
        else return "main";

    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    /**
     * @param registerBody requestBody
     * @return 登录成功 status = 1
     * 浏览器传输参数有误 status = 1001
     * 登录过程中出现错误 status =1002
     * 用户已存在 status = 1007
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestBody String registerBody) {
        String result = userService.register(registerBody);
        System.out.println(result);
        return result;

    }

    /**
     * @param loginBody
     * @return 登录成功 status = 1, user={user}
     * 浏览器传输参数有误 status = 1001
     * 登录过程中出现错误 status =1002
     * 用户不存在 status = 1004
     */

    @ResponseBody
    @RequestMapping(value = "/loginByPhoneNum", method = RequestMethod.POST)
    public String loginByPhoneNum(@RequestBody String loginBody, HttpServletRequest request, HttpServletResponse response) {

        String result = userService.checkLogin(loginBody);

        JSONObject resultObj = JSON.parseObject(result);

        if (!resultObj.containsKey("uuid")) {
            return resultObj.toJSONString();
        }
        request.getSession().setAttribute("uuid", resultObj.getString("uuid"));

        return JsonUtil.resultToJson(StatusCode.GLOBAL_SUCCESS);

    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {

        Enumeration<String> stringEnumeration = session.getAttributeNames();

        while (stringEnumeration.hasMoreElements()) {
            session.removeAttribute(stringEnumeration.nextElement());
        }

        session.invalidate();

        return "main";
    }

    @ResponseBody
    @RequestMapping("/checkLogin")
    public String checkLogin(HttpSession session) {
        if (session.getAttribute("uuid") == null)
            return JsonUtil.resultToJson(0);
        else
            return JsonUtil.resultToJson(1);
    }

}
