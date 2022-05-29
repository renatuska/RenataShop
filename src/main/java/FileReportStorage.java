import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReportStorage  implements ReportStorage{
    private final String filePath;

    public FileReportStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void addDataToReport(Log log) throws Exception {
        FileWriter fw = new FileWriter(filePath, true);
        PrintWriter writer = new PrintWriter(fw);
        writeData(writer, log);
        writer.close();
    }

    private void writeData(PrintWriter writer, Log item) {
        writer.println(item.getItemId());
        writer.println(item.getItemName());
        writer.println(item.getItemCosts());
        writer.println(item.getItemQt());
        writer.println();
    }

    @Override
    public ArrayList<Log> getAllData() throws Exception {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        ArrayList<Log> logs = new ArrayList<>();

        while (sc.hasNextLine()) {
            String itemId = sc.nextLine();
            String itemName = sc.nextLine();
            float itemCosts = sc.nextFloat();
            int itemQt = sc.nextInt();
            sc.nextLine();
            sc.nextLine();

            Log log = new Log(new Stock(itemId, itemName, itemCosts, itemQt));
            logs.add(log);
        }
        return logs;
    }


}

