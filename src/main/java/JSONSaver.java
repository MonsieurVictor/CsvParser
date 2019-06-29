package main.java;

import java.io.File;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class JSONSaver implements IJSONSaver  {
    private String jsonFileName;

    public void createJSONFile(Map<String, Long> map){
        JSONArray jsonArray = new JSONArray();
//        map
//                .entrySet()
//                .stream()
//                .limit(10)
//                .forEach(e -> jsonArray.add(new JSONObject().put(e.getKey(), e.getValue())));

        int i = 0;
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            JSONObject obj = new JSONObject();
            obj.put(entry.getKey(), entry.getValue());
            jsonArray.add(obj);
            i++;
            if (i >= 10) {
                break;
            }
        }

        try (FileWriter file = new FileWriter( "files"
                + File.separatorChar
                + getFileName())) {
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
