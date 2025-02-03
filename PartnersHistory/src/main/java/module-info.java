module com.example.partnershistory {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;

    opens com.example.partnershistory to javafx.fxml;
    exports com.example.partnershistory;
    exports com.example.partnershistory.controller;
    opens com.example.partnershistory.entity to javafx.base;
    opens com.example.partnershistory.controller to javafx.fxml;
}
