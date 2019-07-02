package main.java;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class ControllerGui implements IController, IObserver {

    private ITextReader reader;
    private ITextAnalyzer analyzer;
    private IAppOptions options;
    private IErrorLogger logger;
    private IGuiForm guiForm;
    private IJSONSaver jsonSaver;
    private ControllerGui controllerGui;

    private ActionListener listenerShowBarChart;
    private ActionListener listenerShowPieChart;
    private ActionListener listenerToJSON;
    private ChangeListener listenerSliderFrom;
    private ChangeListener listenerSliderTo;

    public void initController(){}

    public void initController(IAppOptions options,
                               ITextReader reader,
                               ITextAnalyzer analyzer,
                               IGuiForm guiForm,
                               IErrorLogger logger,
                               IJSONSaver jsonSaver,
                               ControllerGui controllerGui) {
        this.options = options;
        this.reader = reader;
        this.analyzer = analyzer;
        this.guiForm = guiForm;
        this.logger = logger;
        this.jsonSaver = jsonSaver;
        this.controllerGui = controllerGui;

        try {
            options.parseOptions();
            analyzer.setBuffer(reader.getTextBuffer(options.getFilePath()));
            analyzer.prepareForAnalyze(controllerGui);
            guiForm.startDraw(controllerGui, guiForm);
            addActionListenersMainMenu();
        } catch (IOException e) {
            logger.logIOException(e);
            //             nice report on can't open the file
        } catch (Exception e) {
            logger.logException(e);
            //             nice report here
        }
    }

    public void handleCalculationEvent(ControllerGui controller) {
        System.out.println("event of calculated handled");
        if (guiForm.getCategoryFlag() == "none") {
            System.out.println("Category is not selected!");
        } else if (guiForm.getCategoryFlag() == "Rx") {
            System.out.println(guiForm.getCategoryFlag());
            updateChartsTopRx();
        } else if (guiForm.getCategoryFlag() == "Tx") {
            System.out.println(guiForm.getCategoryFlag());
            updateChartsTopTx();
        } else if (guiForm.getCategoryFlag() == "Protocols") {
            System.out.println(guiForm.getCategoryFlag());
            updateChartsTopProtocols();
        } else if (guiForm.getCategoryFlag() == "Apps") {
            System.out.println(guiForm.getCategoryFlag());
            updateChartsTopApps();
        }
    }

    public Date getDateFirst() throws ParseException {
        return analyzer.getDateFirst();
    }
    public Date getDateLast() throws ParseException {
        return analyzer.getDateLast();
    }

    public Date[] getDateArrayForSliderLabels(){
        return analyzer.getDateArrayForSliderLabels();
    }

    public void setDateArrayForSliderLabels(){
        analyzer.setDateArrayForSliderLabels();
    }

    public Map<String, Long> getMapTopRxAnalyzer(Date dateFrom, Date dateTo) {
        return analyzer.getMapTopRx(dateFrom, dateTo);
    }

    public Map<String, Long> getMapTopTxAnalyzer(Date dateFrom, Date dateTo) {
        return analyzer.getMapTopTx(dateFrom, dateTo);
    }

    public Map<String, Long> getMapTopProtocolsAnalyzer(Date dateFrom, Date dateTo) {
        return analyzer.getMapTopProtocols(dateFrom, dateTo);
    }

    public Map<String, Long> getMapTopAppsAnalyzer(Date dateFrom, Date dateTo) {
        return analyzer.getMapTopApps(dateFrom, dateTo);
    }

    public void reparseRecordListDateRange(Date dateFrom, Date dateTo) {
        try {
            analyzer.reparseRecordListDateRange(dateFrom, dateTo);
        } catch (ParseException e) {
            logger.logParseException(e);
        }
    }

    public int getDiffSec(){
         return analyzer.getDiffSec();
    }

    private void addActionListenersMainMenu() {
        guiForm.getButtonTopRx().addActionListener(e -> {
                    guiForm.setJFrameMainMenu("Top 10 Transmitters");
                    drawTopRx();
                }
        );
        guiForm.getButtonTopTx().addActionListener(e -> {
                    guiForm.setJFrameMainMenu("Top 10 Transmitters");
                    drawTopTx();
                }
        );
        guiForm.getButtonTopProtocols().addActionListener(e -> {
                    guiForm.setJFrameMainMenu("Top 10 Transmitters");
                    drawTopProtocols();
                }
        );
        guiForm.getButtonTopApps().addActionListener(e -> {
                    guiForm.setJFrameMainMenu("Top 10 Transmitters");
                    drawTopApps();
                }
        );
        guiForm.getButtonBack().addActionListener(e -> {
            guiForm.getButtonShowBarChart().removeActionListener(listenerShowBarChart);
            guiForm.getButtonShowPieChart().removeActionListener(listenerShowPieChart);
            guiForm.getButtonToJSON().removeActionListener(listenerToJSON);
            guiForm.getSliderFrom().removeChangeListener(listenerSliderFrom);
            guiForm.getSliderTo().removeChangeListener(listenerSliderTo);
            guiForm.getjFrameMain().setTitle("Main menu");
            guiForm.getjFrameMain().setContentPane(guiForm.getPanelRootLauncher());
            guiForm.getjFrameMain().setBounds(0, 0, 170, 150);
            guiForm.getjFrameMain().setVisible(true);
        });
    }

    private void drawTopRx(){
        guiForm.setCategoryFlag("Rx");

        listenerShowBarChart = evt -> {
            guiForm.setFrameBarTopRx(
                    guiForm.createChartFrame(
                            createJFreeChartBarRx(),
                            "Top 10 Rx", 0, 205, 900, 300));
        };

        guiForm.getButtonShowBarChart().addActionListener(listenerShowBarChart);

        listenerShowPieChart = evt -> {
            guiForm.setFramePieTopRx(guiForm.createChartFrame(
                    createJFreeChartPieRx(),
                    "Top 10 Rx", 0, 510, 900, 300 ));
        };

        guiForm.getButtonShowPieChart().addActionListener(listenerShowPieChart);

        listenerToJSON = evt -> {
            updateChartsTopRx();
            if (getMapTopRxAnalyzer(guiForm.getDateFrom(), guiForm.getDateTo()).isEmpty()) {
                showInfoBox("It's nothing to save! The Top Rating List is Empty!\n", "Save error");
            } else {
                jsonSaver.setFileName("Top_10_Rx_", guiForm.getDateFrom(), guiForm.getDateTo());
                jsonSaver.createJSONFile(getMapTopRxAnalyzer(guiForm.getDateFrom(), guiForm.getDateTo()));
                showInfoBox("The file successfully saved to:\n" + jsonSaver.getFileName(), "Save result");
            }
        };

        guiForm.getButtonToJSON().addActionListener(listenerToJSON);
        listenerSliderFrom = createChangeListener();
        guiForm.getSliderFrom().addChangeListener(listenerSliderFrom);
        listenerSliderTo = createChangeListener();
        guiForm.getSliderFrom().addChangeListener(listenerSliderTo);
    }

    private void drawTopTx(){
        guiForm.setCategoryFlag("Tx");

        listenerShowBarChart = evt -> {
            guiForm.setFrameBarTopTx(
                    guiForm.createChartFrame(
                            createJFreeChartBarTx(),
                            "Top 10 Tx", 0, 205, 900, 300));
        };

        guiForm.getButtonShowBarChart().addActionListener(listenerShowBarChart);

        listenerShowPieChart = evt -> {
            guiForm.setFramePieTopTx(guiForm.createChartFrame(
                    createJFreeChartPieTx(),
                    "Top 10 Tx", 0, 510, 900, 300 ));
        };

        guiForm.getButtonShowPieChart().addActionListener(listenerShowPieChart);

        listenerToJSON = evt -> {
            updateChartsTopTx();
            if (getMapTopTxAnalyzer(guiForm.getDateFrom(), guiForm.getDateTo()).isEmpty()) {
                showInfoBox("It's nothing to save! The Top Rating List is Empty!\n", "Save error");
            } else {
                jsonSaver.setFileName("Top_10_Tx_", guiForm.getDateFrom(), guiForm.getDateTo());
                jsonSaver.createJSONFile(getMapTopTxAnalyzer(guiForm.getDateFrom(), guiForm.getDateTo()));
                showInfoBox("The file successfully saved to:\n" + jsonSaver.getFileName(), "Save result");
            }
        };

        guiForm.getButtonToJSON().addActionListener(listenerToJSON);
        listenerSliderFrom = createChangeListener();
        guiForm.getSliderFrom().addChangeListener(listenerSliderFrom);
        listenerSliderTo = createChangeListener();
        guiForm.getSliderFrom().addChangeListener(listenerSliderTo);
    }

    private void drawTopProtocols(){
        guiForm.setCategoryFlag("Protocols");

        listenerShowBarChart = evt -> {
            guiForm.setFrameBarTopProtocols(
                    guiForm.createChartFrame(
                            createJFreeChartBarProtocols(),
                            "Top 10 Protocols", 0, 205, 900, 300));
        };

        guiForm.getButtonShowBarChart().addActionListener(listenerShowBarChart);

        listenerShowPieChart = evt -> {
            guiForm.setFramePieTopProtocols(guiForm.createChartFrame(
                    createJFreeChartPieProtocols(),
                    "Top 10 Protocols", 0, 510, 900, 300 ));
        };

        guiForm.getButtonShowPieChart().addActionListener(listenerShowPieChart);

        listenerToJSON = evt -> {
            updateChartsTopProtocols();
            if (getMapTopProtocolsAnalyzer(guiForm.getDateFrom(), guiForm.getDateTo()).isEmpty()) {
                showInfoBox("It's nothing to save! The Top Rating List is Empty!\n", "Save error");
            } else {
                jsonSaver.setFileName("Top_10_Protocols_", guiForm.getDateFrom(), guiForm.getDateTo());
                jsonSaver.createJSONFile(getMapTopProtocolsAnalyzer(guiForm.getDateFrom(), guiForm.getDateTo()));
                showInfoBox("The file successfully saved to:\n" + jsonSaver.getFileName(), "Save result");
            }
        };

        guiForm.getButtonToJSON().addActionListener(listenerToJSON);
        listenerSliderFrom = createChangeListener();
        guiForm.getSliderFrom().addChangeListener(listenerSliderFrom);
        listenerSliderTo = createChangeListener();
        guiForm.getSliderFrom().addChangeListener(listenerSliderTo);
    }

    private void drawTopApps(){
        guiForm.setCategoryFlag("Apps");

        listenerShowBarChart = evt -> {
            guiForm.setFrameBarTopApps(
                    guiForm.createChartFrame(
                            createJFreeChartBarApps(),
                            "Top 10 Apps", 0, 205, 900, 300));
        };

        guiForm.getButtonShowBarChart().addActionListener(listenerShowBarChart);

        listenerShowPieChart = evt -> {
            guiForm.setFramePieTopApps(guiForm.createChartFrame(
                    createJFreeChartPieApps(),
                    "Top 10 Apps", 0, 510, 900, 300 ));
        };

        guiForm.getButtonShowPieChart().addActionListener(listenerShowPieChart);

        listenerToJSON = evt -> {
            updateChartsTopApps();
            if (getMapTopAppsAnalyzer(guiForm.getDateFrom(), guiForm.getDateTo()).isEmpty()) {
                showInfoBox("It's nothing to save! The Top Rating List is Empty!\n", "Save error");
            } else {
                jsonSaver.setFileName("Top_10_Apps_", guiForm.getDateFrom(), guiForm.getDateTo());
                jsonSaver.createJSONFile(getMapTopAppsAnalyzer(guiForm.getDateFrom(), guiForm.getDateTo()));
                showInfoBox("The file successfully saved to:\n" + jsonSaver.getFileName(), "Save result");
            }
        };

        guiForm.getButtonToJSON().addActionListener(listenerToJSON);
        listenerSliderFrom = createChangeListener();
        guiForm.getSliderFrom().addChangeListener(listenerSliderFrom);
        listenerSliderTo = createChangeListener();
        guiForm.getSliderFrom().addChangeListener(listenerSliderTo);
    }


    static void showInfoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public JFreeChart createJFreeChartBarRx() {
        return guiForm.createJFreeChartBar("Top 10 Received Packets",
                getMapTopRxAnalyzer(guiForm.getDateFrom(), guiForm.getDateTo()));
    }

    public JFreeChart createJFreeChartBarTx() {
        return guiForm.createJFreeChartBar("Top 10 Transmitted Packets", getMapTopTxAnalyzer(guiForm.getDateFrom(), guiForm.getDateTo()));
    }

    public JFreeChart createJFreeChartBarProtocols() {
        return guiForm.createJFreeChartBar("Top 10 Protocols", getMapTopProtocolsAnalyzer(guiForm.getDateFrom(), guiForm.getDateTo()));
    }

    public JFreeChart createJFreeChartBarApps() {
        return guiForm.createJFreeChartBar("Top 10 Applications", getMapTopAppsAnalyzer(guiForm.getDateFrom(), guiForm.getDateTo()));
    }

    public JFreeChart createJFreeChartPieRx() {
        return guiForm.createJFreeChartPie("Top 10 Received Packets",
                getMapTopRxAnalyzer(guiForm.getDateFrom(), guiForm.getDateTo()));
    }

    public JFreeChart createJFreeChartPieTx() {
        return guiForm.createJFreeChartPie("Top 10 Transmitted Packets", getMapTopTxAnalyzer(guiForm.getDateFrom(), guiForm.getDateTo()));
    }

    public JFreeChart createJFreeChartPieProtocols() {
        return guiForm.createJFreeChartPie("Top 3 Protocols",
                getMapTopProtocolsAnalyzer(guiForm.getDateFrom(), guiForm.getDateTo()));
    }

    public JFreeChart createJFreeChartPieApps() {
        return guiForm.createJFreeChartPie("Top 10 Applications",
                getMapTopAppsAnalyzer(guiForm.getDateFrom(), guiForm.getDateTo()));
    }

    public void updateChartsTopRx() {
        try {
            if (guiForm.getFrameBarTopRx().isShowing()) {      // to prevent showing the chart if it's closed during moving the slider
                guiForm.getFrameBarTopRx().getContentPane().removeAll();
                guiForm.setPanelChartBarTopRx(new ChartPanel(createJFreeChartBarRx()));
                guiForm.getFrameBarTopRx().add(guiForm.getPanelChartBarTopRx());
                guiForm.getFrameBarTopRx().setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("frameBarTopRx exception catched: " + e);
        }
        try {
            if (guiForm.getFramePieTopRx().isShowing()) {
                guiForm.getFramePieTopRx().getContentPane().removeAll();
                guiForm.setPanelChartPieTopRx(new ChartPanel(createJFreeChartPieRx()));
                guiForm.getFramePieTopRx().add(guiForm.getPanelChartPieTopRx(), BorderLayout.CENTER);
                guiForm.getFramePieTopRx().setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("framePieTopRx exception catched: " + e);
        }
    }

    public void updateChartsTopTx() {
        try {
            if (guiForm.getFrameBarTopTx().isShowing()) {      // to prevent showing the chart if it's closed during moving the slider
                guiForm.getFrameBarTopTx().getContentPane().removeAll();
                guiForm.setPanelChartBarTopTx(new ChartPanel(createJFreeChartBarTx()));
                guiForm.getFrameBarTopTx().add(guiForm.getPanelChartBarTopTx());
                guiForm.getFrameBarTopTx().setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("frameBarTopTx exception catched: " + e);
        }
        try {
            if (guiForm.getFramePieTopTx().isShowing()) {
                guiForm.getFramePieTopTx().getContentPane().removeAll();
                guiForm.setPanelChartPieTopTx(new ChartPanel(createJFreeChartPieTx()));
                guiForm.getFramePieTopTx().add(guiForm.getPanelChartPieTopTx(), BorderLayout.CENTER);
                guiForm.getFramePieTopTx().setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("framePieTopTx exception catched: " + e);
        }
    }

    public void updateChartsTopProtocols() {
        try {
            if (guiForm.getFrameBarTopProtocols().isShowing()) {      // to prevent showing the chart if it's closed during moving the slider
                guiForm.getFrameBarTopProtocols().getContentPane().removeAll();
                guiForm.setPanelChartBarTopProtocols(new ChartPanel(createJFreeChartBarProtocols()));
                guiForm.getFrameBarTopProtocols().add(guiForm.getPanelChartBarTopProtocols());
                guiForm.getFrameBarTopProtocols().setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("frameBarTopProtocols exception catched: " + e);
        }
        try {
            if (guiForm.getFramePieTopProtocols().isShowing()) {
                guiForm.getFramePieTopProtocols().getContentPane().removeAll();
                guiForm.setPanelChartPieTopProtocols(new ChartPanel(createJFreeChartPieProtocols()));
                guiForm.getFramePieTopProtocols().add(guiForm.getPanelChartPieTopProtocols(), BorderLayout.CENTER);
                guiForm.getFramePieTopProtocols().setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("framePieTopProtocols exception catched: " + e);
        }
    }

    public void updateChartsTopApps() {
        try {
            if (guiForm.getFrameBarTopApps().isShowing()) {      // to prevent showing the chart if it's closed during moving the slider
                guiForm.getFrameBarTopApps().getContentPane().removeAll();
                guiForm.setPanelChartBarTopApps(new ChartPanel(createJFreeChartBarApps()));
                guiForm.getFrameBarTopApps().add(guiForm.getPanelChartBarTopApps());
                guiForm.getFrameBarTopApps().setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("frameBarTopApps exception catched: " + e);
        }
        try {
            if (guiForm.getFramePieTopApps().isShowing()) {
                guiForm.getFramePieTopApps().getContentPane().removeAll();
                guiForm.setPanelChartPieTopApps(new ChartPanel(createJFreeChartPieApps()));
                guiForm.getFramePieTopApps().add(guiForm.getPanelChartPieTopApps(), BorderLayout.CENTER);
                guiForm.getFramePieTopApps().setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("framePieTopApps exception catched: " + e);
        }
    }

    public ChangeListener createChangeListener() {
        return evt -> {
            JSlider source = (JSlider)evt.getSource();
            guiForm.setDateFrom(getDateOfSlider(guiForm.getSliderFrom().getValue()));
            guiForm.setDateTo(getDateOfSlider(guiForm.getSliderTo().getValue()));
            guiForm.setLabelsDate();
            if (!source.getValueIsAdjusting()) {
                guiForm.setDateFrom(getDateOfSlider(guiForm.getSliderFrom().getValue()));
                guiForm.setDateTo(getDateOfSlider(guiForm.getSliderTo().getValue()));               
                guiForm.setLabelsDate();
                reparseRecordListDateRange(guiForm.getDateFrom(), guiForm.getDateTo());
                guiForm.checkSlidersPropriety();
            }
        };
    }
    
        public Date getDateOfSlider(int sliderValue){
            Calendar cal = Calendar.getInstance();
            cal.setTime(guiForm.getDateFirst());
            cal.add(Calendar.SECOND, getDiffSec()*sliderValue/1000 );
            return cal.getTime();
        }



}
