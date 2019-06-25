package org.codelouisville.data;

import com.jayway.jsonpath.Configuration;
import org.codelouisville.data.Models.CityState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import com.jayway.jsonpath.JsonPath;

public class Main {
    private static final String googleGeoCode = "https://maps.googleapis.com/maps/api/geocode/json?";
    private static final String API_KEY = "AIzaSyBKLad3JDGzSBerzvPZwoZwcy0pN0R-l0k";

    public static String getState(String teamName) {
        return CityState.valueOf(teamName).getState();
    }
    public static String getJSON(String stadium) throws IOException {
        String a;
        URL url = new URL (String.format("%saddress=%s&key=%s", googleGeoCode, URLEncoder.encode(stadium, "UTF-8"),
                URLEncoder.encode(API_KEY, "UTF-8")));
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
            a = response.toString();

        } else {
            System.out.println("GET request not worked");
            return null;
        }
        return a;
    }
    public static void main(String[] args) {
        System.out.printf("%s %n", getState("NEW_ENGLAND_PATRIOTS"));


        String a ="";
        try {
            a=getJSON("Commonwealth Stadium");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String something = JsonPath.read(a, "$.results.[0].geometry.location.lat").toString();

        System.out.println(something);


    }
}




