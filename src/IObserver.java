import java.text.ParseException;

public interface IObserver {
    void handleCalculationEvent(ControllerGui controller) throws ParseException;
}
