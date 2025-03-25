package com.example.projetoiiapp;

import baseDados.entity.PassageiroEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPassageiroController {

    private PassageiroEntity passageiro;
    @FXML
    private Button viajarButton;
    @FXML
    private Button historicoButton;
    @FXML
    private Button ajudaButton;

    @FXML
    private void setAjudaButton(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ajuda-passageiro.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            AjudaPassageiroController controller = fxmlLoader.getController();
            controller.setPassageiro(passageiro);
            Stage stage = (Stage) ajudaButton.getScene().getWindow();
            stage.setTitle("Rebu - Ajuda");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void setViajarButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pedir-viagem.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 600, 400);
            PedirViagemController controller = fxmlLoader.getController();
            controller.setPassageiro(passageiro);
            Stage stage = (Stage) viajarButton.getScene().getWindow();
            stage.setTitle("Pedir Viagem");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void setHistoricoButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("historico-passageiro.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1387, 663);
            HistoricoPassageiroController controller = fxmlLoader.getController();
            controller.setPassageiro(passageiro);
            controller.preencherTabela();
            Stage stage = (Stage) historicoButton.getScene().getWindow();
            stage.setTitle("Histórico de viagens");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    protected void setPassageiro(PassageiroEntity passageiro){
        this.passageiro = passageiro;
    }
}
