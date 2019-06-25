import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * implements the logic of analyzing text
 */

public interface ITextAnalyzer {
    int getDiffSec();
    void setBuffer(List buffer);
    void doAnalyze(ControllerGui controller) throws ParseException;
    void reparseRecordListDateRange(Date dateFrom, Date dateTo) throws ParseException;
    List getTopReceiversPairs();
    List getTopTransmittersPairs();
    Date getDateOfSlider (int sliderValue);
    Date getDateFirst() throws ParseException;
    Date getDateLast()  throws ParseException;
    void setDateArrayForSliderLabels();
    Date [] getDateArrayForSliderLabels();
}
