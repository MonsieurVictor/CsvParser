import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IJSONSaver {
    void createJSONFile(Map<String, Long> map);
    void setFileName (String topCategoryName, Date dateFrom, Date dateTo);
    String getFileName();
}
