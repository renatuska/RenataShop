package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class SqlLiteStorage implements UserStorage, StockStorage, ReportStorage {

    @Override
    public Stock getStock(String id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Stock> getAllStocks() throws FileNotFoundException, Exception {
        return null;
    }

    @Override
    public void addOrUpdateStock(Stock stock) throws IOException, Exception {

    }

    @Override
    public void deleteStock(String id) throws Exception {

    }

    @Override
    public User getUser(String username) throws Exception {
        return null;
    }

    @Override
    public ArrayList<User> getAllUsers() throws FileNotFoundException, Exception {
        return null;
    }

    @Override
    public void addUser(User user) throws IOException, Exception {

    }

    @Override
    public void deleteUser(String username) throws Exception {

    }

    @Override
    public void addDataToReport(Stock stock) {

    }

    @Override
    public ArrayList<Stock> getAllData() {
        return null;
    }
}
