package org.codelouisville;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import org.codelouisville.Models.Game;

import java.io.IOException;
import java.util.List;

import static org.codelouisville.App.getQueries;

public class ScatterController extends BaseController {
    private static List<Game> games = getQueries().getGamesfromDb();


    @FXML
    private ScatterChart<Number, Number> temperatureScatterChart;
    @FXML
    NumberAxis xAxis;
    @FXML
    NumberAxis yAxis;

    @FXML
    private void loadHomeData(ActionEvent event) {
        temperatureScatterChart.getData().add(getHomeTempScoreSeries());
    }

    @FXML
    private void loadAwayData(ActionEvent event) {
        temperatureScatterChart.getData().add(getAwayTempScoreSeries());
    }

    @FXML
    private void clearChart(ActionEvent event){
        clearPlot();
    }


    @FXML
    private void loadHomePlusAwayData(ActionEvent event){
        clearPlot();
        temperatureScatterChart.getData().add(getHomeAwayTempScoreSeries());
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }


    private XYChart.Series<Number, Number> getHomeTempScoreSeries(){
        XYChart.Series<Number, Number> tempScoreHome = new XYChart.Series<Number, Number>();
        tempScoreHome.setName("Home Team Scores");

        games.forEach(g -> tempScoreHome.getData().add(
                new XYChart.Data<Number, Number>(
                        g.getTemperature(),
                        g.getHomeScore()
                )));
        return tempScoreHome;
    }

    private XYChart.Series<Number, Number> getAwayTempScoreSeries(){
        XYChart.Series<Number, Number> tempScoreAway = new XYChart.Series<Number, Number>();
        tempScoreAway.setName("Away Team Scores");

        games.forEach(g -> tempScoreAway.getData().add(
                new XYChart.Data<Number, Number>(
                        g.getTemperature(),
                        g.getAwayScore()
                )));
        return tempScoreAway;
    }

    private XYChart.Series<Number, Number> getHomeAwayTempScoreSeries(){
        XYChart.Series<Number, Number> tempScoreHomeAway = new XYChart.Series<Number, Number>();
        tempScoreHomeAway.setName("Away and Home Team Scores Combined");

        games.forEach(g -> tempScoreHomeAway.getData().add(
                new XYChart.Data<Number, Number>(
                        g.getTemperature(),
                        g.getAwayScore() + g.getHomeScore()
                )));
        return tempScoreHomeAway;

    }
    private void clearPlot() {
        temperatureScatterChart.getData().clear();
    }
}