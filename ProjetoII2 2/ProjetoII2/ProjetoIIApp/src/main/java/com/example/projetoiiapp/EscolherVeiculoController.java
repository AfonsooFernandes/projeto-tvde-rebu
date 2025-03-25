package com.example.projetoiiapp;

import baseDados.bll.Veiculobll;
import baseDados.entity.CondutorEntity;
import baseDados.entity.VeiculoEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class EscolherVeiculoController {
    private CondutorEntity condutor;

    @FXML
    private ComboBox veiculoComboBox;

    @FXML
    private Button avancarButton;
    @FXML
    private Button historicoButton;
    @FXML
    private Button voltarButton;
    @FXML
    private Button ajudaButton;

    public void preencherCombobox(){
        ObservableList<String> itemsVeiculos = FXCollections.observableArrayList();
        List<VeiculoEntity> veiculosCondutor = Veiculobll.listarVeiculosRegistadosCondutor(condutor.getIdCondutor());
        for(VeiculoEntity veiculo1 : veiculosCondutor){
            itemsVeiculos.add(veiculo1.getMatricula());
        }
        veiculoComboBox.setItems(itemsVeiculos);

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
    private void BackButton(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu-condutor.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            MenuCondutorController controller = fxmlLoader.getController();
            controller.setCondutor(condutor);
            Stage stage = (Stage) voltarButton.getScene().getWindow();
            stage.setTitle("Rebu - A sua viagem sempre à mão");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void avancar(){
        if(veiculoComboBox.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Seleção Necessária");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um veículo.");
            alert.showAndWait();
        }else{
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("viagens-disponiveis.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 909, 491);
                ViagensDisponiveisController controller = fxmlLoader.getController();
                controller.setCondutor(condutor);
                controller.setVeiculo(Veiculobll.listarVeiculosMatricula(veiculoComboBox.getValue().toString()));
                controller.preencherTabela();
                Stage stage = (Stage) voltarButton.getScene().getWindow();
                stage.setTitle("Viagens Disponiveis");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
    @FXML
    public void registarVeiculo(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registar-veiculo.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            RegistarVeiculoController controller = fxmlLoader.getController();
            controller.setCondutor(condutor);
            controller.preencherCombobox();
            Stage stage = (Stage) veiculoComboBox.getScene().getWindow();
            stage.setTitle("Rebu - A sua viagem sempre à mão");
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
