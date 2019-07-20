package org.codelouisville;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.codelouisville.Data.DBFunctions;
import org.codelouisville.Data.DbSeed;
import org.codelouisville.Data.Query;
import org.codelouisville.Data.ReadCsv;
import org.codelouisville.Models.Game;

import java.io.IOException;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {
    private static final DBFunctions dbFunctions = new DBFunctions();
    private static final Query QUERY = new Query(dbFunctions);
    private static Scene scene;

    private static DBFunctions getDbFunctions() {
        return dbFunctions;
    }
    public static Query getQuery() {
        return QUERY;
    }

    private static void readSeed(){
        List<Game> games = null;
            try {
                games = ReadCsv.readingCSV(QUERY);
            } catch (IOException e) {
                e.printStackTrace();
            }
        if(games != null) {
            System.out.println("Hey! We're seeding the db!");
            DbSeed dbSeed = new DbSeed(getDbFunctions());
            dbSeed.seed(games, QUERY);
        } else{
            System.out.println("We didn't need to seed the db!");
        }

    }

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


    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            getDbFunctions().closeEntityManager();
            getDbFunctions().closeEntityManagerFactory();
            getDbFunctions().stopServer();
            System.out.println("Shutdown hooks have run!");
            System.out.println("Closing program!");
        }));
        readSeed();
        launch();

    }

}