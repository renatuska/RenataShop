package com.company;

import java.util.ArrayList;

public class StockService {
    private StockStorage db;

    public StockService(StockStorage db) {
        this.db = db;
    }

    public ArrayList<Stock> getAllStocks() throws Exception {
        return this.db.getAllStocks();
    }

    public void addStock(Stock stock) throws Exception {
        this.db.addOrUpdateStock(stock);
    }

    public Stock getStockbyId(String id) throws Exception {
        return this.db.getStock(id);
    }

    public void updateStock(Stock stock) throws Exception {
        this.db.addOrUpdateStock(stock);
    }
}
