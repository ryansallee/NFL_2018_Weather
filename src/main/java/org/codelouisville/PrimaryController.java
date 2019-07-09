package org.codelouisville;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.Axis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import org.w3c.dom.events.MouseEvent;

public class PrimaryController {

    @FXML
    private ScatterChart<Number, Number> temperatureChart;

    @FXML
    NumberAxis xAxis;
    @FXML
    NumberAxis yAxis;

    public void loadTempChart(ActionEvent event){

       XYChart.Series s1 = new XYChart.Series();
       s1.setName("MAN");
       s1.getData().add(new XYChart.Data(2, 3));
       temperatureChart.getData().add(App.getScatter());
    }


    public PrimaryController(){}

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
