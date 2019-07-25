package org.codelouisville.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import org.codelouisville.Models.Game;

//Controller for temperaturebar scene to show average points scored by temperature range.
public class TemperatureBarController extends BaseChartController {
    //Fields
    @FXML
    private BarChart<String, Double> temperatureBarChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    //ActionEvent Methods to load and display the average points scored by temperature range.
    //Each method uses helper methods.
    @Override
    @FXML
    void loadHomeData(ActionEvent event){
        checkForData("Home Team");
        temperatureBarChart.getData().add(getChartData("Home Team"));
    }

    @Override
    @FXML
    void loadAwayData(ActionEvent event){
        checkForData("Away Team");
        temperatureBarChart.getData().add(getChartData("Away Team"));
    }

    //Loads the average of the total points scored by temperature range.
    @Override
    @FXML
    void loadCombinedData(ActionEvent event){
        checkForData("Total Game");
        temperatureBarChart.getData().add(getChartData("Total Game"));
    }

    @Override
    @FXML
    void clearChart(ActionEvent event){
        temperatureBarChart.getData().clear();
    }

    //Helper Methods
    //Method checks to see if there is already any data present for the series name (e.g. Home Team) when an ActionEvent
    //to load data to the chart is triggered as multiple clicks will load duplicate data into the temperatureBarChart.
    //If the series name exists, it will remove that data from the temperatureBarChart.
    @Override
    void checkForData(String seriesName) {
        XYChart.Series seriesToRemove = null;
        for(XYChart.Series series : temperatureBarChart.getData()) {

            if (series.getName().equals(seriesName)) {
                seriesToRemove = series;
            }
        }
        temperatureBarChart.getData().remove(seriesToRemove);

    }

    //Method to add the average of each temperature range to the XYChart Series that is added to the temperatureBarChart
    //Uses the two following helper methods to calculate the averages. Each range is incremented 10 degrees F and the first
    //range begins at 20 degrees F and ends at 100 degrees F.
    @Override
    XYChart.Series<String,Double> getChartData(String awayHomeTotal){
        XYChart.Series<String, Double> averages = new XYChart.Series<>();
            int endOfRange;
            if (awayHomeTotal.equals("Home Team")) {
                for(int i = 20; i <100; i+=10) {
                    endOfRange = i + 10;
                    averages.setName(awayHomeTotal);
                    averages.getData().add(new XYChart.Data<>(
                            "Overall Average",
                            getOverallHomeAverage()
                    ));
                    averages.getData().add(new XYChart.Data<>(
                            String.format("%d-%d", i, endOfRange),
                            getAverageHome(i, endOfRange)
                    ));
                }
            } else if(awayHomeTotal.equals("Away Team")){
                for(int i = 20; i <100; i+=10) {
                    endOfRange = i + 10;
                    averages.setName(awayHomeTotal);
                    averages.getData().add(new XYChart.Data<>(
                            "Overall Average",
                            getOverallAwayAverage()
                    ));
                    averages.getData().add(new XYChart.Data<>(
                            String.format("%d-%d", i, endOfRange),
                            getAverageAway(i, endOfRange)
                    ));
                }
            } else if(awayHomeTotal.equals("Total Game")) {
                for(int i = 20; i <100; i+=10) {
                    endOfRange = i + 10;
                    averages.setName(awayHomeTotal);
                    averages.getData().add(new XYChart.Data<>(
                            "Overall Average",
                            getOverallAwayAverage()+ getOverallHomeAverage()
                    ));
                    averages.getData().add(new XYChart.Data<>(
                            String.format("%d-%d", i, endOfRange),
                            getAverageAway(i, endOfRange) + getAverageHome(i, endOfRange)
                    ));
                }
            }
            return averages;
        }

    //Helper method to calculate the average for each temperature range for the home team.
    private Double getAverageHome(int i, int endOfRange) {
        return games.stream()
                .filter(g -> g.getTemperature() > i && g.getTemperature() < endOfRange)
                .mapToDouble(Game::getHomeScore)
                .average()
                .orElse(0.0);
    }

    //Helper method to calculate the average for each temperature range for the away team.
    private Double getAverageAway(int i, int endOfRange) {
        return games.stream()
                .filter(g -> g.getTemperature() > i && g.getTemperature() < endOfRange)
                .mapToDouble(Game::getAwayScore)
                .average()
                .orElse(0.0);
    }
}

