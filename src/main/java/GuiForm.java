package main.java;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;

public class GuiForm implements IGuiForm {

    private int diffSec;

    private JFrame jFrame = new JFrame("Main menu");

    private JPanel rootPanelChart;
    private JPanel panelSlider;
    private JPanel panelButton;
    private JPanel panelCenter;
    private JPanel rootPanelLauncher;

    public String categoryFlag = "None";

    private JButton buttonTopRx;
    private JButton buttonTopTx;
    private JButton buttonTopProtocols;
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

    Timer myTimer = new Timer();

    private JSlider sliderFrom;
    private JSlider sliderTo;

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

    private JLabel labelFrom;
    private JLabel labelTo;
    private JLabel labelFrom2;
    private JLabel labelTo2;

    private IGuiForm guiForm;
    private ControllerGui controller;
    private JSONSaver jsonSaver = new JSONSaver();

    private ActionListener listenerShowBarChart;
    private ActionListener listenerShowPieChart;
    private ActionListener listenerToJSON;
    private ChangeListener listenerSliderFrom;
    private ChangeListener listenerSliderTo;

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

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rootPanelLauncher = new JPanel();

        rootPanelLauncher.add(buttonTopRx);
        rootPanelLauncher.add(buttonTopTx);
        rootPanelLauncher.add(buttonTopProtocols);
        rootPanelLauncher.add(buttonTopApps);
        buttonTopRx.addActionListener(e -> {
            setJframeMainMenu("Top 10 Receivers");
            drawTopRx();
        });
        buttonTopTx.addActionListener(e -> {
            setJframeMainMenu("Top 10 Transmitters");
            drawTopTx();
        });
        buttonTopProtocols.addActionListener(e -> {
            setJframeMainMenu("Top 10 Protocols");
            drawTopProtocols();
        });
        buttonTopApps.addActionListener(e -> {
            setJframeMainMenu("Top 10 Apps");
            drawTopApps();
        });
        buttonBack.addActionListener(e -> {
            buttonShowBarChart.removeActionListener(listenerShowBarChart);
            buttonShowPieChart.removeActionListener(listenerShowPieChart);
            buttonToJSON.removeActionListener(listenerToJSON);
            sliderFrom.removeChangeListener(listenerSliderFrom);
            sliderTo.removeChangeListener(listenerSliderTo);
            jFrame.setTitle("Main menu");
            jFrame.setContentPane(rootPanelLauncher);
            jFrame.setBounds(0, 0, 170, 150);
            jFrame.setVisible(true);
        });
        jFrame.setContentPane(rootPanelLauncher);
        jFrame.setBounds(0, 0, 170, 150);
        jFrame.setVisible(true);
    }

    private void drawTopRx() {
        categoryFlag = "Rx";
        listenerShowBarChart = evt -> {
            frameBarTopRx = createChartFrame(createChartBarTopRx(), "Top 10 Rx", 0, 205, 900, 300);
        };
        buttonShowBarChart.addActionListener(listenerShowBarChart);
        listenerShowPieChart = evt -> {
            framePieTopRx = createChartFrame(createJFreeChartPieTopRx(), "Top 10 Rx", 0, 510, 900, 300 );
        };
        buttonShowPieChart.addActionListener(listenerShowPieChart);
        listenerToJSON = evt -> {
            updateChartsTopRx();
            if (controller.getMapTopRxAnalyzer(dateFrom, dateTo).isEmpty()) {
                showInfoBox("It's nothing to save! The Top Rating List is Empty!\n", "Save error");
            } else {
                jsonSaver.setFileName("Top_10_Rx_", dateFrom, dateTo);
                jsonSaver.createJSONFile(controller.getMapTopRxAnalyzer(dateFrom, dateTo));
                showInfoBox("The file successfully saved to:\n" + jsonSaver.getFileName(), "Save result");
            }
        };
        buttonToJSON.addActionListener(listenerToJSON);
        listenerSliderFrom = createChangeListener();
        sliderFrom.addChangeListener(listenerSliderFrom);
        listenerSliderTo = createChangeListener();
        sliderTo.addChangeListener(listenerSliderTo);
    }

    private void drawTopTx(){
        categoryFlag = "Tx";
        listenerShowBarChart = evt -> {
            frameBarTopTx = createChartFrame(createChartBarTopTx(), "Top 10 Tx", 910, 205, 900, 300);
        };
        buttonShowBarChart.addActionListener(listenerShowBarChart);
        listenerShowPieChart = evt -> {
            framePieTopTx = createChartFrame(createJFreeChartPieTopTx(), "Top 10 Tx", 910, 510, 900, 300 );
        };
        buttonShowPieChart.addActionListener(listenerShowPieChart);

        listenerToJSON = evt -> {
            updateChartsTopTx();
            if (controller.getMapTopTxAnalyzer(dateFrom,dateTo).isEmpty()) {
                showInfoBox("It's nothing to save! The Top Rating List is Empty!\n", "Save error");
            } else {
                jsonSaver.setFileName("Top_10_Transmitters_", dateFrom, dateTo);
                jsonSaver.createJSONFile(controller.getMapTopTxAnalyzer(dateFrom, dateTo));
                showInfoBox("The file successfully saved to:\n" + jsonSaver.getFileName(), "Save result");
            }
        };
        buttonToJSON.addActionListener (listenerToJSON) ;
        listenerSliderFrom = createChangeListener();
        sliderFrom.addChangeListener(listenerSliderFrom);
        listenerSliderTo = createChangeListener();
        sliderTo.addChangeListener(listenerSliderTo);
    }

    private void drawTopProtocols() {
        categoryFlag = "Protocols";

        listenerShowBarChart = evt -> {
            frameBarTopProtocols = createChartFrame(createChartBarTopProtocols(), "Top 10 Protocols", 0, 205, 900, 300);
        };
        buttonShowBarChart.addActionListener(listenerShowBarChart);
        listenerShowPieChart = evt -> {
            framePieTopProtocols = createChartFrame(createJFreeChartPieTopProtocols(), "Top 10 Protocols", 0, 510, 900, 300 );
        };
        buttonShowPieChart.addActionListener(listenerShowPieChart);
        listenerToJSON = evt -> {
            updateChartsTopProtocols();
            if (controller.getMapTopProtocolsAnalyzer(dateFrom, dateTo).isEmpty()) {
                showInfoBox("It's nothing to save! The Top Rating List is Empty!\n", "Save error");
            } else {
                jsonSaver.setFileName("Top_10_Protocols_", dateFrom, dateTo);
                jsonSaver.createJSONFile(controller.getMapTopProtocolsAnalyzer(dateFrom, dateTo));
                showInfoBox("The file successfully saved to:\n" + jsonSaver.getFileName(), "Save result");
            }
        };
        buttonToJSON.addActionListener(listenerToJSON);
        listenerSliderFrom = createChangeListener();
        sliderFrom.addChangeListener(listenerSliderFrom);
        listenerSliderTo = createChangeListener();
        sliderTo.addChangeListener(listenerSliderTo);
    }

    private void drawTopApps() {
        categoryFlag = "Apps";

        listenerShowBarChart = evt -> {
            frameBarTopApps = createChartFrame(createChartBarTopApps(), "Top 10 Apps", 0, 205, 900, 300);
        };
        buttonShowBarChart.addActionListener(listenerShowBarChart);
        listenerShowPieChart = evt -> {
            framePieTopApps = createChartFrame(createJFreeChartPieTopApps(), "Top 10 Apps", 0, 510, 900, 300 );
        };
        buttonShowPieChart.addActionListener(listenerShowPieChart);
        listenerToJSON = evt -> {
            updateChartsTopApps();
            if (controller.getMapTopAppsAnalyzer(dateFrom, dateTo).isEmpty()) {
                showInfoBox("It's nothing to save! The Top Rating List is Empty!\n", "Save error");
            } else {
                jsonSaver.setFileName("Top_10_Apps_", dateFrom, dateTo);
                jsonSaver.createJSONFile(controller.getMapTopAppsAnalyzer(dateFrom, dateTo));
                showInfoBox("The file successfully saved to:\n" + jsonSaver.getFileName(), "Save result");
            }
        };
        buttonToJSON.addActionListener(listenerToJSON);
        listenerSliderFrom = createChangeListener();
        sliderFrom.addChangeListener(listenerSliderFrom);
        listenerSliderTo = createChangeListener();
        sliderTo.addChangeListener(listenerSliderTo);
    }

    private ChangeListener createChangeListener() {
        return evt -> {
            JSlider source = (JSlider)evt.getSource();
            dateFrom = getDateOfSlider(sliderFrom.getValue());
            dateTo = getDateOfSlider(sliderTo.getValue());
            setLabelsDate(); // get the slider
            if (!source.getValueIsAdjusting()) {
                dateFrom = getDateOfSlider(sliderFrom.getValue());
                dateTo = getDateOfSlider(sliderTo.getValue());
                setLabelsDate();
                controller.reparseRecordListDateRange(dateFrom, dateTo);
                checkSlidersPropriety();
            }
        };
    }

    private ChartFrame createChartFrame(JFreeChart chartBar, String categoryName, int locationX, int locationY, int sizeX, int sizeY){
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

    private void setSliderFrom () {
            panelCenter.add(sliderFrom = createSlider(0));
        }

    private void setSliderTo () {
        panelSlider.add(sliderTo = createSlider(1000));
    }

    private JSlider createSlider(int initialValue) {
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

    private void setJframeMainMenu(String titleStr) {
        jFrame.setTitle(titleStr);
        jFrame.setContentPane(rootPanelChart);
        jFrame.setVisible(true);
        jFrame.setBounds(0, 0, 900, 200);
    }

    private JFreeChart createChartBarTopRx() {
        return createJFreeChartBar("Top 10 Received Packets",
                controller.getMapTopRxAnalyzer(dateFrom, dateTo));
    }

    private JFreeChart createChartBarTopTx() {
        return createJFreeChartBar("Top 10 Transmitted Packets", controller.getMapTopTxAnalyzer(dateFrom, dateTo));
    }

    private JFreeChart createChartBarTopProtocols() {
        return createJFreeChartBar("Top 10 Protocols", controller.getMapTopProtocolsAnalyzer(dateFrom, dateTo));
    }

    private JFreeChart createChartBarTopApps() {
        return createJFreeChartBar("Top 10 Applications", controller.getMapTopAppsAnalyzer(dateFrom, dateTo));
    }

    private JFreeChart createJFreeChartPieTopRx() {
        return createJFreeChartPie("Top 10 Received Packets",
                controller.getMapTopRxAnalyzer(dateFrom, dateTo));
    }

    private JFreeChart createJFreeChartPieTopTx() {
        return createJFreeChartPie("Top 10 Transmitted Packets", controller.getMapTopTxAnalyzer(dateFrom, dateTo));
    }

    private JFreeChart createJFreeChartPieTopProtocols() {
        return createJFreeChartPie("Top 3 Protocols",
                controller.getMapTopProtocolsAnalyzer(dateFrom, dateTo));
    }

    private JFreeChart createJFreeChartPieTopApps() {
        return createJFreeChartPie("Top 10 Applications",
                controller.getMapTopAppsAnalyzer(dateFrom, dateTo));
    }

    private JFreeChart createJFreeChartBar(String categoryName, Map <String, Long> map ) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();


        map.entrySet().stream().limit(10).forEach(e -> dataset.setValue(e.getValue(), "", e.getKey()));

        JFreeChart chartBar = ChartFactory.createBarChart3D(categoryName
                        + getDateRangeString(), "",
                "Packets", dataset,  PlotOrientation.HORIZONTAL,
                false, true, false);
        return chartBar;
    }

    private JFreeChart createJFreeChartPie(String categoryName, Map <String, Long> map ) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        map.entrySet().stream().limit(10).forEach(e -> dataset.setValue(e.getKey(), e.getValue()));

        JFreeChart chartPie = ChartFactory.createPieChart3D(categoryName + getDateRangeString(),
                dataset, true, true, true);
        return chartPie;
    }

    private String getDateRangeString() {
        return (" from: "
                + new SimpleDateFormat("yyyy-MM-dd hh:mm").format(dateFrom)
                + " to: "
                + new SimpleDateFormat("yyyy-MM-dd hh:mm").format(dateTo));
    }

    public void updateChartsTopRx() {
        try {
            if (frameBarTopRx.isShowing()) {      // to prevent showing the chart if it's closed during moving the slider
                frameBarTopRx.getContentPane().removeAll();
                panelChartBarTopRx = new ChartPanel(createChartBarTopRx());
                frameBarTopRx.add(panelChartBarTopRx);
                frameBarTopRx.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("frameBarTopRx exception catched: " + e);
        }
        try {
            if (framePieTopRx.isShowing()) {
                framePieTopRx.getContentPane().removeAll();
                panelChartPieTopRx = new ChartPanel(createJFreeChartPieTopRx());
                framePieTopRx.add(panelChartPieTopRx, BorderLayout.CENTER);
                framePieTopRx.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("framePieTopRx exception catched: " + e);
        }
    }

    public void updateChartsTopTx() {
        try {
            if (frameBarTopTx.isShowing()) {
                frameBarTopTx.getContentPane().removeAll();
                panelChartBarTopTx = new ChartPanel(createChartBarTopTx());
                frameBarTopTx.add(panelChartBarTopTx);
                frameBarTopTx.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("frameBarTopTx exception catched: " + e);
        }
        try {
            if (framePieTopTx.isShowing()) {
                framePieTopTx.getContentPane().removeAll();
                panelChartPieTopTx = new ChartPanel(createJFreeChartPieTopTx());
                framePieTopTx.add(panelChartPieTopTx, BorderLayout.CENTER);
                framePieTopTx.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("framePieTopTx exception catched: " + e);
        }
    }

    public void updateChartsTopProtocols() {
        try {
            if (frameBarTopProtocols.isShowing()) {      // to prevent showing the chart if it's closed during moving the slider
                frameBarTopProtocols.getContentPane().removeAll();
                panelChartBarTopProtocols = new ChartPanel(createChartBarTopProtocols());
                frameBarTopProtocols.add(panelChartBarTopProtocols);
                frameBarTopProtocols.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("frameBarTopProtocols exception catched: " + e);
        }
        try {
            if (framePieTopProtocols.isShowing()) {
                framePieTopProtocols.getContentPane().removeAll();
                panelChartPieTopProtocols = new ChartPanel(createJFreeChartPieTopProtocols());
                framePieTopProtocols.add(panelChartPieTopProtocols, BorderLayout.CENTER);
                framePieTopProtocols.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("framePieTopProtocols exception catched: " + e);
        }
    }

    public void updateChartsTopApps() {
        try {
            if (frameBarTopApps.isShowing()) {      // to prevent showing the chart if it's closed during moving the slider
                frameBarTopApps.getContentPane().removeAll();
                panelChartBarTopApps = new ChartPanel(createChartBarTopApps());
                frameBarTopApps.add(panelChartBarTopApps);
                frameBarTopApps.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("frameBarTopApps exception catched: " + e);
        }
        try {
            if (framePieTopApps.isShowing()) {
                framePieTopApps.getContentPane().removeAll();
                panelChartPieTopApps = new ChartPanel(createJFreeChartPieTopApps());
                framePieTopApps.add(panelChartPieTopApps, BorderLayout.CENTER);
                framePieTopApps.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("framePieTopApps exception catched: " + e);
        }
    }

    private void setLabelsDate() {
        labelFrom.setText("Date from: " + (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateFrom)));
        labelTo.setText("Date to: " + (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateTo)));
    }

    private static void showInfoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    private void checkSlidersPropriety(){
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

    public Date getDateOfSlider (int sliderValue){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateFirst);
        cal.add(Calendar.SECOND, controller.getDiffSec()*sliderValue/1000 );
        return cal.getTime();
    }

    public String getCategoryFlag() {
        return categoryFlag;
    }

    public void setCategoryFlag(String categoryFlag) {
        this.categoryFlag = categoryFlag;
    }

    }




