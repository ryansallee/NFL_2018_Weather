package org.codelouisville.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class ScatterController extends BaseChartController {

    @FXML
    private ScatterChart<Number, Number> temperatureScatterChart;
    @FXML
    NumberAxis xAxis;
    @FXML
    NumberAxis yAxis;

    @Override
    @FXML
    void loadHomeData(ActionEvent event) {
        checkForData("Home Team");
        temperatureScatterChart.getData().add(getChartData("home"));
    }

    @Override
    @FXML
    void loadAwayData(ActionEvent event) {
        checkForData("Away Team");
        temperatureScatterChart.getData().add(getChartData("away"));
    }

    @Override
    @FXML
    void loadCombinedData(ActionEvent event){
        clear(temperatureScatterChart);
        temperatureScatterChart.getData().add(getChartData("total"));
    }

    @FXML
    private void clearChart(ActionEvent event){
        clear(temperatureScatterChart);
    }

    @Override
    void checkForData(String seriesName){
        XYChart.Series seriesToRemove = null;
        for(XYChart.Series series : temperatureScatterChart.getData())
        {
            if (series.getName().equals(seriesName)) {
                seriesToRemove = series;
            }
        }
        temperatureScatterChart.getData().remove(seriesToRemove);
    }

    @Override
    XYChart.Series<Number, Number> getChartData(String awayHomeTotal) {
        XYChart.Series<Number, Number> data = new XYChart.Series<>();
        if(awayHomeTotal.equals("home")){
            data.setName("Home Team");
            getHomeTeamTempScore(data);
        } else if(awayHomeTotal.equals("away")){
            data.setName("Away Team");
            getAwayTeamTempScores(data);
        } else if(awayHomeTotal.equals("total")){
            data.setName("Combined Scores");
            getTotalScoresTemp(data);
        }
        return data;
    }

    private void getHomeTeamTempScore(XYChart.Series<Number, Number> data) {
        games.forEach(g -> data.getData().add(
                new XYChart.Data<Number, Number>(
                        g.getTemperature(),
                        g.getHomeScore()
                )));
    }

    private void getAwayTeamTempScores(XYChart.Series<Number, Number> data) {
        games.forEach(g -> data.getData().add(
                new XYChart.Data<Number, Number>(
                        g.getTemperature(),
                        g.getAwayScore()
                )));
    }


    private void getTotalScoresTemp(XYChart.Series<Number, Number> tempScoreHomeAway) {
        games.forEach(g -> tempScoreHomeAway.getData().add(
                new XYChart.Data<Number, Number>(
                        g.getTemperature(),
                        g.getAwayScore() + g.getHomeScore()
                )));
    }

}