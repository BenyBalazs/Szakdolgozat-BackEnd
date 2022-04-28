package com.benyovszki.szakdolgozat.util;



import org.joda.time.DateTime;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;

public class DateTimeUtil {

    public static Date dtoTimeToDate(XMLGregorianCalendar xmlGregorianCalendar) {
        return toDateTime(xmlGregorianCalendar).toDate();
    }

    public static Date toStartOfDate(XMLGregorianCalendar xmlGregorianCalendar) {
        return toDateTime(xmlGregorianCalendar).withTimeAtStartOfDay().toDate();
    }
    public static Date toEndOfDate(XMLGregorianCalendar xmlGregorianCalendar) {
        return toDateTime(xmlGregorianCalendar).withTime(23, 59, 59, 999).toDate();
    }

    private static DateTime toDateTime(XMLGregorianCalendar xmlGregorianCalendar) {
        return new DateTime(xmlGregorianCalendar.toGregorianCalendar());
    }
}
