package com.hasherc.controller;

import Util.JsonUtil;
import com.hasherc.consts.StatusCode;
import com.hasherc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @ResponseBody
    @RequestMapping("/placeOrder")
    public String placeOrder(String orderJson, HttpSession session) {
        String uuid = session.getAttribute("uuid").toString();
        if (uuid == null) return JsonUtil.resultToJson(StatusCode.SESSION_TIMEOUT);
        return orderService.placeOrder(uuid, orderJson);
    }

    @ResponseBody
    @RequestMapping("/getUnPaidOrder")
    public String getOrder(HttpSession session) {
        String uuid = session.getAttribute("uuid").toString();
        if (uuid == null) return JsonUtil.resultToJson(StatusCode.SESSION_TIMEOUT);
        return orderService.getUnPaidOrder(uuid);
    }

}
