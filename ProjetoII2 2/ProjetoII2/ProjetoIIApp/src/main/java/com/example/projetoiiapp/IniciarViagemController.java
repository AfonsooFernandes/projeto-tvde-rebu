package com.example.projetoiiapp;

import baseDados.bll.Nacionalidadebll;
import baseDados.bll.Passageirobll;
import baseDados.bll.Viagembll;
import baseDados.entity.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.DataInput;
import java.time.LocalDateTime;

import java.io.IOException;
import java.util.Date;

public class IniciarViagemController {
    private CondutorEntity condutor;
    private VeiculoEntity veiculo;
    private PassageiroEntity passageiro;
    private ViagemEntity viagem;

    @FXML
    private Label nomeField;
    @FXML
    private Label avaliacaoField;
    @FXML
    private Label nacionalidadeField;
    @FXML
    private Label origemField;
    @FXML
    private Label destinoField;


    public void preencherCampos(){
        nomeField.setText(passageiro.getNome());
        avaliacaoField.setText(Passageirobll.getAvaliacaoWithId(passageiro.getIdPassageiro()));
        nacionalidadeField.setText(Nacionalidadebll.getNacionalidade(passageiro.getIdNacionalidade()));
        origemField.setText(viagem.getOrigem());
        destinoField.setText(viagem.getDestino());

    }
    @FXML
    private void iniciarViagem(){
        // Obtém a data atual como java.util.Date
        java.util.Date utilDate = new java.util.Date();
        // Converte para java.sql.Date
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        viagem.setDataIni(sqlDate);
        Viagembll.atualizar(viagem);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("terminar-viagem.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            TerminarViagemController controller = fxmlLoader.getController();
            controller.setViagem(viagem);
            controller.setCondutor(condutor);
            controller.setPassageiro(passageiro);
            Stage stage = (Stage) origemField.getScene().getWindow();
            stage.setTitle("Rebu - A sua viagem sempre à mão");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCondutor(CondutorEntity condutor) {
        this.condutor = condutor;
    }

    public void setVeiculo(VeiculoEntity veiculo) {
        this.veiculo = veiculo;
    }

    public void setPassageiro(PassageiroEntity passageiro) {
        this.passageiro = passageiro;
    }

    public void setViagem(ViagemEntity viagem) {
        this.viagem = viagem;
    }
}
