import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

/**
 * implements the logic of analyzing text
 */

public interface ITextAnalyzer {

    void setBuffer(List buffer);


    void doAnalyze() throws ParseException;

    List <TextAnalyzer.DataFlowStructure> dataFlowStructureList = new ArrayList<>();
    List <TextAnalyzer.TopRatedPair> topReceiversPairs = new ArrayList<>();
    List <TextAnalyzer.TopRatedPair> topTransmittersPairs = new ArrayList<>();
    List <TextAnalyzer.TopRatedPair> topProtocolsPairs = new ArrayList<>();
    List <TextAnalyzer.TopRatedPair> topUsedApplicationsPairs = new ArrayList<>();

    Date getFirstDate() throws ParseException;
    Date getLastDate()  throws ParseException;
    void setDateArray ();
    Date [] getDateArray ();

}
