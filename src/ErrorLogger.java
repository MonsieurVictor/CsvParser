import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * is responsible for catching errors and writing them to Errorlog
 **/

public class ErrorLogger implements IErrorLogger {
    public void errorOpen(IOException e) {
        String filePath = "files/ErrorLog.txt";
        String errorText = "\nOpenError:" + e;
        System.out.println("OpenError:" + e);
        try {
            Files.write(Paths.get(filePath), errorText.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException fileE) {
            System.out.println("can't write a text opening error to the log" + fileE);
        }
    }

    public void errorReadFlags (Exception e){
        System.out.println("Error message:" + e);
        String filePath = "files" +  "ErrorLog.txt";
        String errorText = System.lineSeparator() + "Error message:" + e;
        try {
            Files.write(Paths.get(filePath), errorText.getBytes(), StandardOpenOption.APPEND);
        } catch (Exception flagsE) {
            System.out.println("can't write flag error to the log" + flagsE);
        }
    }
}
