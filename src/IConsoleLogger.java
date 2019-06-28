import java.util.Map;

public interface IConsoleLogger {
    void writeMapToConsole(String string, Map <String, Long> map, int limitCount);
    void writeToConsole(String string);
}
