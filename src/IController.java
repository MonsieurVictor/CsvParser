import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IController {
    void initGui(AppOptions options,
                        TextReader reader,
                        TextAnalyzer analyzer,
                        GuiForm guiForm,
                        ErrorLogger logger,
                        JSONSaver jsonSaver,
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
