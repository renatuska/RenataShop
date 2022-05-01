package com.company;

public class Stock {

    private final String itemId;
    private final String itemName;
    private final float itemCosts;
    private int itemQt;

    public Stock(String itemId, String itemName, float itemCosts, int itemQt) {
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

    public float getItemCosts() {
        return itemCosts;
    }

    public  float getItemPrice() {
        if(itemCosts < 20) {
            return itemCosts * (float)1.3;
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
