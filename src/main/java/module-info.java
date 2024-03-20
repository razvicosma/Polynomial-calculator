module com.example.polynomial {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.polynomial to javafx.fxml;
    exports com.example.polynomial;
    exports com.example.polynomial.controller;
    opens com.example.polynomial.controller to javafx.fxml;
    exports com.example.polynomial.model;
    opens com.example.polynomial.model to javafx.fxml;
}