package com.example.projetoiiapp;

import baseDados.bll.*;
import baseDados.entity.CondutorEntity;
import baseDados.entity.PassageiroEntity;
import baseDados.entity.StaffEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistarStaffController {

    @FXML
    private Button voltarButton;
    @FXML
    private Button feedBacksViagensButton;
    @FXML
    private TextField numeroField;
    @FXML
    private TextField nomeField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox tipoComboBox;
    @FXML
    private Button registarButton;

    public void initialize(){
        ObservableList<String> items = FXCollections.observableArrayList(TipoStaffbll.listar());
        tipoComboBox.setItems(items);
    }
    @FXML
    private void BackButton(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gerir-staff.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            GerirStaffController controller = fxmlLoader.getController();
            controller.carregarDados();
            Stage stage = (Stage) voltarButton.getScene().getWindow();
            stage.setTitle("Rebu - A sua viagem sempre à mão");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void setFeedBacksViagensButton(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("feedbacks-viagens.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1484, 664);
            Stage stage = (Stage) feedBacksViagensButton.getScene().getWindow();
            stage.setTitle("FeedBacks Viagens");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void setRegistarButton(){
        if(numeroField.getText().isEmpty() || passwordField.getText().isEmpty() || tipoComboBox.getValue() == null|| nomeField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Preencha todos os campos");
            alert.setHeaderText(null);
            alert.setContentText("É necessário preencher todos os campos!");
            alert.showAndWait();
        }else {
            int flag = 0;
            for (PassageiroEntity passageiro : Passageirobll.listar()) {
                if (passageiro.getNCc().equals(numeroField.getText())) {
                    flag = 1;
                }
            }
            for (CondutorEntity condutor : Condutorbll.listar()) {
                if (condutor.getnCc().equals(numeroField.getText())) {
                    flag = 1;
                }
            }
            for (StaffEntity staff : Staffbll.listar()) {
                if (staff.getNumero().equals(numeroField.getText())) {
                    flag = 1;
                }
            }

            if (flag == 1) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Erro Registo");
                alerta.setHeaderText(null);
                alerta.setContentText("Já existe um utilizador com esse número cartão de cidadão!");
                alerta.showAndWait();
            } else {
                StaffEntity staff = new StaffEntity();
                staff.setNome(nomeField.getText());
                staff.setIdTipoStaff(TipoStaffbll.listarWithTipo(tipoComboBox.getValue().toString()).getId());
                staff.setNumero(numeroField.getText());
                staff.setPassword(passwordField.getText());
                Staffbll.criar(staff);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Staff Registado");
                alert.setHeaderText(null);
                alert.setContentText("Staff Registado com sucesso!");
                alert.showAndWait();

            }
        }
    }




}
