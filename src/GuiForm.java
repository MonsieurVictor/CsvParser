import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class GuiForm implements IGuiForm {

    JFrame jFrame = new JFrame("Main menu");
    public JPanel rootPanelChart;

    private JButton buttonBack;
    private JButton buttonShowBarChart;
    private JButton buttonShowPieChart;
    private JButton buttonToJSON;

    public JPanel rootPanelLauncher;

    private JButton buttonTopRx;
    private JButton buttonTopTx;
    private JButton buttonTopProtocols;
    private JButton buttonTopApps;

    private JPanel panelButton;
    private JPanel panelCenter;

    private Date[] dateArrayForSliderLabels;
    private Date dateFirst;
    private Date dateLast;
    private Date dateFrom;
    private Date dateTo;

    JFreeChart chartBarTopRx;
    JFreeChart chartPieTopRx;
    JFreeChart chartBarTopTx;
    JFreeChart chartPieTopTx;

    private JSlider sliderFrom;
    private JSlider sliderTo;

    private ChartFrame frameBarTopRx;
    private ChartFrame framePieTopRx;
    private ChartFrame frameBarTopTx;
    private ChartFrame framePieTopTx;

    ChartPanel panelChartBarTopRx;
    ChartPanel panelChartPieTopRx;
    ChartPanel panelChartBarTopTx;
    ChartPanel panelChartPieTopTx;

    private JPanel panelSlider;
    private JLabel labelFrom;
    private JLabel labelTo;
    private JLabel labelFrom2;
    private JLabel labelTo2;

    public ITextAnalyzer analyzer = new TextAnalyzer();
    private IJSONSaver jsonSaver = new JSONSaver();

    ActionListener listenerShowBarChart;
    ActionListener listenerShowPieChart;
    ActionListener listenerToJSON;
    ChangeListener listenerSliderFrom;
    ChangeListener listenerSliderTo;

    public void startDraw(ITextAnalyzer analyzer) throws ParseException {

        this.analyzer = analyzer;
        this.dateFirst = analyzer.getDateFirst();
        this.dateLast = analyzer.getDateLast();
        dateFrom = analyzer.getDateFirst();
        dateTo = analyzer.getDateLast();
        dateArrayForSliderLabels = analyzer.getDateArrayForSliderLabels();
        analyzer.setDateArrayForSliderLabels();
        setLabelsDate();
        setSliderFrom();
        setSliderTo();

        buttonTopRx = new JButton("Top Receivers");
        buttonTopTx = new JButton("Top Transmitters");
        buttonTopProtocols = new JButton("Top Protocols");
        buttonTopApps = new JButton("Top Apps");


        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rootPanelLauncher = new JPanel();

        rootPanelLauncher.add(buttonTopRx);
        rootPanelLauncher.add(buttonTopTx);
        rootPanelLauncher.add(buttonTopProtocols);
        rootPanelLauncher.add(buttonTopApps);

        buttonTopRx.addActionListener(e -> {

            setJframeMainMenu("Top 10 Rx");
            drawMenuRx();
        });

        buttonTopTx.addActionListener(e -> {

            setJframeMainMenu("Top 10 Tx");
            drawMenuTx();
        });

        buttonBack.addActionListener(e -> {

            buttonShowBarChart.removeActionListener(listenerShowBarChart);
            buttonShowPieChart.removeActionListener(listenerShowPieChart);
            buttonToJSON.removeActionListener(listenerToJSON);

            sliderFrom.removeChangeListener(listenerSliderFrom);
            sliderTo.removeChangeListener(listenerSliderTo);

            jFrame.setTitle("Main menu");
            jFrame.setContentPane(rootPanelLauncher);
            jFrame.setBounds(0, 0, 300, 200);
            jFrame.setVisible(true);
        });

        jFrame.setContentPane(rootPanelLauncher);
        jFrame.setBounds(0, 0, 300, 200);
        jFrame.setVisible(true);

    }

    public void drawMenuRx() {

        listenerShowBarChart = evt -> {

            panelChartBarTopRx = new ChartPanel(setDatasetBarTopRx());
            panelChartBarTopRx.setLayout(new GridLayout());

            frameBarTopRx = new ChartFrame("Top 10 Rx", null);
            frameBarTopRx.setLayout(new GridLayout());
            frameBarTopRx.setBounds(0, 205, 900, 300);
            frameBarTopRx.setDefaultCloseOperation(ChartFrame.HIDE_ON_CLOSE);
            frameBarTopRx.setVisible(true);
            frameBarTopRx.add(panelChartBarTopRx);
        };

        buttonShowBarChart.addActionListener(listenerShowBarChart);

        listenerShowPieChart = evt -> {

            panelChartPieTopRx = new ChartPanel(setDatasetPieTopRx());
            panelChartPieTopRx.setLayout(new GridLayout());

            try {
                framePieTopRx.setVisible(false);

            } catch (Exception e) {
                System.out.println("framePieTopRx is not set: " + e);
            }

            framePieTopRx = new ChartFrame("Top 10 Rx", null);
            framePieTopRx.setLayout(new GridLayout());
            framePieTopRx.setBounds(0, 510, 900, 500);
            framePieTopRx.setDefaultCloseOperation(ChartFrame.HIDE_ON_CLOSE);
            framePieTopRx.add(panelChartPieTopRx);
            framePieTopRx.setVisible(true);
        };

        buttonShowPieChart.addActionListener(listenerShowPieChart);

        listenerToJSON = evt -> {

            updateChartsTopRx();

            if (analyzer.topReceiversPairs.isEmpty()) {
                infoBox("It's nothing to save! The Top Rating List is Empty!\n", "Save error");
            } else {
                jsonSaver.setFileName("Top_10_Rx_", dateFrom, dateTo);
                jsonSaver.createJSONFile(analyzer);
                infoBox("The file successfully saved to:\n" + jsonSaver.getFileName(), "Save result");
            }
        };

        buttonToJSON.addActionListener(listenerToJSON);
        listenerSliderFrom = changeListenerFactoryRx();
        sliderFrom.addChangeListener(listenerSliderFrom);
        listenerSliderTo = changeListenerFactoryRx();
        sliderTo.addChangeListener(listenerSliderTo);
    }

    public ChangeListener changeListenerFactoryRx(){
        return evt -> {
            dateFrom = analyzer.getDateOfSlider(sliderFrom.getValue());
            dateTo = analyzer.getDateOfSlider(sliderTo.getValue());
            setLabelsDate();
            updateChartsTopRx();
            checkSlidersPropriety();
        };
    }

    public ChangeListener changeListenerFactoryTx(){
        return evt -> {
            dateFrom = analyzer.getDateOfSlider(sliderFrom.getValue());
            dateTo = analyzer.getDateOfSlider(sliderTo.getValue());
            setLabelsDate();
            updateChartsTopTx();
            checkSlidersPropriety();
        };
    }

    public void drawMenuTx(){

        listenerShowBarChart = evt -> {

            panelChartBarTopTx = new ChartPanel(setDatasetBarTopTx());
            panelChartBarTopTx.setLayout(new GridLayout());

            frameBarTopTx = new ChartFrame("Top 10 Tx", null);
            frameBarTopTx.setLayout(new GridLayout());
            frameBarTopTx.setBounds(910, 205, 900, 300);
            frameBarTopTx.setDefaultCloseOperation(ChartFrame.HIDE_ON_CLOSE);
            frameBarTopTx.setVisible(true);
            frameBarTopTx.add(panelChartBarTopTx);

        };

        buttonShowBarChart.addActionListener(listenerShowBarChart);

        listenerShowPieChart = evt -> {



            panelChartPieTopTx = new ChartPanel(setDatasetPieTopTx());
            panelChartPieTopTx.setLayout(new GridLayout());

            try {
                framePieTopTx.setVisible(false);

            } catch (Exception e) {
                System.out.println("framePieTopTx is not set: " + e);
            }

            framePieTopTx = new ChartFrame("Top 10 Transmitters", null);
            framePieTopTx.setLayout(new GridLayout());

            framePieTopTx.setBounds(910, 510, 900, 500);
            framePieTopTx.setDefaultCloseOperation(ChartFrame.HIDE_ON_CLOSE);

            framePieTopTx.add(panelChartPieTopTx);
            framePieTopTx.setVisible(true);
        };


        buttonShowPieChart.addActionListener(listenerShowPieChart);

        listenerToJSON = evt -> {

            updateChartsTopTx();

            if (analyzer.topTransmittersPairs.isEmpty()) {
                infoBox("It's nothing to save! The Top Rating List is Empty!\n", "Save error");
            } else {
                jsonSaver.setFileName("Top_10_Transmitters_", dateFrom, dateTo);
                jsonSaver.createJSONFile(analyzer);
                infoBox("The file successfully saved to:\n" + jsonSaver.getFileName(), "Save result");
            }

        };
        buttonToJSON.addActionListener (listenerToJSON) ;

        listenerSliderFrom = changeListenerFactoryTx();
        sliderFrom.addChangeListener(listenerSliderFrom);
        listenerSliderTo = changeListenerFactoryTx();
        sliderTo.addChangeListener(listenerSliderTo);
    }

    private void setSliderFrom () {
        panelCenter.add(sliderFrom = sliderFactory(0));
    }

    private void setSliderTo () {
        panelSlider.add(sliderTo = sliderFactory(1000));
    }

    private JSlider sliderFactory(int initialValue) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 1000, initialValue);

        Hashtable<Integer, Component> labelTable = new Hashtable();

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




