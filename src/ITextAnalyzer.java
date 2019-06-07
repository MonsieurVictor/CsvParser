import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * implements the logic of analyzing text
 */

public interface ITextAnalyzer {
    void setBuffer(List buffer);
    void doAnalyze() throws ParseException;
    void reparseDataFlowStructureListWithDateRange(Date dateFrom, Date dateTo) throws ParseException;
    List <TextAnalyzer.TopRatedPair> getTopReceivers();
    List <TextAnalyzer.TopRatedPair> getTopTransmitters();
    Date getDateOfSlider (int sliderValue);
    Date getDateFirst() throws ParseException;
    Date getDateLast()  throws ParseException;
    void setDateArrayForSliderLabels();
    Date [] getDateArrayForSliderLabels();
}
