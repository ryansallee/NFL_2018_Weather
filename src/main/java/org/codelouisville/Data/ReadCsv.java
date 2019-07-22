package org.codelouisville.Data;

import com.jayway.jsonpath.JsonPath;
import org.codelouisville.Models.Game;
import org.codelouisville.Models.Ref;
import org.codelouisville.Models.Stadium;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import tk.plogitech.darksky.forecast.*;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;


//Class to Read the 2018_nfl_results CSV into a list of games.
public class ReadCsv {
    //Constants
    //Times in the CSV are formatted in month-day, time format across two columns.
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("MMMM d yyyy h:mma");
    //All times in the CSV are given in Eastern Time which is a -5 hour offset of GMT.
    private static final ZoneOffset EASTERN_TIME_OFFSET = ZoneOffset.ofHours(-5);
    private static final File CSV_2018_NFL_RESULTS =
            new File("src/main/resources/org/codelouisville/csv/2018_nfl_results.csv");
    private static final String GOOGLE_GEOCODE_URL = "https://maps.googleapis.com/maps/api/geocode/json?";
    //Fields
    private static String googleGeoCodingApiKey;
    private static String darkSkyAPIKey;


//Reads Each CSV line in to a array of Strings, passes it through helper methods and returns a list of games.
    public static List<Game> readingCSV(Query query) throws IOException {
        List<Game> games = new ArrayList<>();
        if(query.checkDb() >= 1){
            games = null;
        } else {
            Scanner in = new Scanner(System.in);
            setGoogleGeoCodingKey(in);
            setDarkSkyAPIKey(in);
            in.close();
            BufferedReader br = Files.newBufferedReader(CSV_2018_NFL_RESULTS.toPath());
            CSVReader reader = new CSVReader(br);
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                //Ref<> Objects are instantiated to simulate passing arguments by reference in the following
                //helper methods as method arguments are passed by value. These objects are manipulated in
                //the proceeding helper methods so that the needed values can be returned in the Game() arguments.
                Ref<String> homeTeam = new Ref<>(null);
                Ref<String> awayTeam = new Ref<>(null);
                Ref<Integer> homePoints = new Ref<>(null);
                Ref<Integer> awayPoints = new Ref<>(null);
                Ref<String> stadiumName = new Ref<>(null);
                Ref<Boolean> isStadiumDome = new Ref<>(false);
                //Each of the methods below are helper methods to organize and transform the CSV line to provide the appropriate values to the
                //Game constructor
                setTeamAndPoints(homeTeam, awayTeam, homePoints, awayPoints, nextLine);
                getStadiumAndIsDome(homeTeam, awayTeam, stadiumName, isStadiumDome);
                String jsonGoogleGeocode = getGoogleGeocodeJSON(stadiumName.getVal());
                Double latitude = getLatitude(jsonGoogleGeocode);
                Double longitude = getLongitude(jsonGoogleGeocode);
                String jsonDarkSky = getJSONDarkSky(getEpochTime(nextLine[2], nextLine[3]), latitude, longitude, isStadiumDome.getVal());
                String weatherCondition = getWeatherCondition(jsonDarkSky);
                Double temperature = getTemperature(jsonDarkSky);
                Game g = new Game(homeTeam.getVal(), awayTeam.getVal(),
                        stadiumName.getVal(), isStadiumDome.getVal(),
                        homePoints.getVal(), awayPoints.getVal(),
                        temperature, weatherCondition);
                games.add(g);
            }
            reader.close();
            System.out.println("Games added");
        }
        return games;
    }

    //Helper Methods
    private static void setGoogleGeoCodingKey(Scanner in)
    {
        System.out.println("Please provide the Google GeoCoding API key");
        googleGeoCodingApiKey = in.nextLine();

    }
    private static void setDarkSkyAPIKey(Scanner in) {
        System.out.println("Please provide the DarkSky GeoCoding API key");
        darkSkyAPIKey = in.nextLine();
            }

    private static void setTeamAndPoints(Ref<String> homeTeam, Ref<String> awayTeam,
                                      Ref<Integer> homePoints, Ref<Integer> awayPoints,
                                      String[] nextLine) {
        //The character "@" is used in the condition as the CSV that contains the results of 2018's NFL games is deliniated
        //by who won the game and not home and away. The 6th column in the chart indicates that the team in the 7th column
        //is the home team.
        if(nextLine[5].equals("@")) {
            homeTeam.setVal(nextLine[6]);
            awayTeam.setVal(nextLine[4]);
            homePoints.setVal(Integer.valueOf(nextLine[9]));
            awayPoints.setVal(Integer.valueOf(nextLine[8]));
        }
        else{
            homeTeam.setVal(nextLine[4]);
            awayTeam.setVal(nextLine[6]);
            homePoints.setVal(Integer.valueOf(nextLine[8]));
            awayPoints.setVal(Integer.valueOf(nextLine[9]));
        }
    }

    //Method uses the Stadium enum to obtain the the stadium name a game was played in and if it is a dome and then
    //transforms the stadiumName and isStadiumDome objects that are passed as arguments.
    private static void getStadiumAndIsDome(Ref<String> homeTeam, Ref<String> awayTeam, Ref<String> stadiumName, Ref<Boolean> isStadiumDome)
    {
        //Three NFL games were played in England in 2018 and these games are accounted for in the first two conditions.
        if(homeTeam.getVal().equals("Oakland Raiders") && awayTeam.getVal().equals("Seattle Seahawks"))
        {
            stadiumName.setVal("Tottenham Stadium");
            isStadiumDome.setVal(false);
        } else if ((homeTeam.getVal().equals("Los Angeles Chargers") && awayTeam.getVal().equals("Tennessee Titans") )||
                (homeTeam.getVal().equals("Jacksonville Jaguars") && awayTeam.getVal().equals("Philadelphia Eagles") ))
        {
            stadiumName.setVal("Wembley Stadium");
            isStadiumDome.setVal(false);
        } else {
            stadiumName.setVal(Stadium.valueOf(homeTeam.getVal().replace(" ", "_")
                    .toUpperCase()).getStadium());
            isStadiumDome.setVal(Stadium.valueOf(homeTeam.getVal().replace(" ", "_")
                    .toUpperCase()).getIsDome());
        }
    }

    //Method uses Java's HttpURLConnection class to get geocoding data in JSON format based only on the stadium name
    private static String getGoogleGeocodeJSON(String stadium) throws IOException {
        String jsonGoogleGeo;
        URL url = new URL (String.format("%saddress=%s&key=%s", GOOGLE_GEOCODE_URL, URLEncoder.encode(stadium, StandardCharsets.UTF_8),
                URLEncoder.encode(googleGeoCodingApiKey, StandardCharsets.UTF_8)));
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            in.close();
            jsonGoogleGeo = response.toString();

        } else {
            System.out.println("GET request not worked");
            return null;
        }
        return jsonGoogleGeo;
    }

    //Since DarkSky needs Longitude data, the longitude for the the stadium is retrieved from the JSON response from Google.
    private static Double getLongitude(String jsonGoogleGeocode) {
        return Double.valueOf(JsonPath.read(jsonGoogleGeocode, "$.results.[0].geometry.location.lng").toString());
    }

    //Since DarkSky needs Longitude data, the longitude for the the stadium is retrieved from the JSON response from Google.
    private static Double getLatitude(String jsonGoogleGeocode) {
        return JsonPath.read(jsonGoogleGeocode, "$.results.[0].geometry.location.lat");
    }

    //Since DarkSky requires Unix time for a request, the two columns containing the date(3rd column) and time(4th column)
    //are transformed to produce Unix time.
    private static long getEpochTime(String date, String time)
    {
        String dateAndTime = date + " 2018 " + time;
        LocalDateTime ldt = LocalDateTime.parse(dateAndTime, TIME_FORMATTER);
        return ldt.toEpochSecond(EASTERN_TIME_OFFSET);
    }

    //A library to specifically request a JSON response from Dark Sky, Dark-Sky Forecast API,  was used as it was available.
    private static String getJSONDarkSky(long epochTime, Double latitude, Double longitude, Boolean isDome){
        //If the game was played in a Dome it is unnecessary to obtain the outdoor weather conditions. Otherwise,
        //the JSON containing weather data for that game is requested.
        if(!isDome) {
            String forecast;
            ForecastRequest request = new ForecastRequestBuilder()
                    .key(new APIKey(darkSkyAPIKey))
                    .time(Instant.ofEpochSecond(epochTime))
                    .language(ForecastRequestBuilder.Language.en)
                    .units(ForecastRequestBuilder.Units.us)
                    .exclude(ForecastRequestBuilder.Block.hourly,
                            ForecastRequestBuilder.Block.daily)
                    .location(new GeoCoordinates(new Longitude(longitude), new Latitude(latitude)))
                    .build();

            DarkSkyClient client = new DarkSkyClient();

            try {
                forecast = client.forecastJsonString(request);
            } catch (ForecastException e) {
                e.printStackTrace();
                forecast = e.getMessage();
            }
            return forecast;
        }
        else{
            return null;
        }
    }

    //Gets weather condition data using the Dark-Sky Forecast API library. If null is passed, the game was played inside
    //a dome.
    private static String getWeatherCondition(String jsonDarkSky){
        if(jsonDarkSky != null) {
            return JsonPath.read(jsonDarkSky, "$.currently.summary").toString();
        } else{
            return "Dome";
        }
    }
    //Gets temperature data using the Dark-Sky Forecast API library. If null is passed, the game was played inside
    // a dome.
    private static Double getTemperature(String jsonDarkSky)  {
        if(jsonDarkSky != null) {
            return Double.valueOf(JsonPath.read(jsonDarkSky, "$.currently.temperature").toString());
        }
        else return 72.0;
    }

}
