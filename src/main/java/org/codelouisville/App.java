package org.codelouisville;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import org.codelouisville.Logic.DBFunctions;
import org.codelouisville.Logic.DbSeed;
import org.codelouisville.Logic.Queries;
import org.codelouisville.Logic.ReadCsv;
import org.codelouisville.Models.Game;

import java.io.IOException;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {
    private static DBFunctions getDbFunctions() {
        return dbFunctions;
    }
    private static DBFunctions dbFunctions = new DBFunctions();
    private static Queries queries = new Queries(dbFunctions);
    private static Scene scene;

    public static Queries getQueries() {
        return queries;
    }

    private static void readSeed(){
        List<Game> games = null;
            try {
                games = ReadCsv.readingCSV(queries);
            } catch (IOException e) {
                e.printStackTrace();
            }
        if(games != null) {
            System.out.println("Hey! We're seeding the db!");
            DbSeed dbSeed = new DbSeed(getDbFunctions());
            dbSeed.seed(games,queries);
        } else{
            System.out.println("We didn't need to seed the db!");
        }

    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

/*    public static ObservableList<XYChart.Series<Number, Number>> getScatter(){
        ObservableList<XYChart.Series<Number, Number>> data = FXCollections.observableArrayList();
        XYChart.Series<Number, Number> tempScoreHome = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> tempScoreAway = new XYChart.Series<Number, Number>();
        tempScoreHome.setName("Temperature Scores");
       games.forEach(g -> tempScoreHome.getData().add(
                new XYChart.Data<Number, Number>(
                        g.getTemperature(),
                        g.getHomeScore()
                )));
        games.forEach(g -> tempScoreAway.getData().add(
                new XYChart.Data<Number, Number>(
                g.getTemperature(),
                g.getAwayScore()
        )));

        data.addAll(tempScoreHome, tempScoreAway);
        data.forEach(d -> System.out.println(d.toString()));
        return data;
    }*/



    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            getDbFunctions().closeEntityManager();
            getDbFunctions().closeEntityManagerFactory();
            getDbFunctions().stopServer();
            System.out.println("Shutdown hooks have run!");
            System.out.println("Closing program!");
        }));
        launch();

    }

}