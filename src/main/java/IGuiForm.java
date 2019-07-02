package main.java;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

public interface IGuiForm {

    void setGuiForm(IGuiForm guiForm);
    void startDraw() throws ParseException;

    String getDateRangeString();
    void setDateArrayForSliderLabels(Date[] dateArrayForSliderLabels);
    Date getDateFirst();
    void setDateFirst(Date dateFirst);
    void setDateLast(Date dateLast);
    Date getDateFrom();
    void setDateFrom(Date dateFrom);
    Date getDateTo();
    void setDateTo(Date dateTo);
    void setLabelsDate();

    JSlider createSlider(int initialValue);
    JSlider getSliderFrom();
    JSlider getSliderTo();
    void setSliderFrom();
    void setSliderTo();
    void checkSlidersPropriety();

    void setJFrameMain(String titleStr);
    JFrame getjFrameMain();

    JButton getButtonTopRx();
    JButton getButtonTopTx();
    JButton getButtonTopProtocols();
    JButton getButtonTopApps();
    JButton getButtonShowBarChart();
    JButton getButtonShowPieChart();
    JButton getButtonToJSON();
    JButton getButtonBack();

    ChartPanel getPanelChartBarTopRx();
    void setPanelChartBarTopRx(ChartPanel panelChartBarTopRx);
    ChartPanel getPanelChartBarTopTx();
    void setPanelChartBarTopTx(ChartPanel panelChartBarTopTx);
    ChartPanel getPanelChartBarTopProtocols();
    void setPanelChartBarTopProtocols(ChartPanel panelChartBarTopProtocols);
    ChartPanel getPanelChartBarTopApps();
    void setPanelChartBarTopApps(ChartPanel panelChartBarTopApps);
    ChartPanel getPanelChartPieTopRx();
    void setPanelChartPieTopRx(ChartPanel panelChartPieTopRx);
    ChartPanel getPanelChartPieTopTx();
    void setPanelChartPieTopTx(ChartPanel panelChartPieTopTx);
    ChartPanel getPanelChartPieTopProtocols();
    void setPanelChartPieTopProtocols(ChartPanel panelChartPieTopProtocols);
    ChartPanel getPanelChartPieTopApps();
    void setPanelChartPieTopApps(ChartPanel panelChartPieTopApps);

    JFreeChart createJFreeChartBar(String categoryName, Map<String, Long> map);
    JFreeChart createJFreeChartPie(String categoryName, Map<String, Long> map);
    ChartFrame createChartFrame(JFreeChart chartBar, String categoryName, int locationX, int locationY, int sizeX, int sizeY);

    JPanel getPanelRootLauncher();

    ChartFrame getFrameBarTopRx();
    void setFrameBarTopRx(ChartFrame frameBarTopRx);
    ChartFrame getFrameBarTopTx();
    void setFrameBarTopTx(ChartFrame frameBarTopTx);
    ChartFrame getFrameBarTopProtocols();
    void setFrameBarTopProtocols(ChartFrame frameBarTopProtocols);
    ChartFrame getFrameBarTopApps();
    void setFrameBarTopApps(ChartFrame frameBarTopApps);
    ChartFrame getFramePieTopTx();
    void setFramePieTopTx(ChartFrame framePieTopTx);
    ChartFrame getFramePieTopRx();
    void setFramePieTopRx(ChartFrame framePieTopRx);
    ChartFrame getFramePieTopProtocols();
    void setFramePieTopProtocols(ChartFrame framePieTopProtocols);
    ChartFrame getFramePieTopApps();
    void setFramePieTopApps(ChartFrame framePieTopApps);

    String getCategoryFlag();
    void setCategoryFlag(String categoryFlag);
}

