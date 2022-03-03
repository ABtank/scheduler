package ru.team.scheduler.oapi.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Component
public class DateFormatterInstantToString {
    private final DateTimeFormatter dateTimeFormatter;

    public DateFormatterInstantToString() {
        this.dateTimeFormatter =  DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
                .withLocale(Locale.getDefault())
                .withZone( ZoneId.systemDefault());
    }

    public String timeFromInstant (Instant instantDate) {
        return dateTimeFormatter.ofPattern("HH:mm:ss").format(instantDate);

    }
}
