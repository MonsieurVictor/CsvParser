import java.util.Map;

public class ConsoleLogger implements IConsoleLogger{
    public void writeMapToConsole(String string, Map<String, Long> map, int limitCount) {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(string);
        map.entrySet().stream().limit(limitCount).forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
    }

    public void writeToConsole(String string){
        System.out.println(string);
    }
}
