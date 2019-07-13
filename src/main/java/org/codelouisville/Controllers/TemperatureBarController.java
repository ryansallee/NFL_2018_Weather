package org.codelouisville.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import org.codelouisville.Models.Game;

import java.util.OptionalDouble;

public class TemperatureBarController extends BaseChartController {

    @FXML
    private BarChart<String, Double> temperatureBarChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    @Override
    @FXML
    void loadHomeData(ActionEvent event){
        checkForData("Home Team");
        temperatureBarChart.getData().add(getChartData("home"));
    }

    @Override
    @FXML
    void loadAwayData(ActionEvent event){
        checkForData("Away Team");
        temperatureBarChart.getData().add(getChartData("away"));
    }

    @Override
    @FXML
    void loadCombinedData(ActionEvent event){
        checkForData("Total Game");
        temperatureBarChart.getData().add(getChartData("total"));
    }

    @FXML
    private void clearChart(ActionEvent event){
        clear(temperatureBarChart);
    }

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

    @Override
    XYChart.Series<String,Double> getChartData(String awayHomeBoth){
        XYChart.Series<String, Double> averages = new XYChart.Series<>();
            int endOfRange;
            if (awayHomeBoth.equals("home")) {
                for(int i = 20; i <100; i+=10) {
                    endOfRange = i + 10;
                    averages.setName("Home Team");
                    averages.getData().add(new XYChart.Data<>(
                            String.format("%d-%d", i, endOfRange),
                            getADoubleHome(i, endOfRange)
                    ));
                }
            } else if(awayHomeBoth.equals("away")){
                for(int i = 20; i <100; i+=10) {
                    endOfRange = i + 10;
                    averages.setName("Away Team");
                    averages.getData().add(new XYChart.Data<>(
                            String.format("%d-%d", i, endOfRange),
                            getADoubleAway(i, endOfRange)
                    ));
                }
            } else if(awayHomeBoth.equals("total")) {
                for(int i = 20; i <100; i+=10) {
                    endOfRange = i + 10;
                    averages.setName("Total Game");
                    averages.getData().add(new XYChart.Data<>(
                            String.format("%d-%d", i, endOfRange),
                            getADoubleAway(i, endOfRange) + getADoubleHome(i, endOfRange)
                    ));
                }
            }
            return averages;
        }


    private Double getADoubleHome(int i, int endOfRange) {
        OptionalDouble optionalAverage  = games.stream()
                .filter(g -> g.getTemperature() > i && g.getTemperature() < endOfRange)
                .mapToDouble(Game::getHomeScore)
                .average();
        if(optionalAverage.isPresent())
        {
             return optionalAverage.getAsDouble();
        } else{
            return 0.0;
        }
    }

    private Double getADoubleAway(int i, int endOfRange) {
        OptionalDouble optionalAverage  = games.stream()
                .filter(g -> g.getTemperature() > i && g.getTemperature() < endOfRange)
                .mapToDouble(Game::getAwayScore)
                .average();
        if(optionalAverage.isPresent())
        {
            return optionalAverage.getAsDouble();
        }else {
            return 0.0;
        }
    }
}

