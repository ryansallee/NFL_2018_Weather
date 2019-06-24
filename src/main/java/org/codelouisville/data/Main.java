package org.codelouisville.data;

import org.codelouisville.data.Logic.ReadCsv;
import org.codelouisville.data.Models.CityState;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Main {
    public static String getState(String teamName)
    {
        return CityState.valueOf(teamName).getState();
    }
    public static void main(String[] args) {
        System.out.printf("%s %n", getState("NEW_ENGLAND_PATRIOTS"));

        LocalDateTime ldt = LocalDateTime.parse("September 16 2018 8:20PM",
                DateTimeFormatter.ofPattern("MMMM d yyyy h:mma"));
        System.out.printf("%s%n", ldt);
        System.out.printf("%s%n", ldt.toEpochSecond(ZoneOffset.ofHours(-5)));
        try {
            ReadCsv.readingCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}



