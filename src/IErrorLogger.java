import java.io.IOException;
import java.text.ParseException;

/**
 * implements the logic of processing errors
 */

public interface IErrorLogger {
    void logIOException(IOException e);
    void logException(Exception e);
    void logParseException(ParseException e);
}
