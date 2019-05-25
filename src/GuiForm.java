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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

public class GuiForm implements IGuiForm {

    private JPanel rootPanel;
    private JButton buttonShowBarChart;
    private JButton buttonReset;
    private JButton buttonShowPieChart;
    private JPanel panelButton;
    private JPanel panelCenter;
    private Date [] dateArray;
    private Date firstDate;
    private Date lastDate;


    private JPanel panelSlider;

    public ITextAnalyzer analyzer = new TextAnalyzer();

    public void drawGui(ITextAnalyzer analyzer) throws ParseException {
        this.analyzer = analyzer;
        this.firstDate = analyzer.getFirstDate();
        this.lastDate = analyzer.getLastDate();
        dateArray = analyzer.getDateArray();
        drawChart();



//        drawChart2();



    }

    public void drawChart() throws ParseException {

        JFrame jFrame = new JFrame("Top Receivers Chart");
        jFrame.setContentPane(rootPanel);
        jFrame.setBounds(0, 0, 1100, 300);


        setSlider1();
        setSlider2();
        panelCenter.setVisible(true);

        jFrame.setVisible(true);

        buttonShowBarChart.addActionListener(new ActionListener() {
                                      @Override
                                      public void actionPerformed(ActionEvent e) {

                                          panelCenter.setVisible(false);
                                          DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                                          for (int i = 0; i < 10; i++) {
                                              dataset.setValue(analyzer.topReceiversPairs.get(i).value, "", analyzer.topReceiversPairs.get(i).key);
                                          }
                                          JFreeChart chart = ChartFactory.createBarChart3D("Bar chart: top 10 Received Packets", "", "Packets", dataset,
                                                  PlotOrientation.HORIZONTAL, false, true, false);
                                          chart.setBackgroundPaint(Color.white);
                                          chart.getTitle().setPaint(Color.blue);
                                          CategoryPlot p = chart.getCategoryPlot();
                                          p.setRangeGridlinePaint(Color.GREEN);
                                          p.setDomainCrosshairPaint(Color.BLUE);
                                          p.setRangeCrosshairPaint(Color.ORANGE);
                                          p.setOutlinePaint(Color.BLUE);

                                          ChartPanel panelChart1 = new ChartPanel(chart);
                                          panelCenter.removeAll();
                                          panelCenter.add(panelChart1, BorderLayout.CENTER);
                                          panelCenter.validate();
                                          panelCenter.setVisible(true);
                                          panelSlider.setVisible(false);
                                          jFrame.setBounds(0, 0, 1100, 500);
                                      }
                                  }
        );

        buttonReset.addActionListener(new ActionListener() {
                                      @Override
                                      public void actionPerformed(ActionEvent e) {
                                          panelCenter.removeAll();
                                          panelSlider.setVisible(true);
                                          setSlider1();
                                          jFrame.setBounds(0, 0, 1100, 300);
                                      }
                                  }
        );

        buttonShowPieChart.addActionListener(new ActionListener() {
                                      @Override
                                      public void actionPerformed(ActionEvent e) {
                                          panelCenter.setVisible(false);
                                          DefaultPieDataset datasetPie = new DefaultPieDataset();

                                          for (int i = 0; i < 10; i++) {
                                              datasetPie.setValue(analyzer.topReceiversPairs.get(i).key, new Integer(analyzer.topReceiversPairs.get(i).value));
                                          }

                                          JFreeChart chartPie = ChartFactory.createPieChart3D("Pie Chart: top 10 Received Packets", datasetPie, true, true, true);
                                          chartPie.setBackgroundPaint(Color.white);
                                          chartPie.getTitle().setPaint(Color.blue);
                                          PiePlot3D p = (PiePlot3D)chartPie.getPlot();
                                          ChartPanel panelChart1Pie = new ChartPanel(chartPie);
                                          panelCenter.removeAll();
                                          panelCenter.validate();
                                          panelCenter.add(panelChart1Pie, BorderLayout.CENTER);

                                          panelCenter.setVisible(true);

                                          panelSlider.setVisible(false);
                                          jFrame.setBounds(0, 0, 1100, 500);

                                      }
                                  }
        );
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
        ChartFrame frame1 = new ChartFrame("Top 10 Transmitters", chart);
        frame1.setVisible(true);
        frame1.setSize(900, 500);

    }

    private void setSlider1(){
        JSlider slider1 = new JSlider(JSlider.HORIZONTAL, 0, 100 , 20);
        panelCenter.add(slider1);
        Hashtable<Integer, Component> labelTable = new Hashtable();
        labelTable.put(0, new JLabel(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(firstDate)));
        labelTable.put(20, new JLabel(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateArray[1])));
        labelTable.put(40, new JLabel(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateArray[2])));
        labelTable.put(60, new JLabel(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateArray[3])));
        labelTable.put(80, new JLabel(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateArray[4])));
        labelTable.put(100, new JLabel(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(lastDate)));
        slider1.setLabelTable(labelTable);
        slider1.setMinorTickSpacing(1);
        slider1.setMajorTickSpacing(20);
        slider1.setValue(0);
        slider1.setPaintTicks(true);
        slider1.setPaintLabels(true);

    }

    private void setSlider2 (){
        JSlider slider2 = new JSlider(JSlider.HORIZONTAL, 0, 100 , 20);
        panelSlider.add(slider2);
        Hashtable<Integer, Component> labelTable = new Hashtable();
        labelTable.put(0, new JLabel(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(firstDate)));
        labelTable.put(20, new JLabel(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateArray[1])));
        labelTable.put(40, new JLabel(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateArray[2])));
        labelTable.put(60, new JLabel(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateArray[3])));
        labelTable.put(80, new JLabel(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateArray[4])));
        labelTable.put(100, new JLabel(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(lastDate)));
        slider2.setLabelTable(labelTable);
        slider2.setMinorTickSpacing(1);
        slider2.setMajorTickSpacing(20);
        slider2.setValue(100);
        slider2.setPaintTicks(true);
        slider2.setPaintLabels(true);
    }


}



//////////////////////////////////            DATES          //////////////////////////////////////////////////////////////


//        Date date = firstDate;
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



