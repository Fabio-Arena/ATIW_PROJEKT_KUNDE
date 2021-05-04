module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires ojdbc8;
    requires java.sql;
    requires java.naming;

    opens org.example to javafx.fxml;
    exports org.example;
}