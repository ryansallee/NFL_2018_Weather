package org.codelouisville.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

abstract class BaseController {

    @FXML
    private void exit() {
        Platform.exit();
        System.exit(1);
    }

    @FXML
    private void goToDarkSky(MouseEvent mouseEvent) {
        try {
            Desktop.getDesktop().browse(new URI("https://darksky.net/poweredby/"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}