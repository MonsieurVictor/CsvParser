package main.java;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

public interface IGuiForm {
    void startDraw(ControllerGui controller, IGuiForm guiForm) throws ParseException;

    String getCategoryFlag();
    void setCategoryFlag(String categoryFlag);
//    void updateChartsTopTx();
//    void updateChartsTopRx();
//    void updateChartsTopProtocols();
//    void updateChartsTopApps();

    String getDateRangeString();

    int getDiffSec();

    void setDiffSec(int diffSec);

    void setRootPanelChart(JPanel rootPanelChart);

    JPanel getPanelSlider();

    void setPanelSlider(JPanel panelSlider);

    JPanel getPanelButton();

    void setPanelButton(JPanel panelButton);

    JPanel getPanelCenter();

    void setPanelCenter(JPanel panelCenter);

    void setPanelRootLauncher(JPanel panelRootLauncher);

    Date[] getDateArrayForSliderLabels();

    void setDateArrayForSliderLabels(Date[] dateArrayForSliderLabels);

    Date getDateFirst();

    void setDateFirst(Date dateFirst);

    Date getDateLast();

    void setDateLast(Date dateLast);

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

    JLabel getLabelFromAtButtons();

    void setLabelFromAtButtons(JLabel labelFromAtButtons);

    JLabel getLabelToAtButtons();

    void setLabelToAtButtons(JLabel labelToAtButtons);

    JLabel getLabelFromAtSlider();

    void setLabelFromAtSlider(JLabel labelFromAtSlider);

    JLabel getLabelToAtSlider();

    void setLabelToAtSlider(JLabel labelToAtSlider);

    IGuiForm getGuiForm();

    void setGuiForm(IGuiForm guiForm);

    ControllerGui getController();

    void setController(ControllerGui controller);

    JSONSaver getJsonSaver();

    void setJsonSaver(JSONSaver jsonSaver);



//    JFreeChart createJFreeChartBarRx();
//
//    JFreeChart createJFreeChartBarTx();
//
//    JFreeChart createJFreeChartBarProtocols();
//
//    JFreeChart createJFreeChartBarApps();
//
//    JFreeChart createJFreeChartPieRx();
//
//    JFreeChart createJFreeChartPieTx();
//
//    JFreeChart createJFreeChartPieProtocols();
//
//    JFreeChart createJFreeChartPieApps();

    JFreeChart createJFreeChartBar(String categoryName, Map<String, Long> map);

    JFreeChart createJFreeChartPie(String categoryName, Map<String, Long> map);


//    ChangeListener createChangeListener();

    ChartFrame createChartFrame(JFreeChart chartBar, String categoryName, int locationX, int locationY, int sizeX, int sizeY);

    void setSliderFrom();

    void setSliderTo();

    JSlider createSlider(int initialValue);

    void setJFrameMainMenu(String titleStr);

    JPanel getPanelRootLauncher();
    ChartFrame getFrameBarTopRx();
    void setFrameBarTopRx(ChartFrame frameBarTopRx);
    ChartFrame getFrameBarTopTx();
    void setFrameBarTopTx(ChartFrame frameBarTopTx);

    ChartFrame getFrameBarTopProtocols();

    void setFrameBarTopProtocols(ChartFrame frameBarTopProtocols);

    ChartFrame getFrameBarTopApps();

    abstract void setFrameBarTopApps(ChartFrame frameBarTopApps);
    ChartFrame getFramePieTopTx();
    void setFramePieTopTx(ChartFrame framePieTopTx);
    ChartFrame getFramePieTopRx();
    void setFramePieTopRx(ChartFrame framePieTopRx);

    Date getDateFrom();

    void setDateFrom(Date dateFrom);

    Date getDateTo();

    void setDateTo(Date dateTo);

    ChartFrame getFramePieTopProtocols();
    void setFramePieTopProtocols(ChartFrame framePieTopProtocols);
    ChartFrame getFramePieTopApps();
    void setFramePieTopApps(ChartFrame framePieTopApps);

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

    void setLabelsDate();

    void checkSlidersPropriety();


//    void drawTopTx();
//    void drawTopRx();
//    void drawTopProtocols();
//    void drawTopApps();

    JButton getButtonBack();
}

