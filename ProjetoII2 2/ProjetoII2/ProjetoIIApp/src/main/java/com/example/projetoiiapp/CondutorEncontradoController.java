package com.example.projetoiiapp;

import baseDados.bll.Condutorbll;
import baseDados.bll.Nacionalidadebll;
import baseDados.bll.Viagembll;
import baseDados.entity.CondutorEntity;
import baseDados.entity.PassageiroEntity;
import baseDados.entity.VeiculoEntity;
import baseDados.entity.ViagemEntity;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CondutorEncontradoController {
    private PassageiroEntity passageiro;
    private ViagemEntity viagem;
    private CondutorEntity condutor;
    private VeiculoEntity veiculo;


    @FXML
    private Label nomeField;
    @FXML
    private Label nacionalidadeField;
    @FXML
    private Label avaliacaoField;
    @FXML
    private Label marcaField;
    @FXML
    private Label modeloField;
    @FXML
    private Label matriculaField;
    @FXML
    private Label tempoField;

    private final int tempoTotalSegundos = 90;

    private int segundosRestantes = tempoTotalSegundos;

    private Timeline timer;

    public void preencher() {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            segundosRestantes--;
            if (segundosRestantes >= 0) {
                tempoField.setText(segundosRestantes + " segundos");
            } else {
                timer.stop();
                viagem.setIdEstadoViagem(2);
                Viagembll.atualizar(viagem);
                Viagembll.atualizar(viagem); // Atualize o estado da viagem no banco de dados
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Viagem Cancelada");
                alerta.setContentText("A viagem foi cancelada uma vez que não a confirmou!");
                alerta.showAndWait();

                // Recarregar a interface do usuário
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu-passageiro.fxml"));
                    MenuPassageiroController controller = fxmlLoader.getController();
                    controller.setPassageiro(passageiro);
                    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                    Stage stage = (Stage) tempoField.getScene().getWindow();
                    stage.setTitle("Rebu - A sua viagem sempre à mão");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));
        timer.setCycleCount(tempoTotalSegundos); // Defina o número de ciclos para o tempo total
        timer.play(); // Inicie o timer
        nomeField.setText(condutor.getNome());
        nacionalidadeField.setText(Nacionalidadebll.getNacionalidade(condutor.getIdNacionalidade()));
        avaliacaoField.setText(Condutorbll.getAvaliacaoWithId(condutor.getIdCondutor()));
        marcaField.setText(veiculo.getMarca());
        modeloField.setText(veiculo.getModelo());
        matriculaField.setText(veiculo.getMatricula());
    }


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
            Viagembll.atualizar(viagem);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu-passageiro.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 652, 431);
                MenuPassageiroController controller = fxmlLoader.getController();
                controller.setPassageiro(passageiro);
                Stage stage = (Stage) nacionalidadeField.getScene().getWindow();
                stage.setTitle("Rebu - A sua viagem sempre à mão");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    protected void confirmarViagem(){
        viagem.setIdEstadoViagem(5);
        Viagembll.atualizar(viagem);
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Viagem Confirmada");
        alerta.setHeaderText(null);
        alerta.setContentText("Aguarde pelo condutor e quando quiser, preencha o relatório feedback!");
        alerta.showAndWait();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("avaliar-condutor.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 652, 431);
            AvaliarCondutorController controller = fxmlLoader.getController();
            controller.setPassageiro(passageiro);
            controller.setCondutor(condutor);
            controller.setViagem(viagem);
            controller.preencher();
            Stage stage = (Stage) nacionalidadeField.getScene().getWindow();
            stage.setTitle("Rebu - A sua viagem sempre à mão");
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

    public void setCondutor(CondutorEntity condutor) {
        this.condutor = condutor;
    }

    public void setVeiculo(VeiculoEntity veiculo) {
        this.veiculo = veiculo;
    }
}
