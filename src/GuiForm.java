import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.resources.JFreeChartResources;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

public class GuiForm implements IGuiForm {

    private JPanel rootPanel;
    private JButton buttonShowBarChart;
    private JButton buttonSetTimeRange;
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

    public ITextAnalyzer analyzer = new TextAnalyzer();

    public void startDraw(ITextAnalyzer analyzer) throws ParseException {

        this.analyzer = analyzer;
        this.dateFirst = analyzer.getDateFirst();

        this.dateLast = analyzer.getDateLast();
        dateFrom = analyzer.getDateFirst();

        dateTo = analyzer.getDateLast();
        dateArrayForSliderLabels = analyzer.getDateArrayForSliderLabels();

        labelFrom.setText(String.format("Date from: " + (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateFirst))));
        labelTo.setText("Date to: " + (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateLast)));
        drawChart();

//        drawChart2();

    }

    public void drawChart() throws ParseException {

        JFrame jFrame = new JFrame("Top Receivers Chart");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(rootPanel);
        jFrame.setBounds(0, 0, 900, 300);

        setSliderFrom();
        setSliderTo();
        panelCenter.setVisible(true);

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



                                                     frameBar.setBounds(1000, 0, 900, 500);
                                                     frameBar.setDefaultCloseOperation(ChartFrame.HIDE_ON_CLOSE);

                                                     panelChartBar = new ChartPanel(chartBar);
                                                     panelChartBar.setLayout(new GridLayout());

                                                     frameBar.add(panelChartBar);
                                                     frameBar.setVisible(true);



//                                          ChartPanel panelChart1 = new ChartPanel(chart);
//                                          panelCenter.removeAll();
//                                          panelCenter.add(panelChart1, BorderLayout.CENTER);
//                                          panelCenter.validate();
//                                          panelCenter.setVisible(true);
//                                          panelSlider.setVisible(false);
//                                          jFrame.setBounds(0, 0, 1100, 500);
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


                                                     framePie.setBounds(0, 300, 900, 500);
                                                     framePie.setDefaultCloseOperation(ChartFrame.HIDE_ON_CLOSE);


                                                     panelChartPie = new ChartPanel(chartPie);
                                                     panelChartPie.setLayout(new GridLayout());

                                                     framePie.add(panelChartPie);
                                                     framePie.setVisible(true);
                                                 }
                                             }
        );

        buttonSetTimeRange.addActionListener(new ActionListener() {
                                                 @Override
                                                 public void actionPerformed(ActionEvent evt) {
                                                     dateFrom = analyzer.getDateOfSlider(sliderFrom.getValue());
                                                     dateTo = analyzer.getDateOfSlider(sliderTo.getValue());

                                                     setTryCatch();
                                                     labelFrom.setText(String.format("Date from: " + (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateFrom))));
                                                     labelTo.setText("Date to: " + (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateTo)));
                                                 }
                                             }
        );

        sliderFrom.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                dateFrom = analyzer.getDateOfSlider(sliderFrom.getValue());
                dateTo = analyzer.getDateOfSlider(sliderTo.getValue());
                setTryCatch();

                labelFrom.setText(String.format("Date from: " + (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateFrom))));
                labelTo.setText("Date to: " + (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateTo)));


            }







        });

        sliderTo.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                dateFrom = analyzer.getDateOfSlider(sliderFrom.getValue());
                dateTo = analyzer.getDateOfSlider(sliderTo.getValue());
                setTryCatch();

                labelFrom.setText(String.format("Date from: " + (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateFrom))));
                labelTo.setText("Date to: " + (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateTo)));
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

        for (int i = 0; i < 10; i++) {
            datasetPie.setValue(analyzer.topReceiversPairs.get(i).key, new Integer(analyzer.topReceiversPairs.get(i).value));
        }
        chartPie = ChartFactory.createPieChart3D("Top 10 Received Packets", datasetPie, true, true, true);
        chartPie.setBackgroundPaint(Color.white);
        chartPie.getTitle().setPaint(Color.blue);
        PiePlot3D p = (PiePlot3D) chartPie.getPlot();



    }

    private void setDataSetBar() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < 10; i++) {
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
            analyzer.reparseDataFlowStructureList(dateFrom, dateTo);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            if (frameBar.isShowing()) {

                System.out.println("Bar is showing");

                setDataSetBar();

                panelChartBar.setVisible(false);
                panelChartBar.removeAll();
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

                panelChartPie.setVisible(false);
                panelChartPie.removeAll();
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



}



//////////////////////////////////            DATES          //////////////////////////////////////////////////////////////


//        Date date = dateFirst;
//        System.out.println();
//        System.out.println("reference date: " + date);
//
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        cal.add(Calendar.HOUR, 36);
//        System.out.println("added one and half days to reference date: "+cal.getTime());
//
//        String newDateString = "2012-02-03 06:30:00.0";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSSSS");
//        Date newDate = sdf.parse(newDateString);
//        System.out.println("new date to compare with reference date : "+newDate);
//
//        Calendar newCal = Calendar.getInstance();
//        newCal.setTime(newDate);
//
//
//        if(cal.after(newCal)){
//            System.out.println("date is greater than reference that.");
//        }else if(cal.before(newCal)){
//            System.out.println("date is lesser than reference that.");
//        }else{
//            System.out.println("date is equal to reference that.");
//        }

///////////////////////////////////////////////////////////////////////////////////////////////



