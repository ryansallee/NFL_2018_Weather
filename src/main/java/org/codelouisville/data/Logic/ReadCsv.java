package org.codelouisville.data.Logic;

import org.codelouisville.data.Models.CityState;
import org.codelouisville.data.Models.Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;


public class ReadCsv {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("MMMM d yyyy h:mma");
    private static final ZoneOffset EASTERN_TIME_OFFSET = ZoneOffset.ofHours(-5);
    private static final File CSV_2018_NFL =
            new File("src/main/resources/org/codelouisville/csvs/2018_nfl_results.csv");
    private static BufferedReader br;

    static {
        try {
            br = Files.newBufferedReader(CSV_2018_NFL.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void readingCSV() throws IOException {
        //List<String> games = new ArrayList<>();
        CSVReader reader = new CSVReader(br);
        String[] nextLine;
        reader.readNext();
        while ((nextLine = reader.readNext()) != null) {
            String homeTeam;
            String awayTeam;
            String homePoints;
            String awayPoints;
            if (nextLine[5].equals("@")) {
                homeTeam = nextLine[6];
                awayTeam = nextLine[4];
                homePoints = nextLine[9];
                awayPoints = nextLine[8];
            } else {
                homeTeam = nextLine[4];
                awayTeam = nextLine[6];
                homePoints = nextLine[8];
                awayPoints = nextLine[9];
            }
            System.out.println(getEpochTime(nextLine[2],nextLine[3]) + "Home" +
                    homeTeam + " vs." + awayTeam + homePoints + "-" +
                    awayPoints + " " +getStadiumCity(homeTeam) + ", " + getStadiumState(homeTeam) +
                    " isDome?" + getIsStadiumDome(homeTeam)
            );
        }


    }

    private static long getEpochTime(String date, String time)
    {
        String dateAndTime = date + " 2018 " + time;
        LocalDateTime ldt = LocalDateTime.parse(dateAndTime, TIME_FORMATTER);
        return ldt.toEpochSecond(EASTERN_TIME_OFFSET);
    }

    private static String getStadiumCity(String homeTeam)
    {
        return CityState.valueOf(homeTeam.replace(" ", "_")
                        .toUpperCase()).getCity();
    }

    private static String getStadiumState(String homeTeam)
    {
        return CityState.valueOf(homeTeam.replace(" ", "_")
                        .toUpperCase()).getState();
    }

    private static Boolean getIsStadiumDome(String homeTeam){
        return CityState.valueOf(homeTeam.replace(" ", "_")
                .toUpperCase()).getIsDome();
    }

}
