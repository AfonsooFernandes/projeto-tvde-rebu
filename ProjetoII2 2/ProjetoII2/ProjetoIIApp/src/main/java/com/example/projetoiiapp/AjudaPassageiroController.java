package com.example.projetoiiapp;

import baseDados.entity.PassageiroEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AjudaPassageiroController {
    private PassageiroEntity passageiro;

    @FXML
    private Button paginaInicialButton;

    @FXML
    private void BackButton(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu-passageiro.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            MenuPassageiroController controller = fxmlLoader.getController();
            controller.setPassageiro(passageiro);
            Stage stage = (Stage) paginaInicialButton.getScene().getWindow();
            stage.setTitle("Rebu - A sua viagem sempre à mão");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setPassageiro(PassageiroEntity passageiro) {
        this.passageiro = passageiro;
    }
}
