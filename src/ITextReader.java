import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * implements the logic of reading text file
 */

public interface ITextReader {
    List getTextBuffer(String path) throws IOException;
}

