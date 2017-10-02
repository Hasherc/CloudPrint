package com.hasherc.service;

public interface OrderService {
    String placeOrder(String uuid, String orderJson);

    String getUnPaidOrder(String uuid);
}
