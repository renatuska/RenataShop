package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface ReportStorage {
    void addDataToReport(Stock stock) throws IOException;
    ArrayList<Stock> getAllData();

}

