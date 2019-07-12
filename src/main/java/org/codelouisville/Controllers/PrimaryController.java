package org.codelouisville.Controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import org.codelouisville.App;
import org.codelouisville.Controllers.BaseController;

import java.io.IOException;

public class PrimaryController extends BaseController {

    public PrimaryController(){}

    @FXML
    private void switchToScatter() throws IOException {
        App.setRoot("scatter");
    }

    @FXML void switchToTemperatureBar() throws IOException {
        App.setRoot("temperaturebar");
    }


}
