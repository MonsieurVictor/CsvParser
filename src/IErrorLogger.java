import java.io.IOException;

/**
 * implements the logic of processing errors
 */

public interface IErrorLogger {
    void errorOpen(IOException e);
    void errorReadFlags(Exception e);
}
