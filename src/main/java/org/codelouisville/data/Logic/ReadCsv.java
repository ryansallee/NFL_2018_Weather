package org.codelouisville.data.Logic;

import com.jayway.jsonpath.JsonPath;
import org.codelouisville.data.Models.CityState;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.opencsv.CSVReader;


public class ReadCsv {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("MMMM d yyyy h:mma");
    private static final ZoneOffset EASTERN_TIME_OFFSET = ZoneOffset.ofHours(-5);
    private static final File CSV_2018_NFL =
            new File("src/main/resources/org/codelouisville/csvs/2018_nfl_results.csv");
    private static final String GOOGLE_GEOCODE_URL = "https://maps.googleapis.com/maps/api/geocode/json?";
    private static String apiKey = setGoogleGeoCodingKey();
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
            String json;
            json = getJSON(getStadium(homeTeam));
            String lat = getLat(json);
            String lng = getLng(json);
            System.out.printf("%s Home: %s vs. %s %s-%s Stadium: %s isDome: %s lat:%s long:%s",
                    getEpochTime(nextLine[2],nextLine[3]),
                     homeTeam,
                     awayTeam,
                     homePoints,
                     awayPoints,
                     getStadium(homeTeam),
                     getIsStadiumDome(homeTeam),
                     lat,
                     lng);

        }


    }

    private static String getLng(String json) {
        return JsonPath.read(json, "$.results.[0].geometry.location.lng").toString();
    }

    private static String getLat(String json) {
        return JsonPath.read(json, "$.results.[0].geometry.location.lat").toString();
    }

    private static long getEpochTime(String date, String time)
    {
        String dateAndTime = date + " 2018 " + time;
        LocalDateTime ldt = LocalDateTime.parse(dateAndTime, TIME_FORMATTER);
        return ldt.toEpochSecond(EASTERN_TIME_OFFSET);
    }

    public static String getStadium(String homeTeam)
    {
        return CityState.valueOf(homeTeam.replace(" ", "_")
                        .toUpperCase()).getStadium();
    }

    private static Boolean getIsStadiumDome(String homeTeam){
        return CityState.valueOf(homeTeam.replace(" ", "_")
                .toUpperCase()).getIsDome();
    }

    private static String getJSON(String lat, String longitude)
    {
        return null;
    }
    private static String getJSON(String stadium) throws IOException {
        String jsonGoogleGeo;
        URL url = new URL (String.format("%saddress=%s&key=%s", GOOGLE_GEOCODE_URL, URLEncoder.encode(stadium, "UTF-8"),
                URLEncoder.encode(apiKey, "UTF-8")));
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        System.out.println(connection.getURL());
        int responseCode = connection.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print result
            jsonGoogleGeo = response.toString();

        } else {
            System.out.println("GET request not worked");
            return null;
        }
        return jsonGoogleGeo;
    }

    private static String setGoogleGeoCodingKey()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Please provide the Google GeoCoding API key");
        return in.nextLine();
    }

}
