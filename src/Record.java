import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Record {

    private Date dateObj;
    private long bytesIn;
    private long bytesOut;
    private int packetsIn;
    private int packetsOut;
    private String applicationName;
    private String destinationAddress;
    private String protocolNumber;
    private String sourceAddress;

    List <Record>recordList = new ArrayList<>();

    public Record(){}

    private Record(
            Date dateObj,
            Long bytesIn,
            Long bytesOut,
            Integer packetsIn,
            Integer packetsOut,
            String applicationName,
            String destinationAddress,
            String protocolNumber,
            String sourceAddress) {
        this.dateObj = dateObj;
        this.bytesIn = bytesIn;
        this.bytesOut = bytesOut;
        this.packetsIn = packetsIn;
        this.packetsOut = packetsOut;
        this.applicationName = applicationName;
        this.destinationAddress = destinationAddress;
        this.protocolNumber = protocolNumber;
        this.sourceAddress = sourceAddress;
    }


    public List <Record> parseRecordList(List <String[]> buffer) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss.SSSSSSSSS a");
        for (String[] csvRow : buffer) {
            Date dateObj = sdf.parse(csvRow[0]);
            long bytesIn = Long.parseLong(csvRow[1]);
            long bytesOut = Long.parseLong(csvRow[2]);
            int packetsIn = Integer.parseInt(csvRow[3]);
            int packetsOut = Integer.parseInt(csvRow[4]);
            String applicationName = csvRow[5];
            String destinationAddress = csvRow[6];
            String protocolNumber = csvRow[7];
            String sourceAddress = csvRow[8];
            this.recordList.add(new Record(
                    dateObj,
                    bytesIn,
                    bytesOut,
                    packetsIn,
                    packetsOut,
                    applicationName,
                    destinationAddress,
                    protocolNumber,
                    sourceAddress));
        }
        return recordList;
    }

    public List <Record> reparseRecordListDateRange(Date dateFrom, Date dateTo, List buffer) throws ParseException {
        recordList.clear();
        parseRecordList(buffer);

        Calendar calDateFrom = Calendar.getInstance();
        calDateFrom.setTime(dateFrom);
        Calendar calDateTo = Calendar.getInstance();
        calDateTo.setTime(dateTo);

        for (int i = 0; i < recordList.size(); i++) {
            Date testDate = recordList.get(i).getDateObj();
            Calendar calDateFromCsv = Calendar.getInstance();
            calDateFromCsv.setTime(testDate);
            if(calDateFrom.after(calDateFromCsv)){
                recordList.remove(i);
                i--;
            }else if (calDateTo.before(calDateFromCsv)) {
                recordList.remove(i);
                i--;
            }
        }
        return recordList;
    }

    public Date getDateObj() {
        return dateObj;
    }

    public void setDateObj(Date dateObj) {
        this.dateObj = dateObj;
    }

    public long getBytesIn() {
        return bytesIn;
    }

    public void setBytesIn(long bytesIn) {
        this.bytesIn = bytesIn;
    }

    public long getBytesOut() {
        return bytesOut;
    }

    public void setBytesOut(long bytesOut) {
        this.bytesOut = bytesOut;
    }

    public int getPacketsIn() {
        return packetsIn;
    }

    public void setPacketsIn(int packetsIn) {
        this.packetsIn = packetsIn;
    }

    public int getPacketsOut() {
        return packetsOut;
    }

    public void setPacketsOut(int packetsOut) {
        this.packetsOut = packetsOut;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }
}
