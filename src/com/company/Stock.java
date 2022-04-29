package com.company;

public class Stock  {

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
        return this.itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public double getItemCosts() {
        return this.itemCosts;
    }

    public double getItemPrice() {
        return this.itemCosts < 20.0D ? this.itemCosts * 1.3D : this.itemCosts * 2.0D;
    }

    public void setQt(int qt) {
        this.itemQt = qt;
    }

    public int getItemQt() {
        return this.itemQt;
    }

    public String print() {
        return null;
    }
}
