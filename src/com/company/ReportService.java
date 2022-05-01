package com.company;

import java.io.IOException;
import java.util.ArrayList;

public class ReportService {
    private final ReportStorage db;
    float revenue = 0;
    float costs = 0;
    float profit;
    float margin;
    public ReportService(ReportStorage db)  {
        this.db = db;
    }

    public void addDataToReport(Stock stock) throws Exception {
        db.addDataToReport(stock);
    }
    public ArrayList<Stock> getAllData() throws Exception {
        return db.getAllData();
    }

    float getRevenue() throws Exception {
        revenue = 0;
        ArrayList<Stock> items = db.getAllData();
        for(Stock stock : items ) {
           revenue =  stock.getItemPrice()+revenue;
        }
        return revenue;
    }


    float getCosts() throws Exception {
        costs = 0;
        ArrayList<Stock> items = db.getAllData();
        for(Stock stock : items) {
            costs =  stock.getItemCosts()+costs;
        }
        return costs;
    }


    double getProfit() {

        profit = revenue - costs;
        return profit;
    }


    float getMargin() {

        margin = profit / revenue;
        return margin;
    }
}
