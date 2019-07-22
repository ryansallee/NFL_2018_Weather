package org.codelouisville.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

//Abstract class that provides basic functionality to all controllers either directly or through a chain of inheritance
abstract class BaseController {
    //Both methods below are used everywhere in the app so they are implemented here.
    @FXML
    private void exit() {
        Platform.exit();
        System.exit(1);
    }

    //The DarkSky "Powered By DarkSky" logo is required by DarkSky, and it must navigate to their "Powered By" page.
    @FXML
    private void goToDarkSky(MouseEvent mouseEvent) {
        try {
            Desktop.getDesktop().browse(new URI("https://darksky.net/poweredby/"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}