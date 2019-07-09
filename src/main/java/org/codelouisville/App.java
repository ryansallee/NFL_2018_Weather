package org.codelouisville;

import javafx.application.Application;
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
    private static Queries getQueries() {
        return queries;
    }
    private static DBFunctions dbFunctions = new DBFunctions();
    private static Queries queries = new Queries(dbFunctions);
    private static Scene scene;
    private static List<Game> games = setGames();

    private static List<Game> setGames(){
        List<Game> gamesInner = null;
        if(getQueries().checkDb() <=0) {
            try {
                gamesInner = ReadCsv.readingCSV(queries);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Hey! We're seeding the db!");
            DbSeed dbSeed = new DbSeed(getDbFunctions());
            dbSeed.seed(gamesInner,queries);
            gamesInner = getQueries().getGamesfromDb();
        } else{
            System.out.println("We didn't need to seed the db!");
            gamesInner = getQueries().getGamesfromDb();
        }
        return gamesInner;
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

    public static XYChart.Series<Number, Number> getScatter(){
        //ObservableList<XYChart.Series<Double, Integer>> data = FXCollections.observableArrayList();
        XYChart.Series<Number, Number> tempScore = new XYChart.Series<Number, Number>();
        tempScore.setName("Temperature Scores");
/*        games.forEach(g ->
                new XYChart.Data<Number, Number>(
                        g.getTemperature(),
                        g.getHomeScore()
                ));
        games.forEach(g -> new XYChart.Data<Number, Number>(
                g.getTemperature(),
                g.getAwayScore()
        ));*/
        for (Game game: games) {

            tempScore.getData().add(new XYChart.Data<Number, Number>(
                    game.getTemperature(),
                    game.getHomeScore()
            ));
        }
        return tempScore;
        /*data.add(tempScore);
        data.forEach(d -> System.out.println(d.toString()));
        return data;*/
    }

    public static ScatterChart<Number, Number> createScatter(){
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        ScatterChart sc = new ScatterChart<Number, Number>(xAxis, yAxis);
        sc.getData().addAll(getScatter());
        sc.setTitle("2019 Temperature and Total Game Scores");
        return sc;
    }

    public static void main(String[] args) {

        launch();
    }

}