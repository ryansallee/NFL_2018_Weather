module org.codelouisville {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;
    requires json.path;


    opens org.codelouisville to javafx.fxml;
    exports org.codelouisville;
}
