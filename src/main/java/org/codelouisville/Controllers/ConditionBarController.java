package org.codelouisville.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.codelouisville.Models.Game;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

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
        //xAxis.setLabel("Weather Condition");
        //yAxis.setLabel("Points");
        conditionBarChart.getData().add(getChartData("home"));
    }

    @Override
    void loadAwayData(ActionEvent event) {

    }

    @Override
    void loadCombinedData(ActionEvent event) {

    }

    @FXML
    private void clearChart(ActionEvent event){
        clear(conditionBarChart);
    }

    @Override
    XYChart.Series getChartData(String awayHomeBoth) {
        XYChart.Series<String, Double> averages = new XYChart.Series<>();
        if(awayHomeBoth.equals("home")) {
            averages.setName("Home Team");
           /* games.stream()
                    .filter(g -> g.getWeatherCondition().equals("Dome"))
                    .mapToDouble(Game::getHomeScore)
                    .average()
                    .getAsDouble();
            ));*/
        }
        return averages;
    }

    @Override
    void checkForData(String seriesName) {
        return;
    }
}
