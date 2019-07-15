package org.codelouisville.Controllers;

import javafx.fxml.FXML;
import org.codelouisville.App;

import java.io.IOException;

@SuppressWarnings("WeakerAccess")
public class HomeController extends BaseController {

    public HomeController(){}

    @FXML
    private void switchToScatter() throws IOException {
        App.setRoot("scatter");
    }

    @FXML
    private void switchToTemperatureBar() throws IOException {
        App.setRoot("temperaturebar");
    }

    @FXML
    private void switchToConditionBar() throws IOException {
        App.setRoot("conditionbar");
    }

    @FXML
    private void switchToConditionTemperatureBar() throws IOException {
        App.setRoot("conditiontemperaturebar");
    }

}
