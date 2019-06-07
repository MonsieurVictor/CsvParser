import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class TextReader implements ITextReader {
    private List initialList = new ArrayList();
    private String [] initialString;

    public void readFileByAPath(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        if (reader.ready()) {
            reader.readLine();}
        while (reader.ready()) {
            initialString = reader.readLine().split(",");
            initialList.add(initialString);
        }
    }

    public List getTextBuffer(String path) throws IOException {
        readFileByAPath(path);
        return this.initialList;
    }
}
