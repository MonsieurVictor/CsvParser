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

public class Main {
    private ITextReader reader;
    private ITextAnalyzer analyzer;
    private IAppOptions options;
    private IErrorLogger logger;
    private IGuiForm guiForm;
    private IJSONSaver jsonSaver;
    private IConsoleLogger consoleLogger;
    private ControllerConsole controllerConsole;
    private ControllerGui controllerGui;

    private Main (
                 IAppOptions options,
                 ITextReader reader,
                 ITextAnalyzer analyzer,
                 IErrorLogger logger,
                 IGuiForm guiForm,
                 IJSONSaver jsonSaver,
                 IConsoleLogger consoleLogger,
                 ControllerConsole controllerConsole,
                 ControllerGui controllerGui
              ) {
        this.options = options;
        this.reader = reader;
        this.analyzer = analyzer;
        this.jsonSaver = jsonSaver;
        this.logger = logger;
        this.guiForm = guiForm;
        this.jsonSaver = jsonSaver;
        this.consoleLogger = consoleLogger;
        this.controllerConsole = controllerConsole;
        this.controllerGui = controllerGui;
    }

    private void start() {
        controllerConsole.initController(options, reader, analyzer,  guiForm, logger, jsonSaver, consoleLogger, controllerConsole);
        controllerGui.initController(options, reader, analyzer,  guiForm, logger, jsonSaver, controllerGui);
    }

    public static void main(String[] args) {
        IAppOptions options = new AppOptions();
        ITextReader reader = new TextReader();
        IErrorLogger logger = new ErrorLogger();
        ITextAnalyzer analyzer = new TextAnalyzer();
        IGuiForm guiForm = new GuiForm();
        IJSONSaver jsonSaver = new JSONSaver();
        IConsoleLogger consoleLogger = new ConsoleLogger();
        ControllerConsole controllerConsole = new ControllerConsole();
        ControllerGui controllerGui = new ControllerGui();
        Main app = new Main(options, reader, analyzer, logger, guiForm, jsonSaver, consoleLogger, controllerConsole, controllerGui); //почему нельзя просто создать объект с пустыми аргументами?
        app.start();
    }
}