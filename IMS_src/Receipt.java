package InventoryManagementSystem.IMS_src;

import java.util.ArrayList;

public class Receipt {
    private String date;
    private String time;
    private String user;
    private ArrayList<String> products;
    private double totalPrice;

    public Receipt(String date, String time, String user) {
        this.date = date;
        this.time = time;
        this.user = user;
        products = new ArrayList<>();
        totalPrice = 0.0;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<String> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
