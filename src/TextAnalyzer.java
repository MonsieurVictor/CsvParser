import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class TextAnalyzer implements ITextAnalyzer {


    private Date firstDate;
    private Date lastDate;
    private int diffSec;
    private Date [] dateArray = new Date [6];


    public class DataFlowStructure {

        String rawDate;
        long bytesIn;
        long bytesOut;
        int packetsIn;
        int packetsOut;
        String applicationName;
        String destinationAddress;
        String protocolNumber;
        String sourceAddress;



        DataFlowStructure(
                String rawDate,
                Long bytesIn,
                Long bytesOut,
                Integer packetsIn,
                Integer packetsOut,
                String applicationName,
                String destinationAddress,
                String protocolNumber,
                String sourceAddress) {

            this.rawDate = rawDate;
            this.bytesIn = bytesIn;
            this.bytesOut = bytesOut;
            this.packetsIn = packetsIn;
            this.packetsOut = packetsOut;
            this.applicationName = applicationName;
            this.destinationAddress = destinationAddress;
            this.protocolNumber = protocolNumber;
            this.sourceAddress = sourceAddress;
        }
    }


    public class TopRatedPair {

        String key;
        int value;

        TopRatedPair(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private List buffer;

    public void setBuffer(List buffer) {
        this.buffer = buffer;
    }

    public void doAnalyze() throws ParseException {
        parseDataFlowStructure(this.buffer);
        getTopReceivers();
        getTopTransmitters();
        getTopProtocols();
        getTopUsedApplications();
        this.firstDate = getFirstDate();
        this.lastDate = getLastDate();
        countDatesRange(firstDate, lastDate);
        setDateArray();


//        getTopProtocols();
//        getTopUsedApplications();


    }

//    private Integer countMethod(String methodString, int numberBefore, int newNumber ) {
//        if (String methodString == "Summ"){
//
//        }
//    }

    public List <TopRatedPair> getTopReceivers(){

        Map<String, Integer> top10ReceiversMap = new HashMap<String, Integer>();

        for (DataFlowStructure currentDataFlowRow : dataFlowStructureList) {
            if (!currentDataFlowRow.applicationName.equals("")) {
                if (top10ReceiversMap.containsKey(currentDataFlowRow.applicationName)) {
                    Integer totalPacketsReceived = top10ReceiversMap.get(currentDataFlowRow.applicationName);
                    top10ReceiversMap.replace(currentDataFlowRow.applicationName, totalPacketsReceived = totalPacketsReceived + currentDataFlowRow.packetsIn);
                } else {
                    top10ReceiversMap.put(currentDataFlowRow.applicationName, currentDataFlowRow.packetsIn);
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




    public List <TopRatedPair> getTopTransmitters(){

        Map<String, Integer> top10TransmittersMap = new HashMap<String, Integer>();

        for (DataFlowStructure currentTransmitter : dataFlowStructureList) {
            if (!currentTransmitter.applicationName.equals("")) {
                if (top10TransmittersMap.containsKey(currentTransmitter.applicationName)) {
                    Integer totalPacketsTransmitted = top10TransmittersMap.get(currentTransmitter.applicationName);
                    top10TransmittersMap.replace(currentTransmitter.applicationName, totalPacketsTransmitted = totalPacketsTransmitted + currentTransmitter.packetsOut);
                } else {
                    top10TransmittersMap.put(currentTransmitter.applicationName, currentTransmitter.packetsOut);
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

    public List <TopRatedPair> getTopProtocols(){

        Map<String, Integer> topProtocolsMap = new HashMap<String, Integer>();

        for (DataFlowStructure currentProtocol : dataFlowStructureList) {
            if (!currentProtocol.protocolNumber.equals("")) {
                if (topProtocolsMap.containsKey(currentProtocol.protocolNumber)) {
                    Integer totalTimesProtocolUsed = topProtocolsMap.get(currentProtocol.protocolNumber);
                    topProtocolsMap.replace(currentProtocol.protocolNumber, totalTimesProtocolUsed = totalTimesProtocolUsed + 1);
                } else {
                    topProtocolsMap.put(currentProtocol.protocolNumber, 1);
                }
            }
        }

        for (String key : topProtocolsMap.keySet()) {
            topProtocolsPairs.add(new TopRatedPair(key, topProtocolsMap.get(key)));
        }

        Collections.sort(topProtocolsPairs, (o1, o2) -> o2.value - o1.value);

        System.out.println(  " \n Top 3 Used Protocols:");
        for (int i = 0; (i < 3) && (i < topProtocolsPairs.size()); i++) {
            System.out.println(topProtocolsPairs.get(i).key + "  " +  topProtocolsPairs.get(i).value);
        }

        return topProtocolsPairs;
    }

    public List <TopRatedPair> getTopUsedApplications(){

        Map<String, Integer> topUsedApplicationsMap = new HashMap<String, Integer>();

        for (DataFlowStructure currentApplication : dataFlowStructureList) {
            if (!currentApplication.applicationName.equals("")) {
                if (topUsedApplicationsMap.containsKey(currentApplication.applicationName)) {
                    Integer totalTimesApplicationUsed = topUsedApplicationsMap.get(currentApplication.applicationName);
                    topUsedApplicationsMap.replace(currentApplication.applicationName, totalTimesApplicationUsed = totalTimesApplicationUsed + 1);
                } else {
                    topUsedApplicationsMap.put(currentApplication.applicationName, 1);
                }
            }
        }

        for (String key : topUsedApplicationsMap.keySet()) {
            topUsedApplicationsPairs.add(new TopRatedPair(key, topUsedApplicationsMap.get(key)));
        }

        Collections.sort(topUsedApplicationsPairs, (o1, o2) -> o2.value - o1.value);

        System.out.println(  " \n Top 10 Used Applications:");
        for (int i = 0; (i < 10) && (i < topUsedApplicationsPairs.size()); i++) {
            System.out.println(topUsedApplicationsPairs.get(i).key + "  " +  topUsedApplicationsPairs.get(i).value);
        }
        return topUsedApplicationsPairs;
    }

    public Date getFirstDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss.SSSSSSSSS a");
        String firstDateInCSV = dataFlowStructureList.get(0).rawDate;
        return sdf.parse(firstDateInCSV);
    }

    public Date getLastDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss.SSSSSSSSS a");
        String lastDateInCSV = dataFlowStructureList.get(dataFlowStructureList.size()-1).rawDate;
        return sdf.parse(lastDateInCSV);
    }


    public void setDateArray () {

        dateArray[0] = this.firstDate;
        dateArray[5] = this.lastDate;

        Calendar cal = Calendar.getInstance();

        for (int i = 1; i <= 4; i++) {

            cal.setTime(firstDate);
            int rangeBetweenSlices = diffSec / 5;
            cal.add(Calendar.SECOND, rangeBetweenSlices*i );
            dateArray[i]= cal.getTime();
            System.out.println("added one slice: "+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(cal.getTime()));

        }
    }

    public Date [] getDateArray () {
        return this.dateArray;
    }

    public void countDatesRange (Date firstDate, Date lastDate){
        try {

            System.out.println("\ndifference between:");
            System.out.println(firstDate);
            System.out.println(lastDate + "\n");

            DecimalFormat crunchifyFormatter = new DecimalFormat("###,###");

            // getTime() returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object
            long diff = lastDate.getTime() - firstDate.getTime();

            int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
            System.out.println("difference between days: " + diffDays);

            int diffhours = (int) (diff / (60 * 60 * 1000));
            System.out.println("difference between hours: " + crunchifyFormatter.format(diffhours));

            int diffmin = (int) (diff / (60 * 1000));
            System.out.println("difference between minutes: " + crunchifyFormatter.format(diffmin));

            this.diffSec = (int) (diff / (1000));
            System.out.println("difference between seconds: " + crunchifyFormatter.format(diffSec));

            System.out.println("difference between milliseconds: " + crunchifyFormatter.format(diff));

            System.out.println(firstDate);
            System.out.println(lastDate + "\n");
            System.out.println("MM/dd/yyyy formatted date : " + new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(firstDate));
            System.out.println("yyyy-MM-dd formatted date : " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(lastDate));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public List parseDataFlowStructure(List <String[]> buffer) {

        for (String[] csvRow: buffer){

            String rawDate = csvRow[0] ;
            long bytesIn = Long.parseLong(csvRow[1]);
            long bytesOut = Long.parseLong(csvRow[2]);
            int packetsIn = Integer.parseInt(csvRow[3]);
            int packetsOut = Integer.parseInt(csvRow[4]);
            String applicationName = csvRow[5];
            String destinationAddress = csvRow[6] ;
            String protocolNumber  = csvRow[7];
            String sourceAddress  = csvRow[8];
            this.dataFlowStructureList.add(new DataFlowStructure(
                    rawDate,
                    bytesIn,
                    bytesOut,
                    packetsIn,
                    packetsOut,
                    applicationName,
                    destinationAddress,
                    protocolNumber,
                    sourceAddress));
        }

        return dataFlowStructureList;
    }





}
