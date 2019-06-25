import java.text.ParseException;

public interface IGuiForm {
    void startDraw(ControllerGui controller, IGuiForm guiForm) throws ParseException;

    String getCategoryFlag();
    void setCategoryFlag(String categoryFlag);
    public void updateChartsTopTx();
    public void updateChartsTopRx();
}

