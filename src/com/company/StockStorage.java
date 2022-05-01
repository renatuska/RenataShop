package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface StockStorage {
    Stock getStock(String id) throws Exception;
    ArrayList<Stock> getAllStocks() throws Exception;
    void addOrUpdateStock(Stock stock) throws Exception;
}
