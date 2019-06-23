package org.codelouisville.data.Logic;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class ReadCsv {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("MMMM dd yyyy hh:mm a");
    private static final ZoneOffset EASTERN_TIME_OFFSET = ZoneOffset.ofHours(-5);
}
