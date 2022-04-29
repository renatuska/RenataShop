package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface StockStorage {
    Stock getStock(String var1) throws Exception;

    ArrayList<Stock> getAllStocks() throws FileNotFoundException, Exception;

    void addOrUpdateStock(Stock var1) throws IOException, Exception;

    void deleteStock(String var1) throws Exception;
}
