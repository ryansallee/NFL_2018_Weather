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

public class ConditionTemperatureBarController extends BaseChartController {
    @FXML
    private BarChart<String, Double> conditionTemperatureBarChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    @FXML
    @Override
    void loadHomeData(ActionEvent event) {
        conditionTemperatureBarChart.getData().add(getChartData("Home Team"));
    }
    @FXML
    @Override
    void loadAwayData(ActionEvent event) {

    }
    @FXML
    @Override
    void loadCombinedData(ActionEvent event) {

    }

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

    @FXML
    @Override
    void clearChart(ActionEvent event) {
        conditionTemperatureBarChart.getData().clear();
    }

    @Override
    XYChart.Series getChartData(String awayHomeTotal) {
        XYChart.Series<String, Double> averages = new XYChart.Series<>();
        List<String> categories = Arrays.asList("Dome", "No Precipitation", "Precipitation");
        int endOfRange;
        if(awayHomeTotal.equals("Home Team")){
            for(int i = 20; i <100; i+=10){
                endOfRange = i + 10;
                averages.setName(awayHomeTotal);
                for(String category: categories)
                {
                    if(category.equals("Dome")){
                        averages.getData().add(new XYChart.Data<>(
                                category,
                                getAverageHome(category, 70, 80)
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
        return averages;
    }

    private Double getAverageHome(String category, int i, int endOfRange) {
        Double average = 1.0;
        if (category.equals("Dome")){
            average = games.stream()
                    .filter(Game::getDomeStadium)
                    .mapToDouble(Game::getHomeScore)
                    .average()
                    .orElse(0.0);
        }
        return average;
    }


}
