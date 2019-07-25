package org.codelouisville;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.codelouisville.Data.DBFunctions;
import org.codelouisville.Data.DbPersistence;
import org.codelouisville.Data.Query;
import org.codelouisville.Data.ReadCsv;
import org.codelouisville.Models.Game;

import java.io.IOException;
import java.util.List;

/**
 * JavaFX App
 */
//Entry Point
public class App extends Application {
    //Fields
    private static final DBFunctions DB_FUNCTIONS = new DBFunctions();
    private static final Query QUERY = new Query(DB_FUNCTIONS);
    private static Scene scene;

    //Getter
    public static Query getQuery() {
        return QUERY;
    }

    //Helper method that uses the readingCSV method to read the 2018_nfl_weather CSV to obtain a list of games if the game
    //table is empty. If the table is not empty, nothing is returned to games. The list of games is passed to the dbPersist
    //method if the list contains any data to persist the list of games to the games table. If the list of games is null
    //no major work is done.
    private static void readSeed(){
        List<Game> games = null;
            try {
                games = ReadCsv.readingCSV(QUERY);
            } catch (IOException e) {
                e.printStackTrace();
            }
        if(games != null) {
            System.out.println("Hey! We're seeding the db!");
            DbPersistence dbPersistence = new DbPersistence(DB_FUNCTIONS);
            dbPersistence.dbPersist(games, QUERY);
        } else{
            System.out.println("We didn't need to dbPersist the db!");
        }
    }

    //FXML methods
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("home"));
        stage.setScene(scene);
        stage.show();
    }


    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    //Main Method
    public static void main(String[] args) {
        //A shutdown hook is added to close the connection to the database when the application is exited through the exit
        // method in any of the controllers.
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DB_FUNCTIONS.closeEntityManager();
            DB_FUNCTIONS.closeEntityManagerFactory();
            DB_FUNCTIONS.stopServer();
            System.out.println("Shutdown hooks have run!");
            System.out.println("Closing program!");
        }));
        readSeed();
        launch();

    }

}