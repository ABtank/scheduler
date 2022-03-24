package ru.team.scheduler.oapi.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Component
public class DateFormatterInstantToString {
    private final  DateTimeFormatter dateTimeFormatter;

    public DateFormatterInstantToString() {
        this.dateTimeFormatter =  DateTimeFormatter.ISO_LOCAL_TIME.withZone(ZoneId.from(ZoneOffset.UTC));
    }

    public String timeFromInstant (Instant instant) {
        return dateTimeFormatter.format(instant).substring(0,5);

    }
}
