package org.codelouisville.data;

import com.jayway.jsonpath.Configuration;
import org.codelouisville.data.Logic.ReadCsv;
import org.codelouisville.data.Models.CityState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import com.jayway.jsonpath.JsonPath;

public class Main {
    private static final String darkSkyAPIUrl = "https://maps.googleapis.com/maps/api/geocode/json?";

    public static void main(String[] args) {
        System.out.printf("%s %n", ReadCsv.getStadium("NEW_ENGLAND_PATRIOTS"));
/*

        try {
            ReadCsv.readingCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/


/*        String lat = JsonPath.read(a, "$.results.[0].geometry.location.lat").toString();
        String lng = JsonPath.read(a, "$.results.[0].geometry.location.lng").toString();

        System.out.println(lat+ " "+ lng);*/


    }
}




