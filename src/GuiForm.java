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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

public class GuiForm implements IGuiForm {

    private JPanel rootPanel;
    private JButton buttonShowBarChart;
    private JButton buttonToJSON;
    private JButton buttonShowPieChart;
    private JPanel panelButton;
    private JPanel panelCenter;
    private Date [] dateArrayForSliderLabels;
    private Date dateFirst;
    private Date dateLast;
    private Date dateFrom;
    private Date dateTo;

    JFreeChart chartBar;
    JFreeChart chartPie;

    private JSlider sliderFrom;
    private JSlider sliderTo;
    private ChartFrame frameBar;
    private ChartFrame framePie;
    DefaultPieDataset datasetPie;
    ChartPanel panelChartPie;
    ChartPanel panelChartBar;

    private JPanel panelSlider;
    private JLabel labelFrom;
    private JLabel labelTo;
    private JLabel labelFrom2;
    private JLabel labelTo2;

    public ITextAnalyzer analyzer = new TextAnalyzer();
    private IJSONSaver jsonSaver = new JSONSaver();

    public void startDraw(ITextAnalyzer analyzer) throws ParseException {

        this.analyzer = analyzer;
        this.dateFirst = analyzer.getDateFirst();
        this.dateLast = analyzer.getDateLast();
        dateFrom = analyzer.getDateFirst();
        dateTo = analyzer.getDateLast();
        dateArrayForSliderLabels = analyzer.getDateArrayForSliderLabels();
        analyzer.setDateArrayForSliderLabels();
        setLabelsDate();
        drawChart();

//        drawChart2();
    }

