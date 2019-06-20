import java.io.IOException;

/**
 * implements the logic of processing errors
 */

public interface IErrorLogger {
    void logIOException(IOException e);
    void logException(Exception e);
}
