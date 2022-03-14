package com.benyovszki.szakdolgozat.util;

import org.joda.time.DateTime;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;

public class DateTimeUtil {

    public static Date dtoTimeToDate(XMLGregorianCalendar xmlGregorianCalendar) {
        DateTime dateTime = new DateTime(xmlGregorianCalendar.toGregorianCalendar());
        return dateTime.toDate();
    }
}
