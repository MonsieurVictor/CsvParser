import java.text.ParseException;

public interface IObserver {
    void handleCalculationEvent(GuiForm guiForm) throws ParseException;
}
