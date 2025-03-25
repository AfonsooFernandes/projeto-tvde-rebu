package com.example.projetoiiapp;

import baseDados.bll.Nacionalidadebll;
import baseDados.bll.TipoVeiculobll;
import baseDados.bll.Veiculobll;
import baseDados.entity.CondutorEntity;
import baseDados.entity.VeiculoEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistarVeiculoController {

    private CondutorEntity condutor;

    @FXML
    private TextField marcaField;
    @FXML
    private TextField modeloField;
    @FXML
    private TextField anoFabricoField;
    @FXML
    private TextField matriculaField;
    @FXML
    private ComboBox tipoVeiculoComboBox;
    @FXML
    private Button voltarButton;
    @FXML
    protected Button registarButton;


    @FXML
    private void BackButton() {
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
    public void registar(){
        if(marcaField.getText().isEmpty() || modeloField.getText().isEmpty() || anoFabricoField.getText().isEmpty() || matriculaField.getText().isEmpty() || tipoVeiculoComboBox.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Preencha todos os campos");
            alert.setHeaderText(null);
            alert.setContentText("É necessário preencher todos os campos!");
            alert.showAndWait();
        }else{
            int flag = 0;
            for(VeiculoEntity veiculo : Veiculobll.listar()){
                if(veiculo.getMatricula().equals(matriculaField.getText())){
                    flag =1;
                }
            }
            if(flag ==1){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Matricula Inválida");
                alert.setHeaderText(null);
                alert.setContentText("Já existe um veículo registado com essa matricula!");
                alert.showAndWait();
            }else{
                VeiculoEntity veiculo = new VeiculoEntity();
                veiculo.setIdCondutor(condutor.getIdCondutor());
                veiculo.setIdTipoVeiculo(TipoVeiculobll.getidTipoVeiculo(tipoVeiculoComboBox.getValue().toString()));
                veiculo.setAnoFabrico(anoFabricoField.getText());
                veiculo.setMarca(marcaField.getText());
                veiculo.setModelo(modeloField.getText());
                veiculo.setIdEstado(1);
                veiculo.setMatricula(matriculaField.getText());
                Veiculobll.criar(veiculo);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pedido Submetido");
                alert.setHeaderText(null);
                alert.setContentText("Aguarde enquanto o pedido é validado!");
                alert.showAndWait();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu-condutor.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                    MenuCondutorController controller = fxmlLoader.getController();
                    controller.setCondutor(condutor);
                    Stage stage = (Stage) registarButton.getScene().getWindow();
                    stage.setTitle("Rebu - A sua viagem sempre à mão");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void setCondutor(CondutorEntity condutor) {
        this.condutor = condutor;
    }

    public void preencherCombobox() {
        ObservableList<String> itemsTipoVeiculo = FXCollections.observableArrayList(TipoVeiculobll.listar());
        tipoVeiculoComboBox.setItems(itemsTipoVeiculo);
    }
}
