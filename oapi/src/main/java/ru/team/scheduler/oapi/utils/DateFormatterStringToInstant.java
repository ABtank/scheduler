package ru.team.scheduler.oapi.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Component
public class DateFormatterStringToInstant {
    private final SimpleDateFormat simpleDateFormatForDateAndTime;
    private final SimpleDateFormat simpleDateFormatForDate;
    private final SimpleDateFormat simpleDateFormatForTime;

    public DateFormatterStringToInstant() {
        this.simpleDateFormatForTime = new SimpleDateFormat("HH:mm");
        this.simpleDateFormatForDateAndTime = new SimpleDateFormat("yy-MM-dd HH:mm");
        this.simpleDateFormatForDate = new SimpleDateFormat("yy-MM-dd");
//        simpleDateFormatForDate.setTimeZone(TimeZone.getTimeZone("Novosibirsk"));
//        simpleDateFormatForDateAndTime.setTimeZone(TimeZone.getTimeZone("Novosibirsk"));
//        simpleDateFormatForTime.setTimeZone(TimeZone.getTimeZone("Novosibirsk"));
    }

    public Instant stringToDateTime(String dateFromString){
        Date date = null;
        try {
            date = simpleDateFormatForDateAndTime.parse(dateFromString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert date != null;
        return date.toInstant();
    }

    public Instant stringToTime (String timeFromString){
        Date date = null;
        try {
            date = simpleDateFormatForTime.parse(timeFromString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert date != null;
        return date.toInstant();
    }

    public Instant stringToDate (String dateFromString){
        Date date = null;
        try {
            date = simpleDateFormatForDate.parse(dateFromString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert date != null;
        return date.toInstant();
    }
}
