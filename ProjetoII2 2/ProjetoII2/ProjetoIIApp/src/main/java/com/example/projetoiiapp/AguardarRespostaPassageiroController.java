package com.example.projetoiiapp;

import baseDados.bll.Passageirobll;
import baseDados.bll.Viagembll;
import baseDados.entity.CondutorEntity;
import baseDados.entity.PassageiroEntity;
import baseDados.entity.VeiculoEntity;
import baseDados.entity.ViagemEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AguardarRespostaPassageiroController {

    private CondutorEntity condutor;
    private VeiculoEntity veiculo;
    private ViagemEntity viagem;
    private PassageiroEntity passageiro;
    @FXML
    private Button atualizarButton;

    @FXML
    public void refresh(){
        if(Viagembll.getViagem(viagem.getIdViagem()).getIdEstadoViagem() == 5){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("iniciar-viagem.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                IniciarViagemController controller = fxmlLoader.getController();
                controller.setCondutor(condutor);
                controller.setPassageiro(passageiro);
                controller.setVeiculo(veiculo);
                controller.setViagem(viagem);
                controller.preencherCampos();
                Stage stage = (Stage) atualizarButton.getScene().getWindow();
                stage.setTitle("Rebu - A sua viagem sempre à mão");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(Viagembll.getViagem(viagem.getIdViagem()).getIdEstadoViagem() == 2){
            Viagembll.atualizar(viagem); // Atualize o estado da viagem no banco de dados
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Viagem Cancelada");
            alerta.setContentText("A viagem foi cancelada pelo passageiro!");
            alerta.showAndWait();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("viagens-disponiveis.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                ViagensDisponiveisController controller = fxmlLoader.getController();
                controller.setCondutor(condutor);
                controller.setVeiculo(veiculo);
                Stage stage = (Stage) atualizarButton.getScene().getWindow();
                stage.setTitle("Viagens disponiveis");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void setCondutor(CondutorEntity condutor) {
        this.condutor = condutor;
    }

    public void setVeiculo(VeiculoEntity veiculo) {
        this.veiculo = veiculo;
    }

    public void setViagem(ViagemEntity viagem) {
        this.viagem = viagem;
    }

    public void setPassageiro(PassageiroEntity passageiro) {
        this.passageiro = passageiro;
    }
}
