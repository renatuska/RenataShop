import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileStockStorage implements StockStorage {

    private final String filePath;

    public FileStockStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Stock getStock(String id) throws Exception {
        ArrayList<Stock> stocks = getAllStocks();
        for (Stock stock : stocks) {
            if (stock.getItemId().equalsIgnoreCase(id)) {
                return stock;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Stock> getAllStocks() throws Exception {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        ArrayList<Stock> stocks = new ArrayList<>();

        while (sc.hasNextLine()) {
            String itemId = sc.nextLine();
            String itemName = sc.nextLine();
            float itemCosts = sc.nextFloat();
            int itemQt = sc.nextInt();
            sc.nextLine();
            sc.nextLine();

            Stock stock = new Stock(itemId, itemName, itemCosts, itemQt);
            stocks.add(stock);
        }
        return stocks;
    }

    @Override
    public void addOrUpdateStock(Stock stock) throws Exception {
        if(stock == null) return;

        ArrayList<Stock> stocks = getAllStocks();

        boolean found = false;
        for(int i=0; i< stocks.size(); i++) {
            if (stocks.get(i).getItemId().equalsIgnoreCase(stock.getItemId())) {
                stocks.set(i, stock);
                found = true;
                break;
            }
        }

        //lets rewrite
        if(found) {
            FileWriter fw = new FileWriter(filePath);
            PrintWriter writer = new PrintWriter(fw);
            for (Stock s : stocks) {
                writeStock(writer, s);
            }
            writer.close();
        } else {
            addStock(stock);
        }
    }

    private void addStock(Stock stock) throws Exception {
        FileWriter fw = new FileWriter(filePath, true);
        PrintWriter writer = new PrintWriter(fw);
        writeStock(writer, stock);
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
