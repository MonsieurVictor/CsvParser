import java.util.Date;
import java.util.List;

public interface IJSONSaver {
    void createJSONFile(List<TextAnalyzer.TopRatedPair> pairs);
    void setFileName (String topCategoryName, Date dateFrom, Date dateTo);
    String getFileName();
}
