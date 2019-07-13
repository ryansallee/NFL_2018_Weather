package org.codelouisville.Controllers;

import javafx.fxml.FXML;
import org.codelouisville.App;

import java.io.IOException;

@SuppressWarnings("WeakerAccess")
public class PrimaryController extends BaseController {

    public PrimaryController(){}

    @FXML
    private void switchToScatter() throws IOException {
        App.setRoot("scatter");
    }

    @FXML
    private void switchToTemperatureBar() throws IOException {
        App.setRoot("temperaturebar");
    }

    @FXML
    private void switchToConditonBar () throws IOException {
        App.setRoot("conditionbar");
    }

}