//////////////////////////////////////////////////////////////////////////////////////////
    private JFreeChart setDatasetBarTopRx() {

        return chartBarFactory("Top 10 Received Packets", analyzer.topReceiversPairs);
    }

    private JFreeChart setDatasetBarTopTx() {

        return chartBarFactory("Top 10 Transmitted Packets", analyzer.topTransmittersPairs);
    }

    private JFreeChart setDatasetPieTopRx() {

        return chartPieFactory("Top 10 Received Packets", analyzer.topReceiversPairs);
    }


    private JFreeChart setDatasetPieTopTx() {

        return chartPieFactory("Top 10 Transmitted Packets", analyzer.topTransmittersPairs);
    }

    public JFreeChart chartBarFactory (String categoryName, List<TextAnalyzer.TopRatedPair> pair) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; (i < 10) && i < (pair.size()) ; i++) {
            dataset.setValue(pair.get(i).value, "", pair.get(i).key);
        }

        JFreeChart chartBar = ChartFactory.createBarChart3D(categoryName, "", "Packets", dataset,
                PlotOrientation.HORIZONTAL, false, true, false);
        chartBar.setBackgroundPaint(Color.white);
        chartBar.getTitle().setPaint(Color.blue);
        CategoryPlot p = chartBar.getCategoryPlot();
        p.setRangeGridlinePaint(Color.GREEN);
        p.setDomainCrosshairPaint(Color.BLUE);
        p.setRangeCrosshairPaint(Color.ORANGE);
        p.setOutlinePaint(Color.BLUE);
        return chartBar;
    }

    public JFreeChart chartPieFactory (String categoryName, List<TextAnalyzer.TopRatedPair> pair) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (int i = 0; (i < 10) && i < (pair.size()) ; i++) {
            dataset.setValue(pair.get(i).key, new Integer(pair.get(i).value));
        }
        JFreeChart chartPie = ChartFactory.createPieChart3D(categoryName + " from: "
                + new SimpleDateFormat("yyyy-MM-dd hh:mm").format(dateFrom)
                + " to: "
                + new SimpleDateFormat("yyyy-MM-dd hh:mm").format(dateTo) , dataset, true, true, true);
        chartPie.setBackgroundPaint(Color.white);
        chartPie.getTitle().setPaint(Color.blue);
        PiePlot3D p = (PiePlot3D) chartPie.getPlot();
        return chartPie;
    }




