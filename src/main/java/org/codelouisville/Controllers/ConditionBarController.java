package org.codelouisville.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.codelouisville.Models.Game;

import java.util.*;

@SuppressWarnings("WeakerAccess")
//Controller for conditionbar.fxml page to show average points scored by condition.
public class ConditionBarController extends  BaseChartController {
    //Fields
    @FXML
    private BarChart<String, Double> conditionBarChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;


    //ActionEvent Methods to load and display data in the conditionBarChart. Each method uses helper methods.
    @Override
    @FXML
    void loadHomeData(ActionEvent event) {
        checkForData("Home Team");
        conditionBarChart.getData().add(getChartData("Home Team"));
    }

    @Override
    @FXML
    void loadAwayData(ActionEvent event) {
        checkForData("Away Team");
        conditionBarChart.getData().add(getChartData("Away Team"));
    }

    //Loads the average of the average home team score and average away team score for the average of a game(home and
    //teams.)
    @Override
    @FXML
    void loadCombinedData(ActionEvent event) {
        checkForData("Total Game");
        conditionBarChart.getData().add(getChartData("Total Game"));
    }

    @Override
    @FXML
    void clearChart(ActionEvent event) {
        conditionBarChart.getData().clear();
    }

    //HelperMethods
    //Method checks to see if there is already any data present for the series name (e.g. Home Team) when one of the
    //buttons to load data to the chart is clicked as multiple clicks will load duplicate data into the conditionBarChart.
    //If the series name exists, it will remove that data from the conditionBarChart.
    @Override
    void checkForData(String seriesName) {
        XYChart.Series seriesToRemove = null;
        for(XYChart.Series series : conditionBarChart.getData()) {

            if (series.getName().equals(seriesName)) {
                seriesToRemove = series;
            }
        }
        conditionBarChart.getData().remove(seriesToRemove);
    }

    //Helper method to obtain the average score for 3 categories of game weather conditions plus the overall average and
    //adds them to an XYChart Series that is added to the conditionBarChart.
    @Override
    XYChart.Series<String, Double> getChartData(String awayHomeTotal) {
        //List of conditions through which to loop
        List<String> categories = Arrays.asList("Overall Average", "Dome", "No Precipitation", "Precipitation");
        XYChart.Series<String, Double> averages = new XYChart.Series<>();
        if (awayHomeTotal.equals("Home Team")) {
            averages.setName(awayHomeTotal);
            for(String category: categories) {
                averages.getData().add(new XYChart.Data<>(
                        category,
                        getAverageHome(category)
                ));
            }
        } else if (awayHomeTotal.equals("Away Team")){
            averages.setName(awayHomeTotal);
            for(String category: categories) {
                averages.getData().add(new XYChart.Data<>(
                        category,
                        getAverageAway(category)
                ));
            }
        } else if (awayHomeTotal.equals("Total Game")){
            averages.setName(awayHomeTotal);
            for(String category: categories) {
                averages.getData().add(new XYChart.Data<>(
                        category,
                        getAverageAway(category) + getAverageHome(category)
                ));
            }
        }
        return averages;
    }

    //Helper method for getChart data to get the average for each category in the categories List for home teams.
    private Double getAverageHome(String category) {
        double average =0.0;
        if(category.equals("Overall Average"))
        {
            average = getOverallHomeAverage();
        }
        else if (category.equals("Dome")) {
            average = games.stream()
                    .filter(Game::getDomeStadium)
                    .mapToDouble(Game::getHomeScore)
                    .average()
                    .orElse(0.0);
        }
        else if(category.equals("No Precipitation")){
            average = games.stream()
                    .filter(g -> !g.getDomeStadium())
                    .filter(g -> !g.getWeatherCondition().contains("Rain"))
                    .filter(g-> !g.getWeatherCondition().contains("Drizzle"))
                    .mapToDouble(Game::getHomeScore)
                    .average()
                    .orElse(0.0);
        } else if(category.equals("Precipitation")) {
            average = games.stream()
                    .filter(g -> g.getWeatherCondition().contains("Rain") || g.getWeatherCondition().contains("Drizzle"))
                    .mapToDouble(Game::getHomeScore)
                    .average()
                    .orElse(0.0);
        }
        return average;
    }

    //Helper method for getChart data to get the average for each category in the categories List for away teams.
    private Double getAverageAway(String category) {
        double average =0.0;
        if(category.equals("Overall Average"))
        {
            average = getOverallAwayAverage();
        }
        else if (category.equals("Dome")) {
            average = games.stream()
                    .filter(Game::getDomeStadium)
                    .mapToDouble(Game::getAwayScore)
                    .average()
                    .orElse(0.0);
        } else if(category.equals("No Precipitation")){
            average = games.stream()
                    .filter(g -> !g.getDomeStadium())
                    .filter(g -> !g.getWeatherCondition().contains("Rain"))
                    .filter(g-> !g.getWeatherCondition().contains("Drizzle"))
                    .mapToDouble(Game::getAwayScore)
                    .average()
                    .orElse(0.0);
        } else if(category.equals("Precipitation")) {
            average = games.stream()
                    .filter(g -> g.getWeatherCondition().contains("Rain") || g.getWeatherCondition().contains("Drizzle"))
                    .mapToDouble(Game::getAwayScore)
                    .average()
                    .orElse(0.0);
        }
        return average;
    }




}