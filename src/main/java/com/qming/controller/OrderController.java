package com.qming.controller;

import com.qming.consts.StatusCode;
import com.qming.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonUtil;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @ResponseBody
    @RequestMapping("/placeOrder")
    public String placeOrder(String orderJson, HttpSession session) {
        String userUuid = session.getAttribute("userUuid").toString();
        if (userUuid == null) {
            return JsonUtil.resultToJson(StatusCode.SESSION_TIMEOUT);
        }
        return orderService.placeOrder(userUuid, orderJson);
    }

    @ResponseBody
    @RequestMapping("/getUnPaidOrder")
    public String getOrder(HttpSession session) {
        String userUuid = (String) session.getAttribute("userUuid");
        if (userUuid == null) {
            return JsonUtil.resultToJson(StatusCode.SESSION_TIMEOUT);
        }
        return orderService.getUnPaidOrder(userUuid);
    }

    @RequestMapping("/toOrderPage")
    public String toOrderPage(HttpSession session) {
        String userUuid = (String) session.getAttribute("userUuid");
        if (userUuid == null) {
            return "login";
        }
        return "order";
    }

    @ResponseBody
    @RequestMapping("/getUserInfo")
    public String getUserInfo(HttpSession session) {
        String userUuid = (String) session.getAttribute("userUuid");
        if (userUuid == null) {
            return JsonUtil.resultToJson(StatusCode.SESSION_TIMEOUT);
        }
        return orderService.getUserNameAndPhone(userUuid);

    }
}
