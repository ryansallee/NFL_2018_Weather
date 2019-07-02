module org.codelouisville {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;
    requires json.path;
    requires darksky.forecast.api;
    requires java.persistence;
    requires java.sql;
    requires com.h2database;


    opens org.codelouisville to javafx.fxml;
    exports org.codelouisville;
}
