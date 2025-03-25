package com.example.projetoiiapp;

import baseDados.bll.Condutorbll;
import baseDados.bll.Veiculobll;
import baseDados.bll.Viagembll;
import baseDados.entity.CondutorEntity;
import baseDados.entity.PassageiroEntity;
import baseDados.entity.VeiculoEntity;
import baseDados.entity.ViagemEntity;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;

public class CartaoController {
    private PassageiroEntity passageiro;
    private ViagemEntity viagem;
    @FXML
    private TextField titularField;
    @FXML
    private TextField numeroField;
    @FXML
    private TextField mesField;
    @FXML
    private TextField anoField;
    @FXML
    private TextField cvcField;
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

    @FXML
    protected void setAvancarButton() {
        if (numeroField.getText().isEmpty() || mesField.getText().isEmpty() || anoField.getText().isEmpty() || cvcField.getText().isEmpty() || titularField.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro no pagamento");
            alerta.setHeaderText(null);
            alerta.setContentText("É necessário preencher todos os campos!");
            alerta.showAndWait();
            return;
        }

        viagem.setIdMetodoPagamento(3);
        viagem.setPago(true);
        Viagembll.criar(viagem);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("viagem-pedida"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 600, 400);
            ViagemPedidaController controller = fxmlLoader.getController();
            controller.setViagem(viagem);
            controller.setPassageiro(passageiro);
            Stage stage = new Stage(); // Criar uma nova janela
            stage.setTitle("Rebu - A sua viagem sempre à mao");
            stage.setScene(scene);
            stage.showAndWait(); // Mostrar e esperar até que a janela seja fechada
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
