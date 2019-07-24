package org.codelouisville.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

@SuppressWarnings("WeakerAccess")
//Controller for temperaturescatter scene to show average points scored by condition.
public class TemperatureScatterController extends BaseChartController {
    //Fields
    @FXML
    private ScatterChart<Number, Number> temperatureScatterChart;
    @FXML
    NumberAxis xAxis;
    @FXML
    NumberAxis yAxis;

    //ActionEvent Methods to load and display the points scored and temperature in the temperatureScatterChart for every .
    //NFL game in 2018. Each method uses helper methods.
    @Override
    @FXML
    void loadHomeData(ActionEvent event) {
        checkForData( "Home Team");
        temperatureScatterChart.getData().add(getChartData("Home Team"));
    }

    @Override
    @FXML
    void loadAwayData(ActionEvent event) {
        checkForData("Away Team");
        temperatureScatterChart.getData().add(getChartData("Away Team"));
    }

    //Loads the total score(home score + away score) and temperature of every NFL game in 2018.
    //checkForData is not used as if the home and away data is present the TemperatureScatter Chart will be overloaded
    //with data. Clearing the whole chart is more prudent.
    @Override
    @FXML
    void loadCombinedData(ActionEvent event){
        clearChart(event);
        temperatureScatterChart.getData().add(getChartData("Total Game"));
    }

    @FXML
    void clearChart(ActionEvent event){
        temperatureScatterChart.getData().clear();
    }

    @Override
    void checkForData(String seriesName){
        XYChart.Series seriesToRemove = null;
        XYChart.Series removeTotalGame = null;
        for(XYChart.Series series : temperatureScatterChart.getData())
        {
            if (series.getName().equals(seriesName)) {
                seriesToRemove = series;
            }
            if (series.getName().equals("Total Game"))
            {
                removeTotalGame = series;
            }
        }
        temperatureScatterChart.getData().remove(seriesToRemove);
        temperatureScatterChart.getData().remove(removeTotalGame);

    }

    @Override
    XYChart.Series<Number, Number> getChartData(String awayHomeTotal) {
        XYChart.Series<Number, Number> data = new XYChart.Series<>();
        if(awayHomeTotal.equals("Home Team")){
            data.setName(awayHomeTotal);
            getHomeTeamTempScore(data);
        } else if(awayHomeTotal.equals("Away Team")){
            data.setName(awayHomeTotal);
            getAwayTeamTempScores(data);
        } else if(awayHomeTotal.equals("Total Game")){
            data.setName(awayHomeTotal);
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