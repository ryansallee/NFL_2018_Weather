package org.codelouisville;

import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;

public class BaseController {
    @FXML
    private void exit(){

        System.exit(0);
    }

    protected void clear(XYChart chart)
    {
        chart.getData().clear();
    }
}
