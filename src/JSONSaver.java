import java.io.File;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class JSONSaver implements IJSONSaver  {
    private String jsonFileName;

    public void createJSONFile(List<TextAnalyzer.TopRatedPair> pairs){
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; (i < 10) && i < (pairs.size()) ; i++) {
            JSONObject obj = new JSONObject();
            obj.put(pairs.get(i).key, pairs.get(i).value);
            jsonArray.add(obj);
        }
        try (FileWriter file = new FileWriter( "files" + File.separatorChar + getFileName())) {
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
