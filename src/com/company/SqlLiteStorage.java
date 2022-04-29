package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class SqlLiteStorage implements UserStorage, StockStorage, ReportStorage {
    public SqlLiteStorage() {
    }

    public Stock getStock(String id) throws Exception {
        return null;
    }

    public ArrayList<Stock> getAllStocks() throws FileNotFoundException, Exception {
        return null;
    }

    public void addOrUpdateStock(Stock stock) throws IOException, Exception {
    }

    public void deleteStock(String id) throws Exception {
    }

    public User getUser(String username) throws Exception {
        return null;
    }

    public ArrayList<User> getAllUsers() throws FileNotFoundException, Exception {
        return null;
    }

    public void addUser(User user) throws IOException, Exception {
    }

    public void deleteUser(String username) throws Exception {
    }

    public void addDataToReport(Stock stock) {
    }

    public ArrayList<Stock> getAllData() {
        return null;
    }
}
