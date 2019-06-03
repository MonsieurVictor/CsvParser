import java.io.File;
import java.io.IOException;
import java.io.Writer;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class JSONSaver implements IJSONSaver  {

    String jsonFileName;



    public ITextAnalyzer analyzer = new TextAnalyzer();


    public void createJSONFile(ITextAnalyzer analyzer){


        this.analyzer = analyzer;
        JSONArray jsonArray = new JSONArray();


        for (int i = 0; (i < 10) && i < (analyzer.topReceiversPairs.size()) ; i++) {
            JSONObject obj = new JSONObject();
            obj.put(analyzer.topReceiversPairs.get(i).key, analyzer.topReceiversPairs.get(i).value);
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
    };
}
