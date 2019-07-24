package org.codelouisville.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.codelouisville.App;

import javax.swing.*;
import java.io.IOException;

@SuppressWarnings("WeakerAccess")
//Controller for home.fxml page to navigate to other fxml scenes.
public class HomeController extends BaseController {

    public HomeController(){}

    //Methods to load the chart scenes that display data.
    @FXML
    private void switchToScatter(ActionEvent event) throws IOException {
        App.setRoot("temperaturescatter");
    }

    @FXML
    private void switchToTemperatureBar(ActionEvent event) throws IOException {
        App.setRoot("temperaturebar");
    }

    @FXML
    private void switchToConditionBar(ActionEvent event) throws IOException {
        App.setRoot("conditionbar");
    }

    @FXML
    private void switchToConditionTemperatureBar(ActionEvent event) throws IOException {
        App.setRoot("conditiontemperaturebar");
    }

}
