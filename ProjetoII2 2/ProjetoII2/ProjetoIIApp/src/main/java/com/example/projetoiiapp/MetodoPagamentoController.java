package com.example.projetoiiapp;

import baseDados.entity.PassageiroEntity;
import baseDados.entity.ViagemEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MetodoPagamentoController {
    private PassageiroEntity passageiro;
    private ViagemEntity viagem;
    @FXML
    private Button voltarButton;


    @FXML
    private ImageView mbway;

    @FXML
    private ImageView paypal;

    @FXML
    private ImageView cartao;

    @FXML
    private void mbWayCliced(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mbway.fxml"));
            Parent root = fxmlLoader.load();
            MBWayController controller = fxmlLoader.getController();
            controller.setPassageiro(passageiro);
            controller.setViagem(viagem);
            Scene scene = new Scene(root, 600, 400);
            Stage stage = (Stage) paypal.getScene().getWindow();
            stage.setTitle("Pagamento");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void paypalClicked(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("paypal.fxml"));
            Parent root = fxmlLoader.load();
            PaypalController controller = fxmlLoader.getController();
            controller.setPassageiro(passageiro);
            controller.setViagem(viagem);
            Scene scene = new Scene(root, 600, 400);
            Stage stage = (Stage) paypal.getScene().getWindow();
            stage.setTitle("Pagamento");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cartaoClicked(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cartao.fxml"));
            Parent root = fxmlLoader.load();
            CartaoController controller = fxmlLoader.getController();
            controller.setPassageiro(passageiro);
            controller.setViagem(viagem);
            Scene scene = new Scene(root, 600, 400);
            Stage stage = (Stage) paypal.getScene().getWindow();
            stage.setTitle("Pagamento");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void BackButton(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pedir-viagem.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            PedirViagemController controller = fxmlLoader.getController();
            controller.setPassageiro(passageiro);

            Stage stage = (Stage) voltarButton.getScene().getWindow();
            stage.setTitle("Rebu - A sua viagem sempre à mão");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected void setViagem(ViagemEntity viagem){
        this.viagem = viagem;
    }

    protected void setPassageiro(PassageiroEntity passageiro){
        this.passageiro = passageiro;
    }
}
