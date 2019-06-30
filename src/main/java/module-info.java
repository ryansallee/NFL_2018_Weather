module org.codelouisville {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;
    requires json.path;
    requires darksky.forecast.api;


    opens org.codelouisville to javafx.fxml;
    exports org.codelouisville;
}
