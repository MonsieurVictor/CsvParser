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

    List <TextAnalyzer.TopRatedPair> topReceiversPairs = new ArrayList<>();
    List <TextAnalyzer.TopRatedPair> topTransmittersPairs = new ArrayList<>();
    List <TextAnalyzer.TopRatedPair> topProtocolsPairs = new ArrayList<>();
    List <TextAnalyzer.TopRatedPair> topUsedApplicationsPairs = new ArrayList<>();

    public class TopRatedPair {
        String key;
        int value;
        TopRatedPair(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    public void setBuffer(List buffer) {
//        this.buffer.clear();
        this.buffer = buffer;
    }

    public void doAnalyze(ControllerGui controller) throws ParseException {
        this.controller = controller;
        addObserver(controller);
        recordList = record.parseRecordList(this.buffer);

        setDateFirst();
        setDateLast();
        countRangeInitialDates(dateFirst, dateLast);
//        getTopReceiversPairs();
//        getTopTransmittersPairs();
//        getTopProtocols();
//        getTopUsedApplications();

    }

    public void doAnalyze() throws ParseException {
        recordList = record.parseRecordList(this.buffer);
        setDateFirst();
        setDateLast();
        countRangeInitialDates(dateFirst, dateLast);
//        getTopReceiversPairs();
//        getTopTransmittersPairs();
//        getTopProtocols();
//        getTopUsedApplications();

//        getMapTopRxAnalyzer(dateFirst, dateLast);
    }

     public List getTopReceiversPairs(){
        topReceiversPairs.clear();
        Map<String, Integer> top10ReceiversMap = new HashMap<String, Integer>();

        for (Record currentDataFlowRow : recordList) {
            if (!currentDataFlowRow.getApplicationName().equals("")) {
                if (top10ReceiversMap.containsKey(currentDataFlowRow.getApplicationName())) {
                    Integer totalPacketsReceived = top10ReceiversMap.get(currentDataFlowRow.getApplicationName());
                    top10ReceiversMap.replace(currentDataFlowRow.getApplicationName(), totalPacketsReceived = totalPacketsReceived + currentDataFlowRow.getPacketsIn());
                } else {
                    top10ReceiversMap.put(currentDataFlowRow.getApplicationName(), currentDataFlowRow.getPacketsIn());
                }
            }
        }
        for (String key : top10ReceiversMap.keySet()) {
            topReceiversPairs.add(new TopRatedPair(key, top10ReceiversMap.get(key)));
        }
        Collections.sort(topReceiversPairs, (o1, o2) -> o2.value - o1.value);
        System.out.println(  " \n Top 10 Rated Receivers:");
        for (int i = 0; (i < 10) && (i < topReceiversPairs.size()); i++) {
            System.out.println(topReceiversPairs.get(i).key + "  " + topReceiversPairs.get(i).value);
        }
        return topReceiversPairs;
    }

    public List getTopTransmittersPairs(){
        topTransmittersPairs.clear();
        Map<String, Integer> top10TransmittersMap = new HashMap<String, Integer>();

        for (Record currentDataFlowRow : recordList) {
            if (!currentDataFlowRow.getApplicationName().equals("")) {
                if (top10TransmittersMap.containsKey(currentDataFlowRow.getApplicationName())) {
                    Integer totalPacketsTransmitted = top10TransmittersMap.get(currentDataFlowRow.getApplicationName());
                    top10TransmittersMap.replace(currentDataFlowRow.getApplicationName(), totalPacketsTransmitted = totalPacketsTransmitted + currentDataFlowRow.getPacketsOut());
                } else {
                    top10TransmittersMap.put(currentDataFlowRow.getApplicationName(), currentDataFlowRow.getPacketsOut());
                }
            }
        }
        for (String key : top10TransmittersMap.keySet()) {
            topTransmittersPairs.add(new TopRatedPair(key, top10TransmittersMap.get(key)));
        }
        Collections.sort(topTransmittersPairs, (o1, o2) -> o2.value - o1.value);
        System.out.println(  " \n Top 10 Rated Transmitters:");
        for (int i = 0; (i < 10) && (i < topTransmittersPairs.size()); i++) {
            System.out.println(topTransmittersPairs.get(i).key + "  " +  topTransmittersPairs.get(i).value);
        }
        return topTransmittersPairs;
    }

//    private List <TopRatedPair> getTopProtocols(){
//        Map<String, Integer> topProtocolsMap = new HashMap<String, Integer>();
//
//        for (Record currentDataFlowRow : recordList) {
//            if (!currentDataFlowRow.getProtocolNumber().equals("")) {
//                if (topProtocolsMap.containsKey(currentDataFlowRow.getProtocolNumber())) {
//                    Integer totalTimesProtocolUsed = topProtocolsMap.get(currentDataFlowRow.getProtocolNumber());
//                    topProtocolsMap.replace(currentDataFlowRow.getProtocolNumber(), totalTimesProtocolUsed = totalTimesProtocolUsed + 1);
//                } else {
//                    topProtocolsMap.put(currentDataFlowRow.getProtocolNumber(), 1);
//                }
//            }
//        }
//        for (String key : topProtocolsMap.keySet()) {
//            topProtocolsPairs.add(new TopRatedPair(key, topProtocolsMap.get(key)));
//        }
//        Collections.sort(topProtocolsPairs, (o1, o2) -> o2.value - o1.value);
//        System.out.println(  " \n Top 3 Used Protocols:");
//        for (int i = 0; (i < 3) && (i < topProtocolsPairs.size()); i++) {
//            System.out.println(topProtocolsPairs.get(i).key + "  " +  topProtocolsPairs.get(i).value);
//        }
//        return topProtocolsPairs;
//    }
//
//    private List <TopRatedPair> getTopUsedApplications(){
//        Map<String, Integer> topUsedApplicationsMap = new HashMap<String, Integer>();
//
//        for (Record currentDataFlowRow : recordList) {
//            if (!currentDataFlowRow.getApplicationName().equals("")) {
//                if (topUsedApplicationsMap.containsKey(currentDataFlowRow.getApplicationName())) {
//                    Integer totalTimesApplicationUsed = topUsedApplicationsMap.get(currentDataFlowRow.getApplicationName());
//                    topUsedApplicationsMap.replace(currentDataFlowRow.getApplicationName(), totalTimesApplicationUsed = totalTimesApplicationUsed + 1);
//                } else {
//                    topUsedApplicationsMap.put(currentDataFlowRow.getApplicationName(), 1);
//                }
//            }
//        }
//        for (String key : topUsedApplicationsMap.keySet()) {
//            topUsedApplicationsPairs.add(new TopRatedPair(key, topUsedApplicationsMap.get(key)));
//        }
//        Collections.sort(topUsedApplicationsPairs, (o1, o2) -> o2.value - o1.value);
//        System.out.println(  " \n Top 10 Used Applications:");
//        for (int i = 0; (i < 10) && (i < topUsedApplicationsPairs.size()); i++) {
//            System.out.println(topUsedApplicationsPairs.get(i).key + "  " +  topUsedApplicationsPairs.get(i).value);
//        }
//        return topUsedApplicationsPairs;
//    }

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