    public void drawChart() throws ParseException {

        JFrame jFrame = new JFrame("Top Receivers Chart");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(rootPanel);
        jFrame.setBounds(0, 0, 900, 200);
        setSliderFrom();
        setSliderTo();
        jFrame.setVisible(true);
        buttonShowBarChart.addActionListener(new ActionListener() {
                                                 @Override
                                                 public void actionPerformed(ActionEvent evt) {

                                                     try {
                                                         frameBar.setVisible(false);
                                                     } catch (Exception e) {
                                                         System.out.println("framePie is not set " + e);
                                                     }
//
                                                     setDataSetBar();

                                                     frameBar = new ChartFrame("Top 10 Receivers", null);
                                                     frameBar.setLayout(new GridLayout());

                                                     frameBar.setBounds(0, 205, 900, 300);
                                                     frameBar.setDefaultCloseOperation(ChartFrame.HIDE_ON_CLOSE);
                                                     panelChartBar = new ChartPanel(chartBar);
                                                     panelChartBar.setLayout(new GridLayout());

                                                     frameBar.add(panelChartBar).setVisible(true);
                                                     frameBar.setVisible(true);
                                                 }
                                             }
        );


        buttonShowPieChart.addActionListener(new ActionListener() {
                                                 @Override
                                                 public void actionPerformed(ActionEvent evt) {

                                                     try {
                                                         framePie.setVisible(false);

                                                     } catch (Exception e) {
                                                         System.out.println("framePie is not set: " + e);
                                                     }

                                                     setDataSetPie();

                                                     framePie = new ChartFrame("Top 10 Receivers", null);
                                                     framePie.setLayout(new GridLayout());


                                                     framePie.setBounds(0, 510, 900, 500);
                                                     framePie.setDefaultCloseOperation(ChartFrame.HIDE_ON_CLOSE);


                                                     panelChartPie = new ChartPanel(chartPie);
                                                     panelChartPie.setLayout(new GridLayout());

                                                     framePie.add(panelChartPie);
                                                     framePie.setVisible(true);
                                                 }
                                             }
        );

        buttonToJSON.addActionListener(new ActionListener() {
                                                 @Override
                                                 public void actionPerformed(ActionEvent evt) {
//                                                     dateFrom = analyzer.getDateOfSlider(sliderFrom.getValue());
//                                                     dateTo = analyzer.getDateOfSlider(sliderTo.getValue());
//
                                                     setTryCatch();
//                                                     setLabelsDate();


                                                     if (analyzer.topReceiversPairs.isEmpty()){
                                                         infoBox("It's nothing to save! The Top Rating List is Empty!\n" , "Save error" );
                                                     }                                                     else {
                                                         jsonSaver.setFileName("Top_10_Receivers_", dateFrom, dateTo);
                                                         jsonSaver.createJSONFile(analyzer);
                                                         infoBox("The file successfully saved to:\n" + jsonSaver.getFileName(), "Save result" );
                                                     }

                                                 }
                                             }
        );

        sliderFrom.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                dateFrom = analyzer.getDateOfSlider(sliderFrom.getValue());
                dateTo = analyzer.getDateOfSlider(sliderTo.getValue());
                setLabelsDate();
                setTryCatch();
                checkDatePropriety();

            }
        });

        sliderTo.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                dateFrom = analyzer.getDateOfSlider(sliderFrom.getValue());
                dateTo = analyzer.getDateOfSlider(sliderTo.getValue());
                setLabelsDate();
                setTryCatch();
                checkDatePropriety();
            }
        });
    }

    public void drawChart2() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < 10; i++) {
            dataset.setValue(analyzer.topTransmittersPairs.get(i).value, "Y", analyzer.topTransmittersPairs.get(i).key);
        }

        JFreeChart chart = ChartFactory.createBarChart3D("Top 10 Transmitted Packets", "", "Packets", dataset,
                PlotOrientation.HORIZONTAL, false, true, false);
        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setPaint(Color.red);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLUE);
        ChartFrame frame2 = new ChartFrame("Top 10 Transmitters", chart);
        frame2.setVisible(true);
        frame2.setSize(900, 500);
    }

    private void setSliderFrom(){
        this.sliderFrom = new JSlider(JSlider.HORIZONTAL, 0, 1000 , 0);
        panelCenter.add(sliderFrom);
        Hashtable<Integer, Component> labelTable = new Hashtable();

        int i = -200;
        for (Date date : dateArrayForSliderLabels) {
            labelTable.put(i += 200 , new JLabel(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(date)));
        }
        sliderFrom.setLabelTable(labelTable);
        sliderFrom.setMinorTickSpacing(100);
        sliderFrom.setMajorTickSpacing(200);
        sliderFrom.setPaintTicks(true);
        sliderFrom.setPaintLabels(true);

    }

    private void setSliderTo(){
        this.sliderTo = new JSlider(JSlider.HORIZONTAL, 0, 1000 , 1000);
        panelSlider.add(sliderTo);
        Hashtable<Integer, Component> labelTable = new Hashtable();

        int i = -200;
        for (Date date : dateArrayForSliderLabels) {
            labelTable.put(i += 200 , new JLabel(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(date)));
        }

        sliderTo.setLabelTable(labelTable);

        sliderTo.setMinorTickSpacing(100);
        sliderTo.setMajorTickSpacing(200);
        sliderTo.setPaintTicks(true);
        sliderTo.setPaintLabels(true);
    }

    private void setDataSetPie() {
        datasetPie = new DefaultPieDataset();

        for (int i = 0; (i < 10) && i < (analyzer.topReceiversPairs.size()) ; i++) {
            datasetPie.setValue(analyzer.topReceiversPairs.get(i).key, new Integer(analyzer.topReceiversPairs.get(i).value));
        }
        chartPie = ChartFactory.createPieChart3D("Top 10 Received Packets", datasetPie, true, true, true);
        chartPie.setBackgroundPaint(Color.white);
        chartPie.getTitle().setPaint(Color.blue);
        PiePlot3D p = (PiePlot3D) chartPie.getPlot();
    }

    private void setDataSetBar() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; (i < 10) && i < (analyzer.topReceiversPairs.size()) ; i++) {
            dataset.setValue(analyzer.topReceiversPairs.get(i).value, "", analyzer.topReceiversPairs.get(i).key);
        }

        chartBar = ChartFactory.createBarChart3D("Top 10 Received Packets", "", "Packets", dataset,
                PlotOrientation.HORIZONTAL, false, true, false);
        chartBar.setBackgroundPaint(Color.white);
        chartBar.getTitle().setPaint(Color.blue);
        CategoryPlot p = chartBar.getCategoryPlot();
        p.setRangeGridlinePaint(Color.GREEN);
        p.setDomainCrosshairPaint(Color.BLUE);
        p.setRangeCrosshairPaint(Color.ORANGE);
        p.setOutlinePaint(Color.BLUE);
    }

    private void setTryCatch() {
        try {
            analyzer.reparseDataFlowStructureListWithDateRange(dateFrom, dateTo);
            analyzer.getTopReceivers();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            if (frameBar.isShowing()) {

                System.out.println("Bar is showing");

                setDataSetBar();

                frameBar.remove(panelChartBar);

                panelChartBar = new ChartPanel(chartBar);
                panelChartBar.setLayout(new GridLayout());
                frameBar.add(panelChartBar);
                frameBar.setVisible(true);



            } else {
                System.out.println("Bar is not showed");
            }
        } catch (Exception e) {
            System.out.println("frameBar exception catched: " + e);
        }

        try {
            if (framePie.isShowing()) {

                System.out.println("Pie is showing");
                setDataSetPie();

                framePie.remove(panelChartPie);

                panelChartPie = new ChartPanel(chartPie);
                panelChartPie.setLayout(new BorderLayout());
                framePie.add(panelChartPie, BorderLayout.CENTER);
                framePie.setVisible(true);

            } else {
                System.out.println("Pie is not showed");
            }
        } catch (Exception e) {
            System.out.println("framePie exception catched: " + e);
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

    private void checkDatePropriety(){
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



