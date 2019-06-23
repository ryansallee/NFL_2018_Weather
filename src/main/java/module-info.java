module org.codelouisville {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.codelouisville to javafx.fxml;
    exports org.codelouisville;
}