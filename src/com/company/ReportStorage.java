package com.company;
import java.util.ArrayList;

public interface ReportStorage {
    void addDataToReport(Stock stock) throws Exception;
    ArrayList<Stock> getAllData() throws Exception;
}

