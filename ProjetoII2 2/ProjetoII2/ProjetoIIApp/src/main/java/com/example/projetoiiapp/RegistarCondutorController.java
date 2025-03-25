package com.example.projetoiiapp;

import baseDados.bll.*;
import baseDados.entity.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RegistarCondutorController {
    @FXML
    private Button homeButton;
    @FXML
    private Button voltarButton;
    @FXML
    private TextField nomeField;
    @FXML
    private DatePicker dataNascPicker;
    @FXML
    private ComboBox generoComboBox;

    @FXML
    private ComboBox nacionalidadeComboBox;

    @FXML
    private TextField nCartaoCidadaoField;

    @FXML
    private TextField nCartaConducaoField;

    @FXML
    private TextField nCertificadoField;

    @FXML
    private PasswordField passwordField;


    public void initialize() {
        ObservableList<String> itemsGenero = FXCollections.observableArrayList("M", "F");
        generoComboBox.setItems(itemsGenero);


        ObservableList<String> itemsNacionalidade = FXCollections.observableArrayList(Nacionalidadebll.listar());
        nacionalidadeComboBox.setItems(itemsNacionalidade);

    }

    @FXML
    protected void setHomeButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.setTitle("Rebu - A sua viagem sempre à mão");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void BackButton(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = (Stage) voltarButton.getScene().getWindow();
            stage.setTitle("Rebu - A sua viagem sempre à mão");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void RegistarCondutor(MouseEvent mouseEvent) {
        if(nomeField.getText().isEmpty() || nCartaConducaoField.getText().isEmpty() || nCertificadoField.getText().isEmpty() || nCartaoCidadaoField.getText().isEmpty() || nacionalidadeComboBox.getValue() == null || generoComboBox.getValue() == null || dataNascPicker.getValue() == null || passwordField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Preencha todos os campos");
            alert.setHeaderText(null);
            alert.setContentText("É necessário preencher todos os campos!");
            alert.showAndWait();
        }else{
            int flag = 0;
            for (PassageiroEntity passageiro : Passageirobll.listar()) {
                if (passageiro.getNCc().equals(nCartaoCidadaoField.getText())) {
                    flag = 1;
                }
            }
            for(CondutorEntity condutor : Condutorbll.listar()){
                if(condutor.getnCc().equals(nCartaoCidadaoField.getText())){
                    flag =1;
                }
            }
            for(StaffEntity staff : Staffbll.listar()){
                if(staff.getNumero().equals(nCartaoCidadaoField.getText())){
                    flag =1;
                }
            }
            if(flag == 1){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Erro Registo");
                alerta.setHeaderText(null);
                alerta.setContentText("Já existe um utilizador com esse número cartão de cidadão!");
                alerta.showAndWait();
            }else{
                CondutorEntity condutor = new CondutorEntity();

                condutor.setNome(nomeField.getText());
                condutor.setnCc(nCartaoCidadaoField.getText());

                LocalDate dataNascimento = dataNascPicker.getValue();
                LocalDate hoje = LocalDate.now();
                condutor.setIdade(Period.between(dataNascimento, hoje).getYears());

                condutor.setGenero(generoComboBox.getValue().toString().charAt(0));
                condutor.setIdNacionalidade(Nacionalidadebll.getidNacionalidade(nacionalidadeComboBox.getValue().toString()));
                condutor.setIdEstado(1);
                condutor.setnCertificadoTvde(nCertificadoField.getText());
                condutor.setnConducao(nCartaConducaoField.getText());
                condutor.setPassword(passwordField.getText());

                Condutorbll.criar(condutor);


                Alert alerta = new Alert(AlertType.INFORMATION);
                alerta.setTitle("Pedido de Registo Efetuado");
                alerta.setHeaderText(null);
                alerta.setContentText("O registro foi efetuado com sucesso!\nAguarde pela validação do pedido para começar!");
                alerta.showAndWait();
            }
        }



    }

}
