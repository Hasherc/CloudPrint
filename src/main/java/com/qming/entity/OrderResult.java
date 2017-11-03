package com.qming.entity;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderResult {
    private String orderUUID;
    private List<String> fileName;
    private List<Boolean> color;
    private List<Boolean> duplex;
    private Date createTime;
    private String phoneNum;
    private double cost;
    private int status;

    public OrderResult() {

        this.fileName = new ArrayList<>();
        this.color = new ArrayList<>();
        this.duplex = new ArrayList<>();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getOrderUUID() {
        return orderUUID;
    }

    public void setOrderUUID(String orderUUID) {
        this.orderUUID = orderUUID;
    }

    public List<String> getFileName() {
        return fileName;
    }

    public void setFileName(List<String> fileName) {
        this.fileName = fileName;
    }

    public List<Boolean> getColor() {
        return color;
    }

    public void setColor(List<Boolean> color) {
        this.color = color;
    }

    public List<Boolean> getDuplex() {
        return duplex;
    }

    public void setDuplex(List<Boolean> duplex) {
        this.duplex = duplex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
