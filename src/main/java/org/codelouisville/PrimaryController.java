package org.codelouisville;

import javafx.fxml.FXML;

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
