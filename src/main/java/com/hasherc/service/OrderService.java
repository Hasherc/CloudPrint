package com.hasherc.service;

public interface OrderService {
    String placeOrder(String userUuid, String orderJson);

    String getUnPaidOrder(String userUuid);
    String getUserNameAndPhone(String userUuid);
}
