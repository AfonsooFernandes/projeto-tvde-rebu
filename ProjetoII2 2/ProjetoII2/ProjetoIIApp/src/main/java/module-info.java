module com.example.projetoiiapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jakarta.persistence;
    requires com.dlsc.formsfx;

    requires baseDados;
    requires javafx.web;
    requires google.maps.services;


    opens com.example.projetoiiapp to javafx.fxml;
    exports com.example.projetoiiapp;
}

