package org.codelouisville.data.Logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ReadCsv {
    static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("MMMM dd yyyy hh:mm a");
    static final ZoneOffset EASTERN_TIME_OFFSET = ZoneOffset.ofHours(-5);
    static final File CSV_2018_NFL =
            new File("src/main/resources/org/codelouisville/csvs/2018_nfl_results.csv");
    static BufferedReader br;

    static {
        try {
            br = Files.newBufferedReader(CSV_2018_NFL.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readingCSV()
    {
        List<String> listz = br.lines()
                .collect(Collectors.toList());
        listz.forEach(System.out::println);

    }


}
