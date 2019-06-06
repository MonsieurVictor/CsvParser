/**Create a class that computes the Top 10 traffic receivers,
 * Top 10 traffic transmitters,
 * top 3 used protocols,
 * and top 10 used applications using NetFlow data taken from the supplied archive(attached CSV file).
 Using time range as an Input parameter for this method, calculate data for and numbers for traffic, protocols and application counts.
 The input of NetFlow data should be handled by its own class, called from the above method.
 Output can be any structure containing the results of the computation.
 Serialize computation results to a JSON string and save to a file.
 Using computation results make a GUI window with a chart for received and transmitted bytes over time for top network transmitter and receiver. You can use any graph or chart libraries that you wish.

 You can use any open source libraries to accomplish this task

 Please confirm receipt of the email.*/

import java.io.IOException;

public class Main {

    private ITextReader reader;
    private ITextAnalyzer analyzer;
    private IAppOptions options;
    private IResultViewer viewer;
    private IErrorLogger logger;
    private IGuiForm guiForm;
    private IJSONSaver jsonSaver;

    public Main (
                 IAppOptions options,
                 ITextReader reader,
                 ITextAnalyzer analyzer,
                 IErrorLogger logger,
                 IGuiForm guiTop10Receivers,
                 IJSONSaver jsonSaver
              ) {

        this.options = options;
        this.reader = reader;
        this.analyzer = analyzer;
        this.jsonSaver = jsonSaver;
        this.viewer = viewer;
        this.logger = logger;
        this.guiForm = guiTop10Receivers;
        this.jsonSaver = jsonSaver;
    }

    public void execute() {
        try {
            options.parseOptions();
            analyzer.setBuffer(reader.getTextBuffer(options.getFilePath()));
            analyzer.doAnalyze();
//            guiLauncher.drawLauncher(analyzer, guiForm);
            guiForm.startDraw(analyzer);
//            viewer.report(analyzer);

        } catch (IOException e) {
            logger.errorOpen(e);
//             nice report on can't open the file
        } catch (Exception e) {
            logger.errorReadFlags(e);
//             nice report here
        }
    }

    public static void main(String[] args) {
        IAppOptions options = new AppOptions();
        ITextReader reader = new TextReader();
        IErrorLogger logger = new ErrorLogger();
        ITextAnalyzer analyzer = new TextAnalyzer();
        IGuiForm form = new GuiForm();
        IJSONSaver jsonSaver = new JSONSaver();

//        IResultViewer viewer = new ConsoleResultViewer();
        Main app = new Main(options, reader, analyzer, logger, form, jsonSaver); //почему нельзя просто создать объект с пустыми аргументами?
        app.execute();
    }
}
