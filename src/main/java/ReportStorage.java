import java.util.ArrayList;

public interface ReportStorage {
    void addDataToReport(Log log) throws Exception;
    ArrayList<Log> getAllData() throws Exception;
}

