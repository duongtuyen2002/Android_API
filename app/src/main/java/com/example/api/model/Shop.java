package com.example.api.model;

public class Shop {
    public int id;
    public String name,price, url;

    public Shop(int id, String name, String price, String url) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.url = url;
    }

}
