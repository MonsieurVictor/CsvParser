import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class ControllerGui implements IController, IObserver {
    public ControllerGui(){}
    private TextReader reader;
    private TextAnalyzer analyzer;
    private AppOptions options;
    private ErrorLogger logger;
    private GuiForm guiForm;
    private JSONSaver jsonSaver;
    private ControllerGui controller;

    public void initGui(AppOptions options,
                        TextReader reader,
                        TextAnalyzer analyzer,
                        GuiForm guiForm,
                        ErrorLogger logger,
                        JSONSaver jsonSaver,
                        ControllerGui controller) {
        this.options = options;
        this.reader = reader;
        this.analyzer = analyzer;
        this.guiForm = guiForm;
        this.logger = logger;
        this.jsonSaver = jsonSaver;
        this.controller = controller;

        try {
            options.parseOptions();
            analyzer.setBuffer(reader.getTextBuffer(options.getFilePath()));
            analyzer.doAnalyze(controller);
            guiForm.startDraw(controller, guiForm);
        } catch (IOException e) {
            logger.logIOException(e);
            //             nice report on can't open the file
        } catch (Exception e) {
            logger.logException(e);
            //             nice report here
        }
    }

    public void handleCalculationEvent(ControllerGui controller) {
        System.out.println("event of calculated handled");
        if (guiForm.categoryFlag == "none"){
            System.out.println("Category is not selected!");
        } else if (guiForm.categoryFlag == "Rx"){
            System.out.println(guiForm.categoryFlag);
            guiForm.updateChartsTopRx();
        } else if (guiForm.categoryFlag == "Tx") {
            System.out.println(guiForm.categoryFlag);
            guiForm.updateChartsTopTx();
        }
    }

    public Date getDateFirst() throws ParseException {
        return analyzer.getDateFirst();
    }
    public Date getDateLast() throws ParseException {
        return analyzer.getDateLast();
    }

    public Date[] getDateArrayForSliderLabels(){
        return analyzer.getDateArrayForSliderLabels();
    }

    public void setDateArrayForSliderLabels(){
        analyzer.setDateArrayForSliderLabels();
    }

    public List <TextAnalyzer.TopRatedPair> getTopReceiversPairs(){
        return analyzer.getTopReceiversPairs();
    }

    public List <TextAnalyzer.TopRatedPair> getTopTransmittersPairs(){
        return analyzer.getTopTransmittersPairs();
    }

    public void reparseRecordListDateRange(Date dateFrom, Date dateTo) throws ParseException {
        try {
            analyzer.reparseRecordListDateRange(dateFrom, dateTo);
        } catch (ParseException e) {
            logger.logParseException(e);
        }
    }

    public int getDiffSec(){
         return analyzer.getDiffSec();
    }
}
