import java.io.IOException;
import java.util.ArrayList;

public class ReportService {
    private final ReportStorage db;
    float revenue = 0;
    float costs = 0;
    float profit;
    float margin;
    public ReportService(ReportStorage db)  {
        this.db = db;
    }

    public void addDataToReport(Log log) throws Exception {
        db.addDataToReport(log);
    }
    public ArrayList<Log> getAllData() throws Exception {
        return db.getAllData();
    }

    float getRevenue() throws Exception {
        revenue = 0;
        ArrayList<Log> items = db.getAllData();
        for(Log log : items ) {
            revenue =  log.getItemPrice() * log.getItemQt() +revenue;
        }
        return revenue;
    }

    float getCosts() throws Exception {
        costs = 0;
        ArrayList<Log> items = db.getAllData();
        for(Log log : items) {
            costs =  log.getItemCosts()* log.getItemQt()+costs;
        }
        return costs;
    }

    double getProfit() {

        profit = revenue - costs;
        return profit;
    }

    float getMargin() {

        margin = profit / revenue;
        return margin;
    }
}
