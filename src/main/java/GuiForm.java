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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GuiForm implements IGuiForm {

    private int diffSec;

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

    public void setFrameBarTopProtocols(ChartFrame frameBarTopProtocols) {
        this.frameBarTopProtocols = frameBarTopProtocols;
    }

    
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

    public void setFramePieTopProtocols(ChartFrame framePieTopProtocols) {
        this.framePieTopProtocols = framePieTopProtocols;
    }

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
    private ControllerGui controller;
    private JSONSaver jsonSaver = new JSONSaver();

//    private ActionListener listenerShowBarChart;
//    private ActionListener listenerShowPieChart;
//    private ActionListener listenerToJSON;
//    private ChangeListener listenerSliderFrom;
//    private ChangeListener listenerSliderTo;

    public JPanel getPanelRootLauncher() {
        return panelRootLauncher;
    }

    public JButton getButtonTopRx() {
        return buttonTopRx;
    }

    public void setButtonTopRx(JButton buttonTopRx) {
        this.buttonTopRx = buttonTopRx;
    }

    public JButton getButtonTopTx() {
        return buttonTopTx;
    }

    public void setButtonTopTx(JButton buttonTopTx) {
        this.buttonTopTx = buttonTopTx;
    }

    public JButton getButtonTopProtocols() {
        return buttonTopProtocols;
    }

    public void setButtonTopProtocols(JButton buttonTopProtocols) {
        this.buttonTopProtocols = buttonTopProtocols;
    }

    public JButton getButtonTopApps() {
        return buttonTopApps;
    }

    public void setButtonTopApps(JButton buttonTopApps) {
        this.buttonTopApps = buttonTopApps;
    }

    public JButton getButtonBack() {
        return buttonBack;
    }

    public void setButtonBack(JButton buttonBack) {
        this.buttonBack = buttonBack;
    }

    public JFrame getjFrameMain() {
        return jFrameMain;
    }

    public void setjFrameMain(JFrame jFrameMain) {
        this.jFrameMain = jFrameMain;
    }

    public JButton getButtonShowBarChart() {
        return buttonShowBarChart;
    }

    public void setButtonShowBarChart(JButton buttonShowBarChart) {
        this.buttonShowBarChart = buttonShowBarChart;
    }

    public JButton getButtonShowPieChart() {
        return buttonShowPieChart;
    }

    public void setButtonShowPieChart(JButton buttonShowPieChart) {
        this.buttonShowPieChart = buttonShowPieChart;
    }

    public JButton getButtonToJSON() {
        return buttonToJSON;
    }

    public void setButtonToJSON(JButton buttonToJSON) {
        this.buttonToJSON = buttonToJSON;
    }

    public JSlider getSliderFrom() {
        return sliderFrom;
    }

    public void setSliderFrom(JSlider sliderFrom) {
        this.sliderFrom = sliderFrom;
    }

    public JSlider getSliderTo() {
        return sliderTo;
    }

    public void setSliderTo(JSlider sliderTo) {
        this.sliderTo = sliderTo;
    }

    public void startDraw(ControllerGui controller, IGuiForm guiForm) throws ParseException {

        this.guiForm = guiForm;
        this.controller = controller;
        this.dateFirst = controller.getDateFirst();
        this.dateLast = controller.getDateLast();

        dateFrom = controller.getDateFirst();
        dateTo = controller.getDateLast();
        dateArrayForSliderLabels = controller.getDateArrayForSliderLabels();
        controller.setDateArrayForSliderLabels();
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

//    public ChangeListener createChangeListener() {
//        return evt -> {
//            JSlider source = (JSlider)evt.getSource();
//            dateFrom = getDateOfSlider(sliderFrom.getValue());
//            dateTo = getDateOfSlider(sliderTo.getValue());
//            setLabelsDate();
//            if (!source.getValueIsAdjusting()) {
//                dateFrom = getDateOfSlider(sliderFrom.getValue());
//                dateTo = getDateOfSlider(sliderTo.getValue());
//                setLabelsDate();
//                controller.reparseRecordListDateRange(dateFrom, dateTo);
//                checkSlidersPropriety();
//            }
//        };
//    }

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

    public void setJFrameMainMenu(String titleStr) {
        jFrameMain.setTitle(titleStr);
        jFrameMain.setContentPane(rootPanelChart);
        jFrameMain.setVisible(true);
        jFrameMain.setBounds(0, 0, 900, 200);
    }

//    public JFreeChart createJFreeChartBarRx() {
//        return createJFreeChartBar("Top 10 Received Packets",
//                controller.getMapTopRxAnalyzer(dateFrom, dateTo));
//    }
//
//    public JFreeChart createJFreeChartBarTx() {
//        return createJFreeChartBar("Top 10 Transmitted Packets", controller.getMapTopTxAnalyzer(dateFrom, dateTo));
//    }
//
//    public JFreeChart createJFreeChartBarProtocols() {
//        return createJFreeChartBar("Top 10 Protocols", controller.getMapTopProtocolsAnalyzer(dateFrom, dateTo));
//    }
//
//    public JFreeChart createJFreeChartBarApps() {
//        return createJFreeChartBar("Top 10 Applications", controller.getMapTopAppsAnalyzer(dateFrom, dateTo));
//    }
//
//    public JFreeChart createJFreeChartPieRx() {
//        return createJFreeChartPie("Top 10 Received Packets",
//                controller.getMapTopRxAnalyzer(dateFrom, dateTo));
//    }
//
//    public JFreeChart createJFreeChartPieTx() {
//        return createJFreeChartPie("Top 10 Transmitted Packets", controller.getMapTopTxAnalyzer(dateFrom, dateTo));
//    }
//
//    public JFreeChart createJFreeChartPieProtocols() {
//        return createJFreeChartPie("Top 3 Protocols",
//                controller.getMapTopProtocolsAnalyzer(dateFrom, dateTo));
//    }
//
//    public JFreeChart createJFreeChartPieApps() {
//        return createJFreeChartPie("Top 10 Applications",
//                controller.getMapTopAppsAnalyzer(dateFrom, dateTo));
//    }

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

//    public void updateChartsTopRx() {
//        try {
//            if (frameBarTopRx.isShowing()) {      // to prevent showing the chart if it's closed during moving the slider
//                frameBarTopRx.getContentPane().removeAll();
//                panelChartBarTopRx = new ChartPanel(createJFreeChartBarRx());
//                frameBarTopRx.add(panelChartBarTopRx);
//                frameBarTopRx.setVisible(true);
//            }
//        } catch (Exception e) {
//            System.out.println("frameBarTopRx exception catched: " + e);
//        }
//        try {
//            if (framePieTopRx.isShowing()) {
//                framePieTopRx.getContentPane().removeAll();
//                panelChartPieTopRx = new ChartPanel(createJFreeChartPieRx());
//                framePieTopRx.add(panelChartPieTopRx, BorderLayout.CENTER);
//                framePieTopRx.setVisible(true);
//            }
//        } catch (Exception e) {
//            System.out.println("framePieTopRx exception catched: " + e);
//        }
//    }
//
//    public void updateChartsTopTx() {
//        try {
//            if (frameBarTopTx.isShowing()) {
//                frameBarTopTx.getContentPane().removeAll();
//                panelChartBarTopTx = new ChartPanel(createJFreeChartBarTx());
//                frameBarTopTx.add(panelChartBarTopTx);
//                frameBarTopTx.setVisible(true);
//            }
//        } catch (Exception e) {
//            System.out.println("frameBarTopTx exception catched: " + e);
//        }
//        try {
//            if (framePieTopTx.isShowing()) {
//                framePieTopTx.getContentPane().removeAll();
//                panelChartPieTopTx = new ChartPanel(createJFreeChartPieTx());
//                framePieTopTx.add(panelChartPieTopTx, BorderLayout.CENTER);
//                framePieTopTx.setVisible(true);
//            }
//        } catch (Exception e) {
//            System.out.println("framePieTopTx exception catched: " + e);
//        }
//    }
//
//    public void updateChartsTopProtocols() {
//        try {
//            if (frameBarTopProtocols.isShowing()) {      // to prevent showing the chart if it's closed during moving the slider
//                frameBarTopProtocols.getContentPane().removeAll();
//                panelChartBarTopProtocols = new ChartPanel(createJFreeChartBarProtocols());
//                frameBarTopProtocols.add(panelChartBarTopProtocols);
//                frameBarTopProtocols.setVisible(true);
//            }
//        } catch (Exception e) {
//            System.out.println("frameBarTopProtocols exception catched: " + e);
//        }
//        try {
//            if (framePieTopProtocols.isShowing()) {
//                framePieTopProtocols.getContentPane().removeAll();
//                panelChartPieTopProtocols = new ChartPanel(createJFreeChartPieProtocols());
//                framePieTopProtocols.add(panelChartPieTopProtocols, BorderLayout.CENTER);
//                framePieTopProtocols.setVisible(true);
//            }
//        } catch (Exception e) {
//            System.out.println("framePieTopProtocols exception catched: " + e);
//        }
//    }

//    public void updateChartsTopApps() {
//        try {
//            if (frameBarTopApps.isShowing()) {      // to prevent showing the chart if it's closed during moving the slider
//                frameBarTopApps.getContentPane().removeAll();
//                panelChartBarTopApps = new ChartPanel(createJFreeChartBarApps());
//                frameBarTopApps.add(panelChartBarTopApps);
//                frameBarTopApps.setVisible(true);
//            }
//        } catch (Exception e) {
//            System.out.println("frameBarTopApps exception catched: " + e);
//        }
//        try {
//            if (framePieTopApps.isShowing()) {
//                framePieTopApps.getContentPane().removeAll();
//                panelChartPieTopApps = new ChartPanel(createJFreeChartPieApps());
//                framePieTopApps.add(panelChartPieTopApps, BorderLayout.CENTER);
//                framePieTopApps.setVisible(true);
//            }
//        } catch (Exception e) {
//            System.out.println("framePieTopApps exception catched: " + e);
//        }
//    }

    
    public int getDiffSec() {
        return diffSec;
    }

    
    public void setDiffSec(int diffSec) {
        this.diffSec = diffSec;
    }

    public JPanel getRootPanelChart() {
        return rootPanelChart;
    }

    
    public void setRootPanelChart(JPanel rootPanelChart) {
        this.rootPanelChart = rootPanelChart;
    }

    
    public JPanel getPanelSlider() {
        return panelSlider;
    }

    
    public void setPanelSlider(JPanel panelSlider) {
        this.panelSlider = panelSlider;
    }

    
    public JPanel getPanelButton() {
        return panelButton;
    }

    
    public void setPanelButton(JPanel panelButton) {
        this.panelButton = panelButton;
    }

    
    public JPanel getPanelCenter() {
        return panelCenter;
    }

    
    public void setPanelCenter(JPanel panelCenter) {
        this.panelCenter = panelCenter;
    }

    
    public void setPanelRootLauncher(JPanel panelRootLauncher) {
        this.panelRootLauncher = panelRootLauncher;
    }

    
    public Date[] getDateArrayForSliderLabels() {
        return dateArrayForSliderLabels;
    }

    
    public void setDateArrayForSliderLabels(Date[] dateArrayForSliderLabels) {
        this.dateArrayForSliderLabels = dateArrayForSliderLabels;
    }

    
    public Date getDateFirst() {
        return dateFirst;
    }

    
    public void setDateFirst(Date dateFirst) {
        this.dateFirst = dateFirst;
    }

    
    public Date getDateLast() {
        return dateLast;
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

    
    public void setPanelChartBarTopProtocols(ChartPanel panelChartBarTopProtocols) {
        this.panelChartBarTopProtocols = panelChartBarTopProtocols;
    }

    
    public ChartPanel getPanelChartBarTopApps() {
        return panelChartBarTopApps;
    }

    
    public void setPanelChartBarTopApps(ChartPanel panelChartBarTopApps) {
        this.panelChartBarTopApps = panelChartBarTopApps;
    }

    
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

    
    public void setPanelChartPieTopProtocols(ChartPanel panelChartPieTopProtocols) {
        this.panelChartPieTopProtocols = panelChartPieTopProtocols;
    }

    
    public ChartPanel getPanelChartPieTopApps() {
        return panelChartPieTopApps;
    }

    
    public void setPanelChartPieTopApps(ChartPanel panelChartPieTopApps) {
        this.panelChartPieTopApps = panelChartPieTopApps;
    }

    
    public JLabel getLabelFromAtButtons() {
        return labelFromAtButtons;
    }

    
    public void setLabelFromAtButtons(JLabel labelFromAtButtons) {
        this.labelFromAtButtons = labelFromAtButtons;
    }

    
    public JLabel getLabelToAtButtons() {
        return labelToAtButtons;
    }

    
    public void setLabelToAtButtons(JLabel labelToAtButtons) {
        this.labelToAtButtons = labelToAtButtons;
    }

    
    public JLabel getLabelFromAtSlider() {
        return labelFromAtSlider;
    }

    
    public void setLabelFromAtSlider(JLabel labelFromAtSlider) {
        this.labelFromAtSlider = labelFromAtSlider;
    }

    
    public JLabel getLabelToAtSlider() {
        return labelToAtSlider;
    }

    
    public void setLabelToAtSlider(JLabel labelToAtSlider) {
        this.labelToAtSlider = labelToAtSlider;
    }

    
    public IGuiForm getGuiForm() {
        return guiForm;
    }

    
    public void setGuiForm(IGuiForm guiForm) {
        this.guiForm = guiForm;
    }

    
    public ControllerGui getController() {
        return controller;
    }

    
    public void setController(ControllerGui controller) {
        this.controller = controller;
    }

    
    public JSONSaver getJsonSaver() {
        return jsonSaver;
    }

    
    public void setJsonSaver(JSONSaver jsonSaver) {
        this.jsonSaver = jsonSaver;
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




