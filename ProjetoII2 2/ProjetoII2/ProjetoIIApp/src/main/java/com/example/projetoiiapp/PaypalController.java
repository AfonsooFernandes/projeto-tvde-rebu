package com.example.projetoiiapp;

import baseDados.bll.Condutorbll;
import baseDados.bll.MetodoPagamentobll;
import baseDados.bll.Veiculobll;
import baseDados.bll.Viagembll;
import baseDados.entity.CondutorEntity;
import baseDados.entity.PassageiroEntity;
import baseDados.entity.VeiculoEntity;
import baseDados.entity.ViagemEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PaypalController {
    private PassageiroEntity passageiro;
    private ViagemEntity viagem;

    @FXML
    private TextField emailField;
    @FXML
    private Button voltarButton;
    @FXML
    private Button historicoButton;

    @FXML
    private Button avancarButton;

    @FXML
    private void BackButton(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("metodo-pagamento.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            MetodoPagamentoController controller = fxmlLoader.getController();
            controller.setPassageiro(passageiro);
            controller.setViagem(viagem);
            Stage stage = (Stage) voltarButton.getScene().getWindow();
            stage.setTitle("Rebu - A sua viagem sempre à mão");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void setAvancarButton() {
        if(emailField.getText().isEmpty()){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro no pagamento");
            alerta.setHeaderText(null);
            alerta.setContentText("É necessário preencher todos os campos!");
            alerta.showAndWait();
            return;
        }
        try {
            viagem.setIdMetodoPagamento(2);
            viagem.setPago(true);
            Viagembll.criar(viagem);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("viagem-pedida.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 600, 400);
            ViagemPedidaController controller = fxmlLoader.getController();
            controller.setViagem(viagem);
            controller.setPassageiro(passageiro);
            Stage stage = (Stage) avancarButton.getScene().getWindow();
            stage.setTitle("Viagem Pedida");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPassageiro(PassageiroEntity passageiro) {
        this.passageiro = passageiro;
    }

    public void setViagem(ViagemEntity viagem) {
        this.viagem = viagem;
    }
}

