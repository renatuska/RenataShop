package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileReportStorage  implements ReportStorage{
    private String filePath;

    public FileReportStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void addDataToReport(Stock stock) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        PrintWriter writer = new PrintWriter(fw);
        writeData(writer, stock);
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

    @Override
    public ArrayList<Stock> getAllData() {

        return null;
    }

}

