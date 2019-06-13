import java.text.ParseException;

public interface IObservable {
    void addObserver(IObserver o);
    void removeObserver(IObserver o);
    void notifyObserver() throws ParseException;
}
