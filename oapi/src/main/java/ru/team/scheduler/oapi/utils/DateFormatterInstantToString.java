package ru.team.scheduler.oapi.utils;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Component
public class DateFormatterInstantToString {
    private final DateTimeFormatter formatter;

    public DateFormatterInstantToString() {
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");;
    }

    public String dateFromInstant (Instant instantDate){
        return formatter.format(instantDate);
    }
}
