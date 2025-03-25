package com.example.projetoiiapp;

import baseDados.bll.Viagembll;
import baseDados.entity.CondutorEntity;
import baseDados.entity.PassageiroEntity;
import baseDados.entity.ViagemEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TerminarViagemController {

    private ViagemEntity viagem;
    private CondutorEntity condutor;
    private PassageiroEntity passageiro;

    @FXML
    private Button terminarViagemButton;


    public void terminarViagem(){
        // Obtém a data atual como java.util.Date
        java.util.Date utilDate = new java.util.Date();
        // Converte para java.sql.Date
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        viagem.setDataFim(sqlDate);
        viagem.setIdEstadoViagem(3);
        Viagembll.atualizar(viagem);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("avaliar-passageiro.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            AvaliarPassageiroController controller = fxmlLoader.getController();
            controller.setViagem(viagem);
            controller.setCondutor(condutor);
            controller.setPassageiro(passageiro);
            Stage stage = (Stage) terminarViagemButton.getScene().getWindow();
            stage.setTitle("Avaliar Passageiro");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setViagem(ViagemEntity viagem) {
        this.viagem = viagem;
    }

    public void setCondutor(CondutorEntity condutor) {
        this.condutor = condutor;
    }

    public void setPassageiro(PassageiroEntity passageiro) {
        this.passageiro = passageiro;
    }
}
