import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * implements the logic of reading text file
 */

public interface ITextReader {
    void readFileByAPath(String path) throws IOException;
    List getTextBuffer(String path) throws IOException;
}

