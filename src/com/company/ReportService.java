package com.company;

import java.io.IOException;
import java.util.ArrayList;

public class ReportService {
    private ReportStorage db;

    public ReportService(ReportStorage db) {
        this.db = db;
    }

    public void addDataToReport(Stock stock) throws IOException {
        this.db.addDataToReport(stock);
    }

    public ArrayList<Stock> getAllData() {
        return this.db.getAllData();
    }
}
