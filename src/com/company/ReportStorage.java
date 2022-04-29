package com.company;

import java.io.IOException;
import java.util.ArrayList;

public interface ReportStorage {
    void addDataToReport(Stock var1) throws IOException;

    ArrayList<Stock> getAllData();
}
