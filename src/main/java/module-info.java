module com.example.modul2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires com.example.partnershistory;

    opens com.example.modul2 to javafx.fxml;
    exports com.example.modul2;
    exports com.example.modul2.controller;
    opens com.example.modul2.controller to javafx.fxml;
    exports com.example.modul2.entity;
    opens com.example.modul2.entity to javafx.fxml;
    exports com.example.modul2.databaseTools;
}
