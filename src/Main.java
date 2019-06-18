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
    private TextReader reader;
    private TextAnalyzer analyzer;
    private AppOptions options;
    private ErrorLogger logger;
    private GuiForm guiForm;
    private JSONSaver jsonSaver;
    private ControllerGui controllerGui;

    private Main (
                 AppOptions options,
                 TextReader reader,
                 TextAnalyzer analyzer,
                 ErrorLogger logger,
                 GuiForm guiForm,
                 JSONSaver jsonSaver,
                 ControllerGui controllerGui
              ) {
        this.options = options;
        this.reader = reader;
        this.analyzer = analyzer;
        this.jsonSaver = jsonSaver;
        this.logger = logger;
        this.guiForm = guiForm;
        this.jsonSaver = jsonSaver;
        this.controllerGui = controllerGui;
    }

    private void start() {
        try {
            controllerGui.initGui(analyzer, guiForm);
            options.parseOptions();
            analyzer.setBuffer(reader.getTextBuffer(options.getFilePath()));
            analyzer.doAnalyze(guiForm);
            guiForm.startDraw(analyzer, guiForm);
        } catch (IOException e) {
            logger.errorOpen(e);
//             nice report on can't open the file
        } catch (Exception e) {
            logger.errorReadFlags(e);
//             nice report here
        }
    }

    public static void main(String[] args) {
        AppOptions options = new AppOptions();
        TextReader reader = new TextReader();
        ErrorLogger logger = new ErrorLogger();
        TextAnalyzer analyzer = new TextAnalyzer();
        GuiForm guiForm = new GuiForm();
        JSONSaver jsonSaver = new JSONSaver();
        ControllerGui controllerGui = new ControllerGui();
        Main app = new Main(options, reader, analyzer, logger, guiForm, jsonSaver, controllerGui); //почему нельзя просто создать объект с пустыми аргументами?
        app.start();
    }
}