/////////////////////////////////////////////////////////////////////////////////////////////
    private void updateChartsTopRx() {
        try {
            analyzer.reparseDataFlowStructureListWithDateRange(dateFrom, dateTo);
            analyzer.getTopReceivers();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            frameBarTopRx.remove(panelChartBarTopRx);
            panelChartBarTopRx = new ChartPanel(setDatasetBarTopRx());
            panelChartBarTopRx.setLayout(new GridLayout());
            frameBarTopRx.add(panelChartBarTopRx);
            frameBarTopRx.setVisible(true);

        } catch (Exception e) {
            System.out.println("frameBarTopRx exception catched: " + e);
        }

        try {

            framePieTopRx.remove(panelChartPieTopRx);
            panelChartPieTopRx = new ChartPanel(setDatasetPieTopRx());
            panelChartPieTopRx.setLayout(new BorderLayout());
            framePieTopRx.add(panelChartPieTopRx, BorderLayout.CENTER);
            framePieTopRx.setVisible(true);

        } catch (Exception e) {
            System.out.println("framePieTopRx exception catched: " + e);
        }
    }

    private void updateChartsTopTx() {
        try {
            analyzer.reparseDataFlowStructureListWithDateRange(dateFrom, dateTo);
            analyzer.getTopTransmitters();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            if (frameBarTopTx.isShowing()) {

                System.out.println("Bar Top10Tx is showing");

                frameBarTopTx.remove(panelChartBarTopTx);
                panelChartBarTopTx = new ChartPanel(setDatasetBarTopTx());
                panelChartBarTopTx.setLayout(new GridLayout());
                frameBarTopTx.add(panelChartBarTopTx);
                frameBarTopTx.setVisible(true);

            } else {
                System.out.println("Bar Top10Tx is not showed");
            }
        } catch (Exception e) {
            System.out.println("frameBarTopTx exception catched: " + e);
        }

        try {
            if (framePieTopTx.isShowing()) {

                System.out.println("Pie Top10Tx is showing");

                framePieTopTx.remove(panelChartPieTopTx);
                panelChartPieTopTx = new ChartPanel(setDatasetPieTopTx());
                panelChartPieTopTx.setLayout(new BorderLayout());
                framePieTopTx.add(panelChartPieTopTx, BorderLayout.CENTER);
                framePieTopTx.setVisible(true);

            } else {
                System.out.println("Pie Top10Tx is not showed");
            }
        } catch (Exception e) {
            System.out.println("framePieTopTx exception catched: " + e);
        }
    }

    private void setLabelsDate() {

        labelFrom.setText(String.format("Date from: " + (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateFrom))));
        labelTo.setText("Date to: " + (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateTo)));

    }

    public static void infoBox(String infoMessage, String titleBar)
    {
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
}



