package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StockService {

    private final StockStorage db;
    public StockService(StockStorage db)  {
        this.db = db;
    }

    public ArrayList<Stock> getAllStocks() throws Exception {
        return db.getAllStocks();
    }

    public void addStock(Stock stock) throws Exception {
        db.addOrUpdateStock(stock);
    }

    public Stock getStockbyId(String id) throws Exception {
        return db.getStock(id);
    }

    public void updateStock(Stock stock) throws Exception {
        db.addOrUpdateStock(stock);
    }
}


