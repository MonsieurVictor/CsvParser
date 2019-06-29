package main.java;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

public class ControllerConsole implements IController {

    public void initController(){}

    private ITextReader reader;
    private ITextAnalyzer analyzer;
    private IAppOptions options;
    private IErrorLogger logger;
    private IGuiForm guiForm;
    private IJSONSaver jsonSaver;
    private IConsoleLogger consoleLogger;
    private ControllerConsole controllerConsole;

    public void initController(IAppOptions options,
                            ITextReader reader,
                            ITextAnalyzer analyzer,
                            IGuiForm guiForm,
                            IErrorLogger logger,
                            IJSONSaver jsonSaver,
                            IConsoleLogger consoleLogger,
                            ControllerConsole controllerConsole){
        this.options = options;
        this.reader = reader;
        this.analyzer = analyzer;
        this.guiForm = guiForm;
        this.logger = logger;
        this.jsonSaver = jsonSaver;
        this.consoleLogger = consoleLogger;
        this.controllerConsole = controllerConsole;

        try {
            options.parseOptions();
            analyzer.setBuffer(reader.getTextBuffer(options.getFilePath()));
            analyzer.doAnalyze();
            consoleLogger.writeMapToConsole("Top 10 Receivers: " , analyzer.getMapTopRx(analyzer.getDateFirst(), analyzer.getDateLast()), 10);
            consoleLogger.writeMapToConsole("Top 10 Transmitters: " , analyzer.getMapTopTx(analyzer.getDateFirst(), analyzer.getDateLast()), 10);
            consoleLogger.writeMapToConsole("Top 3 Protocols: " , analyzer.getMapTopProtocols(analyzer.getDateFirst(), analyzer.getDateLast()), 3);
            consoleLogger.writeMapToConsole("Top 10 Applications: " , analyzer.getMapTopApps(analyzer.getDateFirst(), analyzer.getDateLast()), 10);
        } catch (IOException e) {
            logger.logIOException(e);
            //             nice report on can't open the file
        } catch (Exception e) {
            logger.logException(e);
            //             nice report here
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

    public Map<String, Long> getMapTopRx (Date dateFrom, Date dateTo) {
        return analyzer.getMapTopRx(dateFrom, dateTo);
    }

    public Map<String, Long> getMapTopTx (Date dateFrom, Date dateTo) {
        return analyzer.getMapTopTx(dateFrom, dateTo);
    }

    public void reparseRecordListDateRange(Date dateFrom, Date dateTo) {
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
