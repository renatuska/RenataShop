package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class FileStockStorage implements StockStorage {
    private String filePath;

    public FileStockStorage(String filePath) {
        this.filePath = filePath;
    }

    public Stock getStock(String id) throws Exception {
        ArrayList<Stock> stocks = this.getAllStocks();
        Iterator var3 = stocks.iterator();

        Stock stock;
        do {
            if (!var3.hasNext()) {
                return null;
            }

            stock = (Stock)var3.next();
        } while(!stock.getItemId().equalsIgnoreCase(id));

        return stock;
    }

    public ArrayList<Stock> getAllStocks() throws FileNotFoundException, Exception {
        File file = new File(this.filePath);
        Scanner sc = new Scanner(file);
        ArrayList stocks = new ArrayList();

        while(sc.hasNextLine()) {
            String itemId = sc.nextLine();
            String itemName = sc.nextLine();
            double itemCosts = sc.nextDouble();
            int itemQt = sc.nextInt();
            sc.nextLine();
            sc.nextLine();
            Stock stock = new Stock(itemId, itemName, itemCosts, itemQt);
            stocks.add(stock);
        }

        return stocks;
    }

    public void addOrUpdateStock(Stock stock) throws IOException, Exception {
        if (stock != null) {
            ArrayList<Stock> stocks = this.getAllStocks();
            boolean found = false;

            for(int i = 0; i < stocks.size(); ++i) {
                if (((Stock)stocks.get(i)).getItemId().equalsIgnoreCase(stock.getItemId())) {
                    stocks.set(i, stock);
                    found = true;
                    break;
                }
            }

            if (found) {
                FileWriter fw = new FileWriter(this.filePath);
                PrintWriter writer = new PrintWriter(fw);
                Iterator var6 = stocks.iterator();

                while(var6.hasNext()) {
                    Stock s = (Stock)var6.next();
                    this.writeStock(writer, s);
                }

                writer.close();
            } else {
                this.addStock(stock);
            }

        }
    }

    public void deleteStock(String id) throws Exception {
        ArrayList<Stock> stocks = this.getAllStocks();
        boolean found = false;
        Iterator var4 = stocks.iterator();

        while(var4.hasNext()) {
            Stock stock = (Stock)var4.next();
            if (stock.getItemId().equalsIgnoreCase(id)) {
                stocks.remove(stock);
                found = true;
                break;
            }
        }

        if (found) {
            FileWriter fw = new FileWriter(this.filePath);
            PrintWriter writer = new PrintWriter(fw);
            Iterator var6 = stocks.iterator();

            while(var6.hasNext()) {
                Stock s = (Stock)var6.next();
                this.writeStock(writer, s);
            }

            writer.close();
        }

    }

    private void addStock(Stock stock) throws IOException, Exception {
        FileWriter fw = new FileWriter(this.filePath, true);
        PrintWriter writer = new PrintWriter(fw);
        this.writeStock(writer, stock);
        writer.close();
    }

    private void writeStock(PrintWriter writer, Stock item) {
        writer.println(item.getItemId());
        writer.println(item.getItemName());
        writer.println(item.getItemCosts());
        writer.println(item.getItemQt());
        writer.println();
    }
}
