package org.codelouisville.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.codelouisville.Models.Game;

import java.util.Arrays;
import java.util.List;

//Controller for conditiontemperaturebarchart scene to show average points scored by condition.
public class ConditionTemperatureBarController extends BaseChartController {
    //Fields
    @FXML
    private BarChart<String, Double> conditionTemperatureBarChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    //ActionEvent Methods to load and display data in the conditionTemperatureBarChart. Each method uses helper methods.
    @FXML
    @Override
    void loadHomeData(ActionEvent event) {
        checkForData("Home Team");
        conditionTemperatureBarChart.getData().add(getChartData("Home Team"));
    }
    @FXML
    @Override
    void loadAwayData(ActionEvent event) {
        checkForData("Away Team");
        conditionTemperatureBarChart.getData().add(getChartData("Away Team"));
    }

    //Loads the average of the average home team score and average away team score for the average of a game(home and
    //teams.)
    @FXML
    @Override
    void loadCombinedData(ActionEvent event) {
        checkForData("Total Game");
        conditionTemperatureBarChart.getData().add(getChartData("Total Game"));
    }

    @FXML
    @Override
    void clearChart(ActionEvent event) {
        conditionTemperatureBarChart.getData().clear();
    }

    //Helper Methods
    //Method checks to see if there is already any data present for the series name (e.g. Home Team) when an ActionEvent
    //to load data to the chart is triggered as multiple clicks will load duplicate data into the conditionTemperatureBarChart.
    //If the series name exists, it will remove that data from the conditionBarChart.
    @Override
    void checkForData(String seriesName) {
        XYChart.Series seriesToRemove = null;
        for(XYChart.Series series : conditionTemperatureBarChart.getData()) {

            if (series.getName().equals(seriesName)) {
                seriesToRemove = series;
            }
        }
        conditionTemperatureBarChart.getData().remove(seriesToRemove);
    }

    //Helper method to obtain the average score for 3 categories of game weather conditions plus the overall average and
    //adds them to an XYChart Series that is added to the conditionTemperatureBarChart. As well, it loops over temperature
    //ranges incremented by 10 degrees starting from 20 degrees F and ending at 100 degrees F.
    @Override
    XYChart.Series<String, Double> getChartData(String awayHomeTotal) {
        XYChart.Series<String, Double> averages = new XYChart.Series<>();
        List<String> categories = Arrays.asList("Overall Average", "Dome", "No Precip", "Precip");
        int endOfRange;
        if(awayHomeTotal.equals("Home Team")){
            for(int i = 20; i <100; i+=10){
                endOfRange = i + 10;
                averages.setName(awayHomeTotal);
                for(String category: categories)
                {
                    //Since all Dome games are played at essentially the same temperature and overall average is for all
                    //temperatures, temperature does not need to be accounted for.
                    if(category.equals("Dome") ||
                        category.equals("Overall Average")){
                        averages.getData().add(new XYChart.Data<>(
                                category,
                                getAverageHome(category, i, endOfRange)
                        ));
                    } else {
                        averages.getData().add(new XYChart.Data<>(
                                String.format("%d-%d %s", i, endOfRange, category),
                                getAverageHome(category, i, endOfRange)
                        ));
                    }
                }
            }
        }
        if(awayHomeTotal.equals("Away Team")){
            for(int i = 20; i <100; i+=10){
                endOfRange = i + 10;
                averages.setName(awayHomeTotal);
                for(String category: categories)
                {
                    //Since all Dome games are played at essentially the same temperature and overall average is for all
                    //temperatures, temperature does not need to be accounted for.
                    if(category.equals("Dome")  ||
                        category.equals("Overall Average")){
                        averages.getData().add(new XYChart.Data<>(
                                category,
                                getAverageAway(category, i, endOfRange)
                        ));
                    } else {
                        averages.getData().add(new XYChart.Data<>(
                                String.format("%d-%d %s", i, endOfRange, category),
                                getAverageAway(category, i, endOfRange)
                        ));
                    }
                }
            }
        }
        if(awayHomeTotal.equals("Total Game")){
            for(int i = 20; i <100; i+=10){
                endOfRange = i + 10;
                averages.setName(awayHomeTotal);
                for(String category: categories)
                {
                    //Since all Dome games are played at essentially the same temperature and overall average is for all
                    //temperatures, temperature does not need to be accounted for.
                    if(category.equals("Dome") ||
                        category.equals("Overall Average")){
                        averages.getData().add(new XYChart.Data<>(
                                category,
                                getAverageAway(category, i, endOfRange) +
                                        getAverageHome(category, i, endOfRange)
                        ));
                    } else {
                        averages.getData().add(new XYChart.Data<>(
                                String.format("%d-%d %s", i, endOfRange, category),
                                getAverageAway(category, i, endOfRange) +
                                        getAverageHome(category, i, endOfRange)
                        ));
                    }
                }
            }
        }
        return averages;
    }

