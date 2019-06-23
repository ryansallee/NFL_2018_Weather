package org.codelouisville.data;

import org.codelouisville.data.Models.CityState;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Main {
    public static String getState(String teamName)
    {
        return CityState.valueOf(teamName).getState();
    }
    public static void main(String[] args) throws ParseException {
        System.out.printf("%s %n", getState("NEW_ENGLAND_PATRIOTS"));

    LocalDateTime ldt = LocalDateTime.parse("September 06 2018 08:20 PM",
            DateTimeFormatter.ofPattern("MMMM dd yyyy hh:mm a"));
    System.out.printf("%s%n", ldt);
    System.out.printf("%s", ldt.toEpochSecond(ZoneOffset.ofHours(-5)));
    }
}



