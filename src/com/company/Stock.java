package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Stock {

    private String itemId;
    private String itemName;
    private double itemCosts;
    private int itemQt;

    public Stock(String itemId, String itemName, double itemCosts, int itemQt) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCosts = itemCosts;
        this.itemQt = itemQt;
    }

    public String getItemId() {
        return itemId;
    }
    public String getItemName() {
        return itemName;
    }

    public double getItemCosts() {
        return itemCosts;
    }

    public  double getItemPrice() {
        if(itemCosts < 20) {
            return itemCosts * 1.3;
        } else {
            return itemCosts * 2;
        }
    }

    public void setQt(int qt) {
        itemQt = qt;
    }

    public int getItemQt() {
        return itemQt;
    }
}
