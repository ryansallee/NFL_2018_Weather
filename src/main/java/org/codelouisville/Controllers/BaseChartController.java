package org.codelouisville.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import org.codelouisville.App;
import org.codelouisville.Models.Game;

import java.io.IOException;
import java.util.List;

import static org.codelouisville.App.getQuery;

abstract class BaseChartController extends BaseController {
    static final List<Game> games = getQuery().getGamesfromDb();

    @FXML
    abstract void loadHomeData(ActionEvent event);
    @FXML
    abstract void loadAwayData(ActionEvent event);
    @FXML
    abstract void loadCombinedData(ActionEvent event);
    abstract XYChart.Series getChartData(String awayHomeBoth);
    abstract void checkForData(String seriesName);
    abstract void clearChart(ActionEvent event);

    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("home");
    }

    double getOverallHomeAverage() {
        return games.stream()
                .mapToDouble(Game::getHomeScore)
                .average()
                .orElse(0.0);

    }

    double getOverallAwayAverage() {
        return games.stream()
                .mapToDouble(Game::getAwayScore)
                .average()
                .orElse(0.0);

    }
}
