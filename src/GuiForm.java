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
    private JButton buttonSetTimeRange;
    private JButton buttonShowPieChart;
    private JPanel panelButton;
    private JPanel panelCenter;
    private Date [] dateArrayForSliderLabels;
    private Date dateFirst;
    private Date dateLast;
    private Date dateFrom;
    private Date dateTo;

    private JSlider sliderFrom;
    private JSlider sliderTo;
    private ChartFrame frame1;


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
        jFrame.setContentPane(rootPanel);
        jFrame.setBounds(0, 0, 900, 300);

        setSliderFrom();
        setSliderTo();
        panelCenter.setVisible(true);

        jFrame.setVisible(true);

        buttonShowBarChart.addActionListener(new ActionListener() {
                                                 @Override
                                                 public void actionPerformed(ActionEvent e) {

                                                     try {
//                                              frame1.removeAll();
                                                         frame1.setVisible(false);
                                                     } catch (Exception x) {
                                                         System.out.println(x);
                                                     }
//
                                                     DefaultCategoryDataset dataset = new DefaultCategoryDataset();

                                                     for (int i = 0; i < 10; i++) {
                                                         dataset.setValue(analyzer.topReceiversPairs.get(i).value, "", analyzer.topReceiversPairs.get(i).key);
                                                     }

                                                     JFreeChart chart = ChartFactory.createBarChart3D("Top 10 Received Packets", "", "Packets", dataset,
                                                             PlotOrientation.HORIZONTAL, false, true, false);
                                                     chart.setBackgroundPaint(Color.white);
                                                     chart.getTitle().setPaint(Color.blue);
                                                     CategoryPlot p = chart.getCategoryPlot();
                                                     p.setRangeGridlinePaint(Color.GREEN);
                                                     p.setDomainCrosshairPaint(Color.BLUE);
                                                     p.setRangeCrosshairPaint(Color.ORANGE);
                                                     p.setOutlinePaint(Color.BLUE);


                                                     frame1 = new ChartFrame("Top 10 Receivers", chart);
                                                     frame1.setVisible(true);
                                                     frame1.setBounds(0, 300, 900, 500);
                                                     System.out.println(sliderFrom.getValue());
                                                     System.out.println(sliderTo.getValue());


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
                                                 public void actionPerformed(ActionEvent e) {

                                                     try {
//                                              frame1.removeAll();
                                                         frame1.setVisible(false);
                                                     } catch (Exception x) {
                                                         System.out.println(x);
                                                     }

//                                          panelCenter.setVisible(false);

                                                     DefaultPieDataset datasetPie = new DefaultPieDataset();

                                                     for (int i = 0; i < 10; i++) {
                                                         datasetPie.setValue(analyzer.topReceiversPairs.get(i).key, new Integer(analyzer.topReceiversPairs.get(i).value));
                                                     }

                                                     JFreeChart chartPie = ChartFactory.createPieChart3D("Top 10 Received Packets", datasetPie, true, true, true);
                                                     chartPie.setBackgroundPaint(Color.white);
                                                     chartPie.getTitle().setPaint(Color.blue);
                                                     PiePlot3D p = (PiePlot3D) chartPie.getPlot();
                                                     ChartPanel panelChart1Pie = new ChartPanel(chartPie);

                                                     frame1 = new ChartFrame("Top 10 Receivers", chartPie);
                                                     frame1.setVisible(true);
                                                     frame1.setBounds(0, 300, 900, 500);


                                                 }
                                             }
        );

        buttonSetTimeRange.addActionListener(new ActionListener() {
                                                 @Override
                                                 public void actionPerformed(ActionEvent e) {
                                                     dateFrom = analyzer.getDateOfSlider(sliderFrom.getValue());
                                                     dateTo = analyzer.getDateOfSlider(sliderTo.getValue());

                                                     System.out.println(sliderFrom.getValue());
                                                     System.out.println(sliderTo.getValue());

                                                     labelFrom.setText(String.format("Date from: " + (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateFrom))));
                                                     labelTo.setText("Date to: " + (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateTo)));



                                                 }
                                             }
        );


        sliderFrom.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                dateFrom = analyzer.getDateOfSlider(sliderFrom.getValue());

                System.out.println(sliderFrom.getValue());

                labelFrom.setText(String.format("Date from: " + (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateFrom))));
            }


        });

        sliderTo.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                dateTo = analyzer.getDateOfSlider(sliderTo.getValue());

                System.out.println(sliderTo.getValue());

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
        this.sliderFrom = new JSlider(JSlider.HORIZONTAL, 0, 1000 , 20);
        panelCenter.add(sliderFrom);
        Hashtable<Integer, Component> labelTable = new Hashtable();

        int i = -200;
        for (Date date : dateArrayForSliderLabels) {
            labelTable.put(i += 200 , new JLabel(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(date)));
        }

        sliderFrom.setLabelTable(labelTable);
        sliderFrom.setMinorTickSpacing(100);
        sliderFrom.setMajorTickSpacing(200);
        sliderFrom.setValue(0);
        sliderFrom.setPaintTicks(true);
        sliderFrom.setPaintLabels(true);
    }

    private void setSliderTo(){
        this.sliderTo = new JSlider(JSlider.HORIZONTAL, 0, 1000 , 20);
        panelSlider.add(sliderTo);
        Hashtable<Integer, Component> labelTable = new Hashtable();

        int i = -200;
        for (Date date : dateArrayForSliderLabels) {
            labelTable.put(i += 200 , new JLabel(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(date)));
        }

        sliderTo.setLabelTable(labelTable);
        sliderTo.setMinorTickSpacing(100);
        sliderTo.setMajorTickSpacing(200);
        sliderTo.setValue(1000);
        sliderTo.setPaintTicks(true);
        sliderTo.setPaintLabels(true);
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



