package com.example.projetoiiapp;

import baseDados.bll.Nacionalidadebll;
import baseDados.bll.TipoVeiculobll;
import baseDados.bll.Viagembll;
import baseDados.entity.PassageiroEntity;
import baseDados.entity.ViagemEntity;
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
import java.math.RoundingMode;
import java.util.Random;
import java.math.BigDecimal;


public class PedirViagemController {

    private PassageiroEntity passageiro;
    private ViagemEntity viagem;
    @FXML
    private Button voltarButton;

    @FXML
    private Button ajudaButton;

    @FXML
    private Button historicoButton;

    @FXML
    private Button avancarButton;
    @FXML
    private ComboBox tipoVeiculoComboBox;
    @FXML
    private TextField origemField;
    @FXML
    private TextField destinoField;
    @FXML
    private Label valorLabel;

    @FXML
    private void setAjudaButton(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ajuda-passageiro.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            AjudaPassageiroController controller = fxmlLoader.getController();
            controller.setPassageiro(passageiro);
            Stage stage = (Stage) ajudaButton.getScene().getWindow();
            stage.setTitle("Rebu - Ajuda");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initialize(){
        ObservableList<String> itemsTipoVeiculo = FXCollections.observableArrayList(TipoVeiculobll.listar());
        tipoVeiculoComboBox.setItems(itemsTipoVeiculo);

    }

    public void setValor(){
        ViagemEntity viagem = new ViagemEntity();
        viagem.setIdEstadoViagem(1);
        viagem.setIdPassageiro(passageiro.getIdPassageiro());
        viagem.setPago(false);
        viagem.setDestino(destinoField.getText());
        viagem.setOrigem(origemField.getText());
        viagem.setIdTipoVeiculo(TipoVeiculobll.getidTipoVeiculo(tipoVeiculoComboBox.getValue().toString()));

        // Criação de um objeto Random
        Random random = new Random();
        double numeroAleatorio = (random.nextInt(11) + 5);
        BigDecimal valorKm = new BigDecimal(Double.toString(numeroAleatorio));
        viagem.setDistancia(valorKm);
        viagem.setValorKm(BigDecimal.valueOf(1.75));

        viagem.setValor(viagem.getValorKm().multiply(viagem.getDistancia()).setScale(2, RoundingMode.HALF_UP));
        viagem.setValor(viagem.getValor().multiply(BigDecimal.valueOf(1).add(TipoVeiculobll.getAcrescimo(viagem.getIdTipoVeiculo()))));
        viagem.setPercent_condutor(BigDecimal.valueOf(0.8));

        valorLabel.setText(viagem.getValor().toString() + "€");
        this.viagem = viagem;
    }

    @FXML
    private void BackButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu-passageiro.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            MenuPassageiroController controller = fxmlLoader.getController();
            controller.setPassageiro(passageiro);
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
    protected void setAvancarButtonClick(){
        if(origemField.getText().isEmpty() || destinoField.getText().isEmpty() || tipoVeiculoComboBox.getValue() == null){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro no pagamento");
            alerta.setHeaderText(null);
            alerta.setContentText("É necessário preencher todos os campos!");
            alerta.showAndWait();
        }
        else{
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("metodo-pagamento.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 652, 431);
                MetodoPagamentoController controller = fxmlLoader.getController();
                controller.setViagem(viagem);
                Stage stage = (Stage) avancarButton.getScene().getWindow();
                stage.setTitle("Metodo de Pagamento");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void setPassageiro(PassageiroEntity passageiro){
        this.passageiro = passageiro;
    }

    protected void setViagem(ViagemEntity viagem){this.viagem=viagem;
    }
}
