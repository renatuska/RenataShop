package com.company;

import java.io.IOException;
import java.util.ArrayList;

public class ReportService {
    private final ReportStorage db;
    public ReportService(ReportStorage db)  {
        this.db = db;
    }

    public void addDataToReport(Stock stock) throws IOException {
        db.addDataToReport(stock);
    }
    public ArrayList<Stock> getAllData() {
        return db.getAllData();
    }
//     double getRevenue(){
//        Report
//     }

}
