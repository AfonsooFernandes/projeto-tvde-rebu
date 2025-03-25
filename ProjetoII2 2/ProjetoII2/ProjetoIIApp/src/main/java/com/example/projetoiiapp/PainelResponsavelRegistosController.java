package com.example.projetoiiapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PainelResponsavelRegistosController {
    @FXML
    private Button registosCondutoresButton;

    @FXML
    private void setRegistosCondutoresButton(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registos-condutores.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 871, 663);
            Stage stage = (Stage) registosCondutoresButton.getScene().getWindow();
            stage.setTitle("Registos de Condutores");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void registosVeiculos(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registos-veiculos.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 871, 663);
            Stage stage = (Stage) registosCondutoresButton.getScene().getWindow();
            stage.setTitle("Registos Veiculos");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
