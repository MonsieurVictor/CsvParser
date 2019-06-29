package main.java;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * implements the logic of analyzing text
 */

public interface ITextAnalyzer {
    int getDiffSec();
    void setBuffer(List buffer);
    void doAnalyze(ControllerGui controller) throws ParseException;
    void doAnalyze() throws ParseException;
    void reparseRecordListDateRange(Date dateFrom, Date dateTo) throws ParseException;
    List getTopReceiversPairs();
    List getTopTransmittersPairs();
    Date getDateOfSlider (int sliderValue);
    Date getDateFirst() throws ParseException;
    Date getDateLast()  throws ParseException;
    void setDateArrayForSliderLabels();
    Date [] getDateArrayForSliderLabels();
    Map <String, Long> getMapTopRx(Date dateFrom, Date dateTo);
    Map <String, Long> getMapTopTx(Date dateFrom, Date dateTo);
    Map <String, Long> getMapTopProtocols(Date dateFrom, Date dateTo);
    Map <String, Long> getMapTopApps(Date dateFrom, Date dateTo);
}
