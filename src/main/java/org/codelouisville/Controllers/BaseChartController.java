package org.codelouisville.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import org.codelouisville.App;
import org.codelouisville.Models.Game;

import java.io.IOException;
import java.util.List;

import static org.codelouisville.App.getQuery;

//Abstract class to that allows for polymorphism as all of the concrete classes except HomeController directly inherit this class either.
abstract class BaseChartController extends BaseController {
    //Fields
    static final List<Game> games = getQuery().getGamesfromDb();

    //Abstract methods to be overriden. All chart controllers must implement these classes.
    @FXML
    abstract void loadHomeData(ActionEvent event);
    @FXML
    abstract void loadAwayData(ActionEvent event);
    @FXML
    abstract void loadCombinedData(ActionEvent event);
    abstract XYChart.Series getChartData(String awayHomeBoth);
    abstract void checkForData(String seriesName);
    abstract void clearChart(ActionEvent event);

    //All chart classes have a "Return to Home" button. It is implemented here as not to violate DRY.
    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("home");
    }

    //All chart classes except ScatterController use these methods below to obtain overall averages
    //so they are implemented in this class and inherited.
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
