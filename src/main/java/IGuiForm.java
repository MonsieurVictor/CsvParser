package main.java;

import java.text.ParseException;

public interface IGuiForm {
    void startDraw(ControllerGui controller, IGuiForm guiForm) throws ParseException;

    String getCategoryFlag();
    void setCategoryFlag(String categoryFlag);
    void updateChartsTopTx();
    void updateChartsTopRx();
    void updateChartsTopProtocols();
    void updateChartsTopApps();
}

