import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * implements the logic of analyzing text
 */

public interface ITextAnalyzer {
    void setBuffer(List buffer);
    void doAnalyze(GuiForm guiForm) throws ParseException;
    void reparseRecordListDateRange(Date dateFrom, Date dateTo) throws ParseException;
    List <TextAnalyzer.TopRatedPair> getTopReceiversPairs();
    List <TextAnalyzer.TopRatedPair> getTopTransmittersPairs();
    Date getDateOfSlider (int sliderValue);
    Date getDateFirst() throws ParseException;
    Date getDateLast()  throws ParseException;
    void setDateArrayForSliderLabels();
    Date [] getDateArrayForSliderLabels();
}
