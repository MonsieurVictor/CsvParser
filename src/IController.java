import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IController {
//    void initController(IAppOptions options,
//                        ITextReader reader,
//                        ITextAnalyzer analyzer,
//                        IGuiForm guiForm,
//                        IErrorLogger logger,
//                        IJSONSaver jsonSaver,
//                        ControllerGui controller);

    void initController();

    Date getDateFirst() throws ParseException;

    Date getDateLast() throws ParseException ;

    Date[] getDateArrayForSliderLabels();

    void setDateArrayForSliderLabels();

    void reparseRecordListDateRange(Date dateFrom, Date dateTo) throws ParseException;

    int getDiffSec();
}
