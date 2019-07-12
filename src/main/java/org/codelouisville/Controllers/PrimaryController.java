package org.codelouisville.Controllers;

import javafx.fxml.FXML;
import org.codelouisville.App;

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
