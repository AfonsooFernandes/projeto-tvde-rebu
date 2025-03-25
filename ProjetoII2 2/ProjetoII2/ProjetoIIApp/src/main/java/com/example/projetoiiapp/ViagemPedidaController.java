package com.example.projetoiiapp;

import baseDados.bll.Condutorbll;
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
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.util.Optional;
import java.io.IOException;

public class ViagemPedidaController {
    private PassageiroEntity passageiro;
    private ViagemEntity viagem;

    @FXML
    private Label rebuTitle;

    @FXML
    protected void cancelarViagem(){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Cancelar Viagem");
        alerta.setHeaderText(null);
        alerta.setContentText("Tem a certeza que quer cancelar a viagem?");

        // Adicionando botões personalizados (Sim e Não)
        ButtonType botaoSim = new ButtonType("Sim");
        ButtonType botaoNao = new ButtonType("Não");

        alerta.getButtonTypes().setAll(botaoSim, botaoNao);

        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.isPresent() && resultado.get() == botaoSim) {
            viagem.setIdEstadoViagem(2);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu-passageiro.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 652, 431);
                MenuPassageiroController controller = fxmlLoader.getController();
                controller.setPassageiro(passageiro);
                Stage stage = (Stage) rebuTitle.getScene().getWindow();
                stage.setTitle("Rebu - A sua viagem sempre à mão");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void refresh() {
        viagem = Viagembll.getViagem(viagem.getIdViagem()); // Busca a viagem novamente no banco de dados
        if (viagem.getIdEstadoViagem() == 4) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("condutor-encontrado.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 652, 431);
                CondutorEncontradoController controller = fxmlLoader.getController();
                controller.setPassageiro(passageiro);
                controller.setViagem(viagem);
                controller.setVeiculo(Veiculobll.getWithId(viagem.getIdVeiculo()));
                controller.setCondutor(Condutorbll.getWithId(Veiculobll.getWithId(viagem.getIdVeiculo()).getIdCondutor()));
                controller.preencher();
                Stage stage = (Stage) rebuTitle.getScene().getWindow();
                stage.setTitle("Rebu - A sua viagem sempre à mão");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void setPassageiro(PassageiroEntity passageiro) {
        this.passageiro = passageiro;
    }

    public void setViagem(ViagemEntity viagem) {
        this.viagem = viagem;
    }
}
