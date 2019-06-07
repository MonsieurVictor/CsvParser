import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JSONSaver implements IJSONSaver  {
    private String jsonFileName;
    private ITextAnalyzer analyzer = new TextAnalyzer();

    public void createJSONFile(ITextAnalyzer analyzer){
        this.analyzer = analyzer;
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; (i < 10) && i < (analyzer.getTopReceivers().size()) ; i++) {
            JSONObject obj = new JSONObject();
            obj.put(analyzer.getTopReceivers().get(i).key, analyzer.getTopReceivers().get(i).value);
            jsonArray.add(obj);
        }
        try (FileWriter file = new FileWriter( "files/"+ getFileName())) {
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFileName (String topCategoryName, Date dateFrom, Date dateTo) {
        jsonFileName = topCategoryName + new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(dateFrom)
                + "_" + (new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(dateTo)) + ".json";
    }

    public String getFileName() {
        return jsonFileName;
    }
}
