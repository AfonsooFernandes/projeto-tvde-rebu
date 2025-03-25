package com.example.projetoiiapp;

import baseDados.bll.Condutorbll;
import baseDados.bll.Passageirobll;
import baseDados.bll.Staffbll;
import baseDados.entity.CondutorEntity;
import baseDados.entity.PassageiroEntity;
import baseDados.entity.StaffEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class IniciarSessaoController {

    @FXML
    private Button homeButton;
    @FXML
    private Button iniciarSessaoButton;
    @FXML
    private TextField nCartaoCidadaoField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button voltarButton;

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

    @FXML
    private void IniciarSessao(MouseEvent event){
        boolean isCondutor = false;
        boolean isPassageiro = false;
        boolean isResponsavel = false;
        boolean isAdmin = false;
        for(CondutorEntity condutor : Condutorbll.listar()){
            if(condutor.getPassword().equals(passwordField.getText()) && condutor.getnCc().equals(nCartaoCidadaoField.getText())){
                isCondutor = true;
                if(condutor.getIdEstado() == 2 ){
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-condutor.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                        MenuCondutorController controller = fxmlLoader.getController();
                        controller.setCondutor(condutor);
                        Stage stage = (Stage) iniciarSessaoButton.getScene().getWindow();
                        stage.setTitle("Rebu - A sua viagem sempre à mão");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(condutor.getIdEstado() == 1){
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Pedido de Registo Pendente");
                    alerta.setHeaderText(null);
                    alerta.setContentText("O seu pedido de registo ainda não foi aprovado, aguarde!");
                    alerta.showAndWait();

                }else{
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("O seu pedido foi recusado");
                    alerta.setHeaderText(null);
                    alerta.setContentText("O seu pedido de registo foi recusado!");
                    alerta.showAndWait();
                }

            }
        }

        for(PassageiroEntity passageiro : Passageirobll.listar()){
            if(passageiro.getPassword().equals(passwordField.getText()) && passageiro.getNCc().equals(nCartaoCidadaoField.getText())){
                isPassageiro = true;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-passageiro.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                    MenuPassageiroController controller = fxmlLoader.getController();
                    controller.setPassageiro(passageiro);
                    Stage stage = (Stage) iniciarSessaoButton.getScene().getWindow();
                    stage.setTitle("Rebu - A sua viagem sempre à mão");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for(StaffEntity responsavel : Staffbll.listarResponsaveis()){
            if(responsavel.getPassword().equals(passwordField.getText()) && responsavel.getNumero().equals(nCartaoCidadaoField.getText())){
                isResponsavel = true;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("painel-responsavel-registos.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                    Stage stage = (Stage) iniciarSessaoButton.getScene().getWindow();
                    stage.setTitle("Rebu - A sua viagem sempre à mão");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        for(StaffEntity admin : Staffbll.listarAdmins()){
            if(admin.getPassword().equals(passwordField.getText()) && admin.getNumero().equals(nCartaoCidadaoField.getText())){
                isAdmin = true;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("painel-admin.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                    Stage stage = (Stage) iniciarSessaoButton.getScene().getWindow();
                    stage.setTitle("Rebu - A sua viagem sempre à mão");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(!isAdmin && !isPassageiro && !isCondutor && !isResponsavel){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro Registo");
            alerta.setHeaderText(null);
            alerta.setContentText("Não existe nenhum utilizador com esses dados!");
            alerta.showAndWait();
        }


        /*
        if(isCondutor){
            //Passar para a tela de Condutor
        }else if(isPassageiro){
            try {
                PassageiroEntity passageiro = Passageirobll
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu-passageiro.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage stage = (Stage) iniciarSessaoButton.getScene().getWindow();
                stage.setTitle("Rebu - A sua viagem sempre à mão");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(isResponsavel){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("painel-responsavel-registos.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage stage = (Stage) iniciarSessaoButton.getScene().getWindow();
                stage.setTitle("Rebu - A sua viagem sempre à mão");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (isAdmin) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("painel-admin.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage stage = (Stage) iniciarSessaoButton.getScene().getWindow();
                stage.setTitle("Rebu - A sua viagem sempre à mão");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro Registo");
            alerta.setHeaderText(null);
            alerta.setContentText("Não existe nenhum utilizador com esses dados!");
            alerta.showAndWait();
        }
        */
    }


}
