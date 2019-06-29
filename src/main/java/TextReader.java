package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextReader implements ITextReader {

    private String [] initialString;

    public List getTextBuffer(String path) throws IOException {
        List initialList = new ArrayList();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        if (reader.ready()) {
            reader.readLine();}
        while (reader.ready()) {
            initialString = reader.readLine().split(",");
            initialList.add(initialString);
        }
        return initialList;
    }
}