    //Helper method for getChart data to calculate the average for each weather condition category within each incrementation
    //of temperature for home teams.
    private Double getAverageHome(String category, int i, int endOfRange) {
        double average = 0.0;
        //Temperature does not need to be accounted for the overall average and games played within a dome.
        if(category.equals("Overall Average"))
        {
            average = getOverallHomeAverage();
        }
        else if (category.equals("Dome")){
            average = games.stream()
                    .filter(Game::getDomeStadium)
                    .mapToDouble(Game::getHomeScore)
                    .average()
                    .orElse(0.0);
        } else if(category.equals("No Precip")){
            average = games.stream()
                    .filter(g -> g.getTemperature() > i && g.getTemperature() < endOfRange)
                    .filter(g -> !g.getDomeStadium())
                    .filter(g -> !g.getWeatherCondition().contains("Rain"))
                    .filter(g -> !g.getWeatherCondition().contains("Drizzle"))
                    .mapToDouble(Game::getHomeScore)
                    .average()
                    .orElse(0.0);
        } else if(category.equals("Precip")) {
            average = games.stream()
                    .filter(g -> g.getTemperature() > i && g.getTemperature() < endOfRange)
                    .filter(g -> g.getWeatherCondition().contains("Rain") || g.getWeatherCondition().contains("Drizzle"))
                    .mapToDouble(Game::getHomeScore)
                    .average()
                    .orElse(0.0);
        }
        return average;
    }

    //Helper method for getChart data to calculate the average for each weather condition category within each incrementation
    //of temperature for away teams.
    private Double getAverageAway(String category, int i, int endOfRange) {
        double average = 0.0;
        //Temperature does not need to be accounted for the overall average and games played within a dome.
        if(category.equals("Overall Average"))
        {
            average = getOverallAwayAverage();
        }
        else if (category.equals("Dome")){
            average = games.stream()
                    .filter(Game::getDomeStadium)
                    .mapToDouble(Game::getAwayScore)
                    .average()
                    .orElse(0.0);
        } else if(category.equals("No Precip")){
            average = games.stream()
                    .filter(g -> g.getTemperature() > i && g.getTemperature() < endOfRange)
                    .filter(g -> !g.getDomeStadium())
                    .filter(g -> !g.getWeatherCondition().contains("Rain"))
                    .filter(g-> !g.getWeatherCondition().contains("Drizzle"))
                    .mapToDouble(Game::getAwayScore)
                    .average()
                    .orElse(0.0);
        } else if(category.equals("Precip")) {
            average = games.stream()
                    .filter(g -> g.getTemperature() > i && g.getTemperature() < endOfRange)
                    .filter(g -> g.getWeatherCondition().contains("Rain") || g.getWeatherCondition().contains("Drizzle"))
                    .mapToDouble(Game::getAwayScore)
                    .average()
                    .orElse(0.0);
        }
        return average;
    }

}
