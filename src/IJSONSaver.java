import java.util.Date;

public interface IJSONSaver {
    void createJSONFile(ITextAnalyzer analyzer);
    void setFileName (String topCategoryName, Date dateFrom, Date dateTo);
    String getFileName();
}
