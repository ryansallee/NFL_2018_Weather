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
    static final List<Game> games = getQuery().getGamesFromDb();

    //Abstract methods to be implemented in subclasses. All chart controllers share these behaviors.
    @FXML
    abstract void loadHomeData(ActionEvent event);
    @FXML
    abstract void loadAwayData(ActionEvent event);
    @FXML
    abstract void loadCombinedData(ActionEvent event);
    abstract XYChart.Series getChartData(String awayHomeBoth);
    abstract void checkForData(String seriesName);
    abstract void clearChart(ActionEvent event);

    //All chart classes have a "Return to Home" button to navigate to the home.fxml scene. It is implemented here as not to violate DRY.
    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("home");
    }

    //All chart classes except TemperatureScatterController use these methods below to obtain overall averages
    //so they are implemented in this class and inherited.
    final double getOverallHomeAverage() {
        return games.stream()
                .mapToDouble(Game::getHomeScore)
                .average()
                .orElse(0.0);

    }

    final double getOverallAwayAverage() {
        return games.stream()
                .mapToDouble(Game::getAwayScore)
                .average()
                .orElse(0.0);

    }
}
