package com.example.projetoiiapp;

import baseDados.entity.CondutorEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuCondutorController {
    private CondutorEntity condutor;
    @FXML
    private Button conduzirButton;
    @FXML
    private Button historicoButton;
    @FXML
    private Button ajudaButton;

    @FXML
    public void conduzir(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("escolher-veiculo.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            EscolherVeiculoController controller = fxmlLoader.getController();
            controller.setCondutor(condutor); // Passa o CondutorEntity para o construtor do EscolherVeiculoController
            controller.preencherCombobox();
            Stage stage = (Stage) conduzirButton.getScene().getWindow();
            stage.setTitle("Rebu - A sua viagem sempre à mão");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void registarVeiculo(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registar-veiculo.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            RegistarVeiculoController controller = fxmlLoader.getController();
            controller.setCondutor(condutor); // Passa o CondutorEntity para o construtor do EscolherVeiculoController
            controller.preencherCombobox();
            Stage stage = (Stage) conduzirButton.getScene().getWindow();
            stage.setTitle("Rebu - A sua viagem sempre à mão");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setAjudaButton(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ajuda-condutor.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            AjudaCondutorController controller = fxmlLoader.getController();
            controller.setCondutor(condutor);
            Stage stage = (Stage) ajudaButton.getScene().getWindow();
            stage.setTitle("Rebu - Ajuda");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void setHistoricoButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("historico-condutor.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1387, 663);
            HistoricoCondutorController controller = fxmlLoader.getController();
            controller.setCondutor(condutor);
            controller.preencherTabela();
            Stage stage = (Stage) historicoButton.getScene().getWindow();
            stage.setTitle("Histórico de viagens");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setCondutor(CondutorEntity condutor) {
        this.condutor = condutor;
    }
}
