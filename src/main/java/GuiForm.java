package main.java;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class GuiForm implements IGuiForm {

    private JFrame jFrameMain = new JFrame("Main menu");

    private JPanel rootPanelChart;
    private JPanel panelSlider;
    private JPanel panelButton;
    private JPanel panelCenter;

    private JPanel panelRootLauncher;

    public String categoryFlag = "None";

    private JButton buttonTopTx;
    private JButton buttonTopProtocols;
    private JButton buttonTopRx;
    private JButton buttonTopApps;

    private JButton buttonBack;
    private JButton buttonShowBarChart;
    private JButton buttonShowPieChart;
    private JButton buttonToJSON;

    private Date[] dateArrayForSliderLabels;
    private Date dateFirst;
    private Date dateLast;
    private Date dateFrom;
    private Date dateTo;

    private JSlider sliderFrom;
    private JSlider sliderTo;

    public ChartFrame getFrameBarTopRx() {
        return frameBarTopRx;
    }

    public void setFrameBarTopRx(ChartFrame frameBarTopRx) {
        this.frameBarTopRx = frameBarTopRx;
    }

    public ChartFrame getFrameBarTopTx() {
        return frameBarTopTx;
    }

    public void setFrameBarTopTx(ChartFrame frameBarTopTx) {
        this.frameBarTopTx = frameBarTopTx;
    }

    public ChartFrame getFrameBarTopProtocols() {
        return frameBarTopProtocols;
    }

    public void setFrameBarTopProtocols(ChartFrame frameBarTopProtocols) { this.frameBarTopProtocols = frameBarTopProtocols; }

    public ChartFrame getFrameBarTopApps() {
        return frameBarTopApps;
    }

    public void setFrameBarTopApps(ChartFrame frameBarTopApps) {
        this.frameBarTopApps = frameBarTopApps;
    }

    public ChartFrame getFramePieTopTx() {
        return framePieTopTx;
    }

    public void setFramePieTopTx(ChartFrame framePieTopTx) {
        this.framePieTopTx = framePieTopTx;
    }

    public ChartFrame getFramePieTopRx() {
        return framePieTopRx;
    }

    public void setFramePieTopRx(ChartFrame framePieTopRx) {
        this.framePieTopRx = framePieTopRx;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public ChartFrame getFramePieTopProtocols() {
        return framePieTopProtocols;
    }

    public void setFramePieTopProtocols(ChartFrame framePieTopProtocols) {this.framePieTopProtocols = framePieTopProtocols;}

    public ChartFrame getFramePieTopApps() {
        return framePieTopApps;
    }

    public void setFramePieTopApps(ChartFrame framePieTopApps) {
        this.framePieTopApps = framePieTopApps;
    }

    private ChartFrame frameBarTopRx;
    private ChartFrame frameBarTopTx;
    private ChartFrame frameBarTopProtocols;
    private ChartFrame frameBarTopApps;

    private ChartFrame framePieTopTx;
    private ChartFrame framePieTopRx;
    private ChartFrame framePieTopProtocols;
    private ChartFrame framePieTopApps;

    private ChartPanel panelChartBarTopRx;
    private ChartPanel panelChartBarTopTx;
    private ChartPanel panelChartBarTopProtocols;
    private ChartPanel panelChartBarTopApps;

    private ChartPanel panelChartPieTopRx;
    private ChartPanel panelChartPieTopTx;
    private ChartPanel panelChartPieTopProtocols;
    private ChartPanel panelChartPieTopApps;

    private JLabel labelFromAtButtons;
    private JLabel labelToAtButtons;
    private JLabel labelFromAtSlider;
    private JLabel labelToAtSlider;

    private IGuiForm guiForm;
    private JSONSaver jsonSaver = new JSONSaver();

    public JPanel getPanelRootLauncher() {
        return panelRootLauncher;
    }

    public JButton getButtonTopRx() {
        return buttonTopRx;
    }

    public JButton getButtonTopTx() {
        return buttonTopTx;
    }

    public JButton getButtonTopProtocols() {
        return buttonTopProtocols;
    }

    public JButton getButtonTopApps() {
        return buttonTopApps;
    }

    public JButton getButtonBack() {
        return buttonBack;
    }

    public JFrame getjFrameMain() {
        return jFrameMain;
    }

    public JButton getButtonShowBarChart() {
        return buttonShowBarChart;
    }

    public JButton getButtonShowPieChart() {
        return buttonShowPieChart;
    }

    public JButton getButtonToJSON() {
        return buttonToJSON;
    }

    public JSlider getSliderFrom() {
        return sliderFrom;
    }

    public JSlider getSliderTo() {
        return sliderTo;
    }

    public Date getDateFirst() {
        return dateFirst;
    }

    public void setDateFirst(Date dateFirst) {
        this.dateFirst = dateFirst;
    }

    public void setDateLast(Date dateLast) {
        this.dateLast = dateLast;
    }

    public ChartPanel getPanelChartBarTopRx() {
        return panelChartBarTopRx;
    }

    public void setPanelChartBarTopRx(ChartPanel panelChartBarTopRx) {
        this.panelChartBarTopRx = panelChartBarTopRx;
    }

    public ChartPanel getPanelChartBarTopTx() {
        return panelChartBarTopTx;
    }

    public void setPanelChartBarTopTx(ChartPanel panelChartBarTopTx) {
        this.panelChartBarTopTx = panelChartBarTopTx;
    }

    public ChartPanel getPanelChartBarTopProtocols() {
        return panelChartBarTopProtocols;
    }

    public void setPanelChartBarTopProtocols(ChartPanel panelChartBarTopProtocols) { this.panelChartBarTopProtocols = panelChartBarTopProtocols; }

    public ChartPanel getPanelChartBarTopApps() {
        return panelChartBarTopApps;
    }

    public void setPanelChartBarTopApps(ChartPanel panelChartBarTopApps) { this.panelChartBarTopApps = panelChartBarTopApps; }

    public ChartPanel getPanelChartPieTopRx() {
        return panelChartPieTopRx;
    }

    public void setPanelChartPieTopRx(ChartPanel panelChartPieTopRx) {
        this.panelChartPieTopRx = panelChartPieTopRx;
    }

    public ChartPanel getPanelChartPieTopTx() {
        return panelChartPieTopTx;
    }

    public void setPanelChartPieTopTx(ChartPanel panelChartPieTopTx) {
        this.panelChartPieTopTx = panelChartPieTopTx;
    }

    public ChartPanel getPanelChartPieTopProtocols() {
        return panelChartPieTopProtocols;
    }

    public void setPanelChartPieTopProtocols(ChartPanel panelChartPieTopProtocols) { this.panelChartPieTopProtocols = panelChartPieTopProtocols; }

    public ChartPanel getPanelChartPieTopApps() {
        return panelChartPieTopApps;
    }

    public void setPanelChartPieTopApps(ChartPanel panelChartPieTopApps) { this.panelChartPieTopApps = panelChartPieTopApps; }

    public void setGuiForm(IGuiForm guiForm) {
        this.guiForm = guiForm;
    }

    public void startDraw(){
        setLabelsDate();
        setSliderFrom();
        setSliderTo();
        ToolTipManager.sharedInstance().setInitialDelay(300);

        buttonTopRx = new JButton(new ImageIcon(getClass().getResource("/main/resources/receivers3.png")));
        buttonTopTx = new JButton(new ImageIcon(getClass().getResource("/main/resources/Tx.png")));
        buttonTopProtocols = new JButton(new ImageIcon(getClass().getResource("/main/resources/protocols2.png")));
        buttonTopApps = new JButton(new ImageIcon(getClass().getResource("/main/resources/apps.png")));

        buttonTopRx.setToolTipText("Top 10 Receivers");
        buttonTopTx.setToolTipText("Top 10 Transmitters");
        buttonTopProtocols.setToolTipText("Top 3 Protocols");
        buttonTopApps.setToolTipText("Top 10 Apps");

        jFrameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelRootLauncher = new JPanel();

        panelRootLauncher.add(buttonTopRx);
        panelRootLauncher.add(buttonTopTx);
        panelRootLauncher.add(buttonTopProtocols);
        panelRootLauncher.add(buttonTopApps);

        jFrameMain.setContentPane(panelRootLauncher);
        jFrameMain.setBounds(0, 0, 170, 150);
        jFrameMain.setVisible(true);
    }

    public ChartFrame createChartFrame(JFreeChart chartBar, String categoryName, int locationX, int locationY, int sizeX, int sizeY){
        ChartPanel chartBarPanel = new ChartPanel(chartBar);
        chartBarPanel.setLayout(new GridLayout());
        ChartFrame chartFrame = new ChartFrame(categoryName, null);
        chartFrame.setLayout(new GridLayout());
        chartFrame.setBounds(locationX, locationY, sizeX, sizeY);
        chartFrame.setDefaultCloseOperation(ChartFrame.HIDE_ON_CLOSE);
        chartFrame.add(chartBarPanel);
        chartFrame.setVisible(true);
        return chartFrame;
    }

    public void setSliderFrom() {
            panelCenter.add(sliderFrom = createSlider(0));
        }

    public void setSliderTo() {
        panelSlider.add(sliderTo = createSlider(1000));
    }

    public JSlider createSlider(int initialValue) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 1000, initialValue);
        Hashtable labelTable = new Hashtable();
        int i = -200;
        for (Date date : dateArrayForSliderLabels) {
            labelTable.put(i += 200, new JLabel(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(date)));
        }
        slider.setLabelTable(labelTable);
        slider.setMinorTickSpacing(100);
        slider.setMajorTickSpacing(200);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }

    public void setJFrameMain(String titleStr) {
        jFrameMain.setTitle(titleStr);
        jFrameMain.setContentPane(rootPanelChart);
        jFrameMain.setVisible(true);
        jFrameMain.setBounds(0, 0, 900, 200);
    }

    public JFreeChart createJFreeChartBar(String categoryName, Map<String, Long> map) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        map.entrySet().stream().limit(10).forEach(e -> dataset.setValue(e.getValue(), "", e.getKey()));
        JFreeChart chartBar = ChartFactory.createBarChart3D(categoryName
                + getDateRangeString(), "",
                "Packets", dataset,  PlotOrientation.HORIZONTAL,
                false, true, false);
        return chartBar;
    }

    public JFreeChart createJFreeChartPie(String categoryName, Map<String, Long> map) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        map.entrySet().stream().limit(10).forEach(e -> dataset.setValue(e.getKey(), e.getValue()));
        JFreeChart chartPie = ChartFactory.createPieChart3D(categoryName + getDateRangeString(),
                dataset, true, true, true);
        return chartPie;
    }

    public String getDateRangeString() {
        return (" from: "
                + new SimpleDateFormat("yyyy-MM-dd hh:mm").format(dateFrom)
                + " to: "
                + new SimpleDateFormat("yyyy-MM-dd hh:mm").format(dateTo));
    }

    public void setDateArrayForSliderLabels(Date[] dateArrayForSliderLabels) {
        this.dateArrayForSliderLabels = dateArrayForSliderLabels;
    }

    public void setLabelsDate() {
        labelFromAtButtons.setText("Date from: " + (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateFrom)));
        labelToAtButtons.setText("Date to: " + (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateTo)));
    }

    public void checkSlidersPropriety(){
        if (dateFrom.compareTo(dateTo) >= 0 ){
            buttonShowBarChart.setEnabled(false);
            buttonShowPieChart.setEnabled(false);
            buttonToJSON.setEnabled(false);
        } else {
            buttonShowBarChart.setEnabled(true);
            buttonShowPieChart.setEnabled(true);
            buttonToJSON.setEnabled(true);
        }
    }

    public String getCategoryFlag() {
        return categoryFlag;
    }

    public void setCategoryFlag(String categoryFlag) {
        this.categoryFlag = categoryFlag;
    }
}