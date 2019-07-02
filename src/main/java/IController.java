package main.java;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IController {

    Date getDateFirst() throws ParseException;

    Date getDateLast() throws ParseException ;

    Date[] getDateArrayForSliderLabels();

    void setDateArrayForSliderLabels();

    void reparseRecordListDateRange(Date dateFrom, Date dateTo) throws ParseException;

    int getDiffSec();
}
