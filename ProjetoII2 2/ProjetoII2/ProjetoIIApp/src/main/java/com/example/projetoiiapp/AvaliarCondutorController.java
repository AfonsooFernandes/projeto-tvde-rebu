package com.example.projetoiiapp;

import baseDados.bll.Feedbackbll;
import baseDados.entity.CondutorEntity;
import baseDados.entity.FeedbackEntity;
import baseDados.entity.PassageiroEntity;
import baseDados.entity.ViagemEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AvaliarCondutorController {
    private ViagemEntity viagem;
    private CondutorEntity condutor;
    private PassageiroEntity passageiro;


    @FXML
    private Button naoAvaliarButton;
    @FXML
    private Button avaliarButton;
    @FXML
    private Label nomeCondutorField;
    @FXML
    private TextField comentariosField;
    @FXML
    private ComboBox avaliacaoComboBox;


    public void preencher() {
        ObservableList<String> itemsGenero = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        avaliacaoComboBox.setItems(itemsGenero);
        nomeCondutorField.setText(condutor.getNome());
    }

    @FXML
    public void setAvaliarCondutor(){
        FeedbackEntity feedback = new FeedbackEntity();
        feedback.setIdViagem(viagem.getIdViagem());
        feedback.setIdCondutor(condutor.getIdCondutor());
        feedback.setIdPassageiro(passageiro.getIdPassageiro());
        feedback.setAvaliacao(Integer.parseInt(avaliacaoComboBox.getValue().toString()));
        feedback.setDescricao("Feedback do passageiro sobre o condutor\n" + comentariosField.getText());
        Feedbackbll.criar(feedback);
        avancar();
    }
    @FXML
    public void setNaoAvaliarButton(){
        avancar();
    }
    @FXML
    public void avancar(){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem REBU");
        alerta.setHeaderText(null);
        alerta.setContentText("Obrigado pela preferência! Rebu, a sua viagem sempre à mão!");
        alerta.showAndWait();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu-passageiro.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 652, 431);
            MenuPassageiroController controller = fxmlLoader.getController();
            controller.setPassageiro(passageiro);
            Stage stage = (Stage) avaliarButton.getScene().getWindow();
            stage.setTitle("Rebu - A sua viagem sempre à mão");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected void setPassageiro(PassageiroEntity passageiro){
        this.passageiro = passageiro;
    }
    protected void setCondutor(CondutorEntity condutor){
        this.condutor = condutor;
    }
    protected void setViagem(ViagemEntity viagem){
        this.viagem = viagem;
    }
}
