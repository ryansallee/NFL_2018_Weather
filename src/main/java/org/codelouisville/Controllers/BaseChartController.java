package org.codelouisville.Controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import org.codelouisville.App;
import org.codelouisville.Models.Game;

import java.io.IOException;
import java.util.List;

import static org.codelouisville.App.getQueries;

public abstract class BaseChartController extends BaseController {
    static List<Game> games = getQueries().getGamesfromDb();
    abstract XYChart.Series getChartData(String awayHomeBoth);
    abstract void checkForData(String seriesName);

    protected void clear(XYChart chart) {
        chart.getData().clear();
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
