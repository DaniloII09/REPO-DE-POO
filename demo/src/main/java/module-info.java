module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.swing;
    requires javafx.media;

    opens org.example.demo to javafx.fxml;
    exports org.example.demo;
}