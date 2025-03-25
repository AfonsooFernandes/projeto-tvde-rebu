package com.example.projetoiiapp;

import baseDados.bll.Condutorbll;
import baseDados.bll.Nacionalidadebll;
import baseDados.bll.Passageirobll;
import baseDados.bll.Staffbll;
import baseDados.entity.CondutorEntity;
import baseDados.entity.PassageiroEntity;
import baseDados.entity.StaffEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

public class RegistarPassageiroController {
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

    public void RegistarPassageiro(MouseEvent mouseEvent) {
        if(nomeField.getText().isEmpty() || nCartaoCidadaoField.getText().isEmpty() || nacionalidadeComboBox.getValue() == null || generoComboBox.getValue() == null || dataNascPicker.getValue() == null || passwordField.getText().isEmpty()){
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
                PassageiroEntity passageiro = new PassageiroEntity();

                passageiro.setNome(nomeField.getText());
                passageiro.setNCc(nCartaoCidadaoField.getText());

                LocalDate dataNascimento = dataNascPicker.getValue();
                LocalDate hoje = LocalDate.now();
                passageiro.setIdade(Period.between(dataNascimento, hoje).getYears());

                passageiro.setGenero(generoComboBox.getValue().toString().charAt(0));
                passageiro.setIdNacionalidade(Nacionalidadebll.getidNacionalidade(nacionalidadeComboBox.getValue().toString()));
                passageiro.setPassword(passwordField.getText());

                Passageirobll.criar(passageiro);


                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Registo Efetuado");
                alerta.setHeaderText(null);
                alerta.setContentText("Registo efetuado com sucesso!");
                alerta.showAndWait();
            }
        }





    }
}
