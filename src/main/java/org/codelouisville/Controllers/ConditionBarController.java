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
public class ConditionBarController extends  BaseChartController {
    @FXML
    private BarChart<String, Double> conditionBarChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;


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

    @Override
    @FXML
    void loadCombinedData(ActionEvent event) {
        checkForData("Total Game");
        conditionBarChart.getData().add(getChartData("Total Game"));
    }

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

    @Override
    @FXML
    void clearChart(ActionEvent event) {
        conditionBarChart.getData().clear();
    }

    @Override
    XYChart.Series<String, Double> getChartData(String awayHomeTotal) {
        List<String> categories = Arrays.asList("Dome", "No Precipitation", "Precipitation");
        XYChart.Series<String, Double> averages = new XYChart.Series<>();
        if (awayHomeTotal.equals("Home Team")) {
            averages.setName(awayHomeTotal);
            averages.getData().add(new XYChart.Data<>(
                    "Overall Average",
                    getAverageHome("Overall Average")
            ));
            for(String category: categories) {
                averages.getData().add(new XYChart.Data<>(
                        category,
                        getAverageHome(category)
                ));
            }
        } else if (awayHomeTotal.equals("Away Team")){
            averages.setName(awayHomeTotal);
            averages.getData().add(new XYChart.Data<>(
                    "Overall Average",
                    getAverageAway("Overall Average")
            ));
            for(String category: categories) {
                averages.getData().add(new XYChart.Data<>(
                        category,
                        getAverageAway(category)
                ));
            }
        } else if (awayHomeTotal.equals("Total Game")){
                averages.setName(awayHomeTotal);
            averages.getData().add(new XYChart.Data<>(
                    "Overall Average",
                    getAverageAway("Overall Average") +getAverageHome("Overall Average")
            ));
            for(String category: categories) {
                averages.getData().add(new XYChart.Data<>(
                        category,
                        getAverageAway(category) + getAverageHome(category)
                ));
            }
        }
        return averages;
    }


    private Double getAverageHome(String category) {
        double average =0.0;
        if(category.equals("Overall Average"))
        {
            average = games.stream()
                    .mapToDouble(Game::getHomeScore)
                    .average()
                    .orElse(0.0);
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

    private Double getAverageAway(String category) {
        double average =0.0;
        if(category.equals("Overall Average"))
        {
            average = games.stream()
                    .mapToDouble(Game::getAwayScore)
                    .average()
                    .orElse(0.0);
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