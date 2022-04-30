package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface StockStorage {
    Stock getStock(String id) throws Exception;
    ArrayList<Stock> getAllStocks() throws FileNotFoundException, Exception;
    void addOrUpdateStock(Stock stock) throws IOException, Exception;
    void deleteStock(String id) throws Exception;
}
