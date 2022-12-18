package com.shoukry.workgraduate.Models;

public class ProviderModel {

    private String name,service;
    private int id,img,userId;

    public ProviderModel(String name, String service, int id, int img, int userId) {
        this.name = name;
        this.service = service;
        this.id = id;
        this.img = img;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}