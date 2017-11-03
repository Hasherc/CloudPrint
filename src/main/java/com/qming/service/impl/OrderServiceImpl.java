package com.qming.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qming.consts.OrderConsts;
import com.qming.consts.StatusCode;
import com.qming.dao.FileDao;
import com.qming.dao.OrderDao;
import com.qming.dao.UserDao;
import com.qming.entity.Order;
import com.qming.entity.OrderResult;
import com.qming.entity.UserInfo;
import com.qming.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.JsonUtil;
import util.UUIDUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    FileDao fileDao;

    @Autowired
    OrderDao orderDao;
    @Autowired
    UserDao userDao;

    @Override
    public String placeOrder(String userUuid, String orderJson) {
        //如果有未付款订单，则不允许下单
        if (orderDao.finUndoOrder(userUuid).get(0) <= 1) {
            return JsonUtil.resultToJson(StatusCode.STATUS__UNPAID_ORDER_EXIST);
        }
        //检测json格式
        if (!JsonUtil.valid(orderJson)) {
            return JsonUtil.resultToJson(StatusCode.STATUS_INVALID_JSON);
        }
        //json字符串转为List
        JSONArray orderListJson = JSONArray.parseArray(orderJson);
        List<Order> orderList = new ArrayList<>();
        for (Object anOrderListJson : orderListJson) {
            JSONObject jsonObject = JSON.parseObject(anOrderListJson.toString());

            orderList.add(JSONObject.toJavaObject(jsonObject, Order.class));
        }
        //组装订单，一个订单里每一个文件对应一条数据库记录，同订单UUID相同
        String orderUUID = UUIDUtil.getUUID();
        for (Order order : orderList) {
            order.setUserUUID(userUuid);
            order.setOrderUUID(orderUUID);
            int page = fileDao.getFilePage(userUuid, order.getFileName());
            double cost = OrderConsts.calculatePrice(page, order.isColor(), order.isDuplex());
            order.setCost(cost);
            order.setStatus(OrderConsts.UNPAID);
        }

        //插入数据库
        for (Order order : orderList) {
            int inserResult = orderDao.insertOrder(order);

            if (inserResult == -1) {
                orderDao.deleteOrder(orderUUID);
                return JsonUtil.resultToJson(StatusCode.GLOBAL_FAIL);
            }

        }

        return JsonUtil.resultToJson(StatusCode.GLOBAL_SUCCESS);
    }

    /**
     * 获取未付款订单
     *
     * @param uuid 用户uuid
     * @return 订单信息json
     */
    @Override
    public String getUnPaidOrder(String uuid) {
        //根据uuid获取用户订单 条件 status<=1 即未付款
        List<Order> orderList = orderDao.getUnPaidOrder(uuid);
        if (orderList == null) {
            return JsonUtil.resultToJson(StatusCode.STATUS_NOT_EXIST);
        }
        OrderResult orderResult = new OrderResult();

        //组装返回订单返回信息
        orderResult.setOrderUUID(orderList.get(0).getOrderUUID());
        orderResult.setPhoneNum(orderList.get(0).getPhoneNum());
        orderResult.setCreateTime(orderList.get(0).getCreateTime());
        orderResult.setStatus(orderList.get(0).getStatus());
        double cost = 0;
        for (Order order : orderList) {
            orderResult.getFileName().add(order.getFileName());
            orderResult.getColor().add(order.isColor());
            orderResult.getDuplex().add(order.isDuplex());
            cost += order.getCost();
        }
        orderResult.setCost(cost);
        //转为json
        return JSON.toJSONString(orderResult);
    }

    @Override
    public String getUserNameAndPhone(String userUuid) {
        UserInfo userInfo = userDao.findUserInfo(userUuid);
        System.out.println(userInfo);
        String userName = userInfo.getNickName();
        String phoneNum = userInfo.getPhoneNum();
        JSONObject userInfoJson = new JSONObject();
        userInfoJson.put("userName", userName);
        userInfoJson.put("phoneNum", phoneNum);
        userInfoJson.put("status", 1);
        System.out.println("userInfoJson" + userInfoJson);
        return userInfoJson.toJSONString();
    }

}
