package main.java;

import javax.swing.*;
import java.text.ParseException;
import java.util.Date;

public interface IGuiForm {
    void startDraw(ControllerGui controller, IGuiForm guiForm) throws ParseException;

    String getCategoryFlag();
    void setCategoryFlag(String categoryFlag);
    void updateChartsTopTx();
    void updateChartsTopRx();
    void updateChartsTopProtocols();
    void updateChartsTopApps();

    void setJFrameMainMenu(String titleStr);

    JButton getButtonTopRx();
    void setButtonTopRx(JButton buttonTopRx);
    JButton getButtonTopTx();
    void setButtonTopTx(JButton buttonTopTx);
    JButton getButtonTopProtocols();
    void setButtonTopProtocols(JButton buttonTopProtocols);
    JButton getButtonTopApps();
    void setButtonTopApps(JButton buttonTopApps);

    void setButtonBack(JButton buttonBack);

    JFrame getjFrameMain();

    void setjFrameMain(JFrame jFrameMain);

    JButton getButtonShowBarChart();

    void setButtonShowBarChart(JButton buttonShowBarChart);

    JButton getButtonShowPieChart();

    void setButtonShowPieChart(JButton buttonShowPieChart);

    JButton getButtonToJSON();

    void setButtonToJSON(JButton buttonToJSON);

    JSlider getSliderFrom();

    void setSliderFrom(JSlider sliderFrom);

    JSlider getSliderTo();

    void setSliderTo(JSlider sliderTo);

    Date getDateOfSlider(int sliderValue);


    void drawTopTx();
    void drawTopRx();
    void drawTopProtocols();
    void drawTopApps();

    JButton getButtonBack();
}

