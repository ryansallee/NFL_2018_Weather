package org.codelouisville.data;

import com.jayway.jsonpath.JsonPath;
import org.codelouisville.data.Logic.ReadCsv;
import org.codelouisville.data.Models.Game;
import tk.plogitech.darksky.forecast.*;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        //System.out.printf("%s %n", ReadCsv.getStadium("NEW_ENGLAND_PATRIOTS"));
        List<Game> games = null;
        try {
            games = ReadCsv.readingCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(games.get(20).toString());

    }
}




