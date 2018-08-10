package com.baizhi.lucene.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by SK on 2018/8/9.
 */
public class Product {
    private String id;
    private String name;
    private double price;
    private String content;
    private String picture;
    private String status;
    @JSONField(format = "yyyy/MM/dd")
    private Date createDate;
    private String location;

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", content='" + content + '\'' +
                ", picture='" + picture + '\'' +
                ", status='" + status + '\'' +
                ", createDate=" + createDate +
                ", location='" + location + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Product() {

    }

    public Product(String id, String name, double price, String content, String picture, String status, Date createDate, String location) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.content = content;
        this.picture = picture;
        this.status = status;
        this.createDate = createDate;
        this.location = location;
    }
}
