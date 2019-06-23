module org.codelouisville {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;

    opens org.codelouisville to javafx.fxml;
    exports org.codelouisville;
}