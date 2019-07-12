package org.codelouisville.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class BaseController {
/*    @FXML
    private Button darkSky;*/

    @FXML
    private void exit() {
        Platform.exit();
        System.exit(1);
    }

/*    public void initialize(){
        darkSky.setGraphic(new ImageView("https://darksky.net/poweredby/"));
    }*/


    @FXML
    private void goToDarkSky(MouseEvent mouseEvent) {
        try {
            Desktop.getDesktop().browse(new URI("https://darksky.net/poweredby/"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}