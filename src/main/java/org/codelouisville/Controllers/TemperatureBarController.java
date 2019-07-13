package org.codelouisville.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import org.codelouisville.Models.Game;

@SuppressWarnings("WeakerAccess")
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
        temperatureBarChart.getData().add(getChartData("Home Team"));
    }

    @Override
    @FXML
    void loadAwayData(ActionEvent event){
        checkForData("Away Team");
        temperatureBarChart.getData().add(getChartData("Away Team"));
    }

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
    XYChart.Series<String,Double> getChartData(String awayHomeTotal){
        XYChart.Series<String, Double> averages = new XYChart.Series<>();
            int endOfRange;
            if (awayHomeTotal.equals("Home Team")) {
                for(int i = 20; i <100; i+=10) {
                    endOfRange = i + 10;
                    averages.setName(awayHomeTotal);
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
                            String.format("%d-%d", i, endOfRange),
                            getAverageAway(i, endOfRange)
                    ));
                }
            } else if(awayHomeTotal.equals("Total Game")) {
                for(int i = 20; i <100; i+=10) {
                    endOfRange = i + 10;
                    averages.setName(awayHomeTotal);
                    averages.getData().add(new XYChart.Data<>(
                            String.format("%d-%d", i, endOfRange),
                            getAverageAway(i, endOfRange) + getAverageHome(i, endOfRange)
                    ));
                }
            }
            return averages;
        }


    private Double getAverageHome(int i, int endOfRange) {
        return games.stream()
                .filter(g -> g.getTemperature() > i && g.getTemperature() < endOfRange)
                .mapToDouble(Game::getHomeScore)
                .average()
                .orElse(0.0);
    }

    private Double getAverageAway(int i, int endOfRange) {
        return games.stream()
                .filter(g -> g.getTemperature() > i && g.getTemperature() < endOfRange)
                .mapToDouble(Game::getAwayScore)
                .average()
                .orElse(0.0);
    }
}

