package com.shoukry.workgraduate.Models;

public class DetailsModel {

    private int orderNumber;
    private String serviceType,time;

    public DetailsModel(int orderNumber, String serviceType) {
        this.orderNumber = orderNumber;
        this.serviceType = serviceType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
