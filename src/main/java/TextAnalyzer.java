package main.java;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class TextAnalyzer implements ITextAnalyzer, IObservable {
    private Date dateFirst;
    private Date dateLast;
    private Date dateFrom;
    private Date dateTo;
    private int diffSec;
    private Date [] dateArray = new Date [6];
    private List buffer;
    ControllerGui controller;

    Record record = new Record();

    List <IObserver> observers = new ArrayList<>();

    List <Record> recordList = new ArrayList<>();

    public void setBuffer(List buffer) {
        this.buffer = buffer;
    }

    public void prepareForAnalyze(ControllerGui controller) throws ParseException {
        this.controller = controller;
        addObserver(controller);
        recordList = record.parseRecordList(this.buffer);

        setDateFirst();
        setDateLast();
        countRangeInitialDates(dateFirst, dateLast);
    }

    public void prepareForAnalyze() throws ParseException {
        recordList = record.parseRecordList(this.buffer);
        setDateFirst();
        setDateLast();
        countRangeInitialDates(dateFirst, dateLast);
    }

    private void setDateFirst() {
        this.dateFirst = recordList.get(0).getDateObj();
    }

    private void setDateLast() {
        this.dateLast = recordList.get(recordList.size() - 1).getDateObj();
    }

    public Date getDateFirst() {
        return dateFirst;
    }

    public Date getDateLast() {
        return dateLast;
    }

    public void setDateArrayForSliderLabels() {
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i <= 4; i++) {
            cal.setTime(dateFirst);
            int rangeBetweenSlices = diffSec / 5;
            cal.add(Calendar.SECOND, rangeBetweenSlices*i );
            dateArray[i]= cal.getTime();
        }
        dateArray[5] = this.dateLast;
    }

    public Date [] getDateArrayForSliderLabels() {
        return this.dateArray;
    }

    private void countRangeInitialDates(Date firstDate, Date lastDate){
        try {
            System.out.println("\ndifference between:");
            System.out.println(firstDate);
            System.out.println(lastDate + "\n");
            DecimalFormat decimalFormatter = new DecimalFormat("###,###");
            // getTime() returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object
            long diff = lastDate.getTime() - firstDate.getTime();
            int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
            System.out.println("difference between days: " + diffDays);
            int diffhours = (int) (diff / (60 * 60 * 1000));
            System.out.println("difference between hours: " + decimalFormatter.format(diffhours));
            int diffmin = (int) (diff / (60 * 1000));
            System.out.println("difference between minutes: " + decimalFormatter.format(diffmin));
            this.diffSec = (int) (diff / (1000));
            System.out.println("difference between seconds: " + decimalFormatter.format(diffSec));
            System.out.println("difference between milliseconds: " + decimalFormatter.format(diff));
            System.out.println("\nMM/dd/yyyy formatted date : " + new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(firstDate));
            System.out.println("MM/dd/yyyy formatted date : " + new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(lastDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Date getDateOfSlider (int sliderValue){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateFirst);
        cal.add(Calendar.SECOND, diffSec*sliderValue/1000 );
        return cal.getTime();
    }

    public void reparseRecordListDateRange(Date dateFrom, Date dateTo) throws ParseException {
        this.recordList = record.reparseRecordListDateRange(dateFrom,dateTo, this.buffer);
        notifyObserver();
    }

    public void addObserver(IObserver o){
        observers.add(o);
    }

    public void removeObserver(IObserver o){
        observers.remove(o);
    }

    public void notifyObserver() {
        for(IObserver o: observers){
            try {
                o.handleCalculationEvent(controller);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public int getDiffSec(){
           return this.diffSec;
    }

    public Map <String, Long> getMapTopRx(Date dateFrom, Date dateTo){
        Map <String, Long> mapTopRx = new HashMap<>();
        mapTopRx = recordList
                .stream()
                .filter(record -> record.getDateObj().after(dateFrom) && record.getDateObj().before(dateTo))
                .collect(Collectors.groupingBy(Record::getApplicationName, Collectors.summingLong(Record::getPacketsIn)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return mapTopRx;
    }

    public Map <String, Long> getMapTopTx(Date dateFrom, Date dateTo){
        Map <String, Long> mapTopTx = new HashMap<>();
        mapTopTx = recordList
                .stream()
                .filter(record -> record.getDateObj().after(dateFrom) && record.getDateObj().before(dateTo))
                .collect(Collectors.groupingBy(Record::getApplicationName, Collectors.summingLong(Record::getPacketsOut)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return mapTopTx;
    }

    public Map <String, Long> getMapTopProtocols(Date dateFrom, Date dateTo){
        Map <String, Long> mapTopProtocols = new HashMap<>();
        mapTopProtocols = recordList
                .stream()
                .filter(record -> record.getDateObj().after(dateFrom) && record.getDateObj().before(dateTo))
                .collect(Collectors.groupingBy(Record::getProtocolNumber, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return mapTopProtocols;
    }

    public Map <String, Long> getMapTopApps(Date dateFrom, Date dateTo){
        Map <String, Long> mapTopApps = new HashMap<>();
        mapTopApps = recordList
                .stream()
                .filter(record -> record.getDateObj().after(dateFrom) && record.getDateObj().before(dateTo))
                .collect(Collectors.groupingBy(Record::getApplicationName, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return mapTopApps;
    }
}

