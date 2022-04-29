package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileReportStorage implements ReportStorage {
    private String filePath;

    public FileReportStorage(String filePath) {
        this.filePath = filePath;
    }

    public void addDataToReport(Stock stock) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);
        PrintWriter writer = new PrintWriter(fw);
        this.writeData(writer, stock);
        writer.close();
    }

    private void writeData(PrintWriter writer, Stock item) {
        writer.println(item.getItemId());
        writer.println(item.getItemName());
        writer.println(item.getItemPrice());
        writer.println(item.getItemCosts());
        writer.println(item.getItemQt());
        writer.println();
    }

    public ArrayList<Stock> getAllData() {
        return null;
    }
}
