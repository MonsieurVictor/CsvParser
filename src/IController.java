import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IController {
    void initGui(IAppOptions options,
                 ITextReader reader,
                 ITextAnalyzer analyzer,
                 IGuiForm guiForm,
                 IErrorLogger logger,
                 IJSONSaver jsonSaver,
                 ControllerGui controller);

    Date getDateFirst() throws ParseException;

    Date getDateLast() throws ParseException ;

    Date[] getDateArrayForSliderLabels();

    void setDateArrayForSliderLabels();

    List<TextAnalyzer.TopRatedPair> getTopReceiversPairs();

    List <TextAnalyzer.TopRatedPair> getTopTransmittersPairs();

    void reparseRecordListDateRange(Date dateFrom, Date dateTo) throws ParseException;

    int getDiffSec();
}
